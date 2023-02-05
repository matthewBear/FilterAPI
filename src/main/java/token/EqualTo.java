package token;

import java.util.Map;

public class EqualTo extends Comparable {

    private String property;
    private String value;

    public EqualTo(String property, String value) {
        super();
        this.property = property;
        this.value = value;
    }

    @Override
    public String toString() {
        return "equalTo(" + property + ", " + value + ")";
    }

    @Override
    public boolean evaluate(Map<String, String> resource) throws PropertyNotFoundException {
        return getProperty(property, resource).equalsIgnoreCase(value);
    }
}
