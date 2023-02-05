import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import token.AndOperator;
import token.BooleanOperator;
import token.Comparable;
import token.EqualTo;
import token.GreaterThan;
import token.IsPresent;
import token.LessThan;
import token.Literal;
import token.MatchesRegex;
import token.OrOperator;
import token.PropertyNotFoundException;
import token.Token;
import token.enums.TokenType;

public class Filter {
    Builder builder;

    private Filter(Builder builder) {
        this.builder = builder;
    }

    public boolean matches(Map<String, String> resource) throws PropertyNotFoundException {
        //stack is ordered in reverse polish notation, for order of operations
        Stack<Comparable> stack = new Stack<>();
        for(Token token : builder.queue) {
            if (token.getType() == TokenType.COMPARABLE) {
                stack.push(new Literal(((Comparable)token).evaluate(resource)));
                continue;
            }

            BooleanOperator booleanOperator = (BooleanOperator) token;
            Comparable a = stack.pop();
            Comparable b = stack.pop();

            stack.push(new Literal(booleanOperator.booleanCompare(a,b, resource)));
        }

        return stack.pop().evaluate(resource);
    }

    @Override
    public String toString() {
        return builder.stringBuilder.substring(1);
    }

    public static class Builder {
        private StringBuilder stringBuilder = new StringBuilder();
        Queue<Token> queue = new ArrayDeque<>();
        Deque<BooleanOperator> stack = new ArrayDeque<>();
        TokenType previousType = null;

        //////////
        //helper methods
        /////////
        private void tokenOrderCheck(TokenType currentType) throws InvalidTokenOrderException {
            if (previousType == currentType) {
                throw new InvalidTokenOrderException("Invalid token order");
            }
            previousType = currentType;
        }

        private void addTokenToQueue(Token newToken) {
            stringBuilder.append(" " + newToken);
            queue.add(newToken);
        }

        //////////
        //literals
        /////////
        public Builder trueLiteral() throws InvalidTokenOrderException {
            Token newToken = new Literal(true);
            tokenOrderCheck(newToken.getType());
            addTokenToQueue(newToken);
            return this;
        }

        public Builder falseLiteral() throws InvalidTokenOrderException {
            Token newToken = new Literal(false);
            tokenOrderCheck(newToken.getType());
            addTokenToQueue(newToken);
            return this;
        }

        ///////
        //operators
        //////
        public Builder or() throws InvalidTokenOrderException {
            BooleanOperator newToken = new OrOperator();
            tokenOrderCheck(newToken.getType());
            checkStack(newToken);
            return this;
        }

        public Builder and() throws InvalidTokenOrderException {
            BooleanOperator newToken = new AndOperator();
            tokenOrderCheck(newToken.getType());
            checkStack(newToken);
            return this;
        }

        private void checkStack(BooleanOperator newToken) {
            while (!stack.isEmpty() && stack.peek().getOperatorOrdinal() > newToken.getOperatorOrdinal()) {
                queue.add(stack.pop());
            }
            stack.push(newToken);
            stringBuilder.append(" " + newToken);
        }

        //////
        //comparisons
        //////
        public Builder isPresent(String property) throws InvalidTokenOrderException {
            Token newToken = new IsPresent(property);
            tokenOrderCheck(newToken.getType());
            addTokenToQueue(newToken);
            return this;
        }

        public Builder equalTo(String property, String str) throws InvalidTokenOrderException {
            Token newToken = new EqualTo(property, str);
            tokenOrderCheck(newToken.getType());
            addTokenToQueue(newToken);
            return this;
        }

        public Builder lessThan(String property, String str) throws InvalidTokenOrderException {
            Token newToken = new LessThan(property, str);
            tokenOrderCheck(newToken.getType());
            addTokenToQueue(newToken);
            return this;
        }

        public Builder greaterThan(String property, String str) throws InvalidTokenOrderException {
            Token newToken = new GreaterThan(property, str);
            tokenOrderCheck(newToken.getType());
            addTokenToQueue(newToken);
            return this;
        }

        public Builder matchesRegex(String property, String regex)
                throws InvalidTokenOrderException {
            Token newToken = new MatchesRegex(property, regex);
            tokenOrderCheck(newToken.getType());
            addTokenToQueue(newToken);
            return this;
        }

        ///////
        //build
        ///////
        public Filter build() {
            while (!stack.isEmpty()) {
                queue.add(stack.pop());
            }

            return new Filter(this);
        }
    }
}
