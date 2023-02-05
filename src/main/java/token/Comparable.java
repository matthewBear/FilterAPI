package token;

import java.util.Map;
import token.enums.TokenType;

public abstract class Comparable implements Token {

    @Override
    public TokenType getType() {
        return TokenType.COMPARABLE;
    }

    public String getProperty(String prop, Map<String, String> resource)
            throws PropertyNotFoundException {
        String propValue = resource.get(prop);

        if (propValue == null) {
            throw new PropertyNotFoundException("Property not found");
        }

        return propValue;
    }

    public abstract boolean evaluate(Map<String, String> resource) throws PropertyNotFoundException;
}
