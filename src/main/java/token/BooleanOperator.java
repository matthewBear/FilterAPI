package token;

import java.util.Map;
import token.enums.TokenType;

public abstract class BooleanOperator implements Token {

    @Override
    public TokenType getType() {
        return TokenType.OPERATOR;
    }

    public abstract int getOperatorOrdinal();

    public abstract boolean booleanCompare(Comparable a, Comparable b, Map<String, String> resource)
            throws PropertyNotFoundException;
}
