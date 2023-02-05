package token;

import java.util.Map;

public class GreaterThan extends NumericalComparison {

    private String property;
    private String value;

    public GreaterThan(String property, String value) {
        super();
        this.property = property;
        this.value = value;
    }

    @Override
    public String toString() {
        return "greaterThan(" + property + ", " + value + ")";
    }

    @Override
    public boolean evaluate(Map<String, String> resource) throws PropertyNotFoundException {
        return compareNumberStrings(getProperty(property, resource), value, false);
    }
}

