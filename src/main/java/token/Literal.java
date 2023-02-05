package token;

import java.util.Map;
import token.enums.TokenType;

public class Literal extends Comparable {

    private boolean value;

    public Literal(boolean val) {
        this.value = val;
    }

    @Override
    public TokenType getType() {
        return TokenType.COMPARABLE;
    }

    @Override
    public String toString() {
        return value ? "TRUE" : "FALSE";
    }

    @Override
    public boolean evaluate(Map<String, String> resource) {
        return this.value;
    }
}
