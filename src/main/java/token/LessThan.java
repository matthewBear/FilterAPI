package token;

import java.util.Map;

public class LessThan extends NumericalComparison {

    private String property;
    private String value;

    public LessThan(String property, String value) {
        super();
        this.property = property;
        this.value = value;
    }

    @Override
    public String toString() {
        return "lessThan(" + property + ", " + value + ")";
    }

    @Override
    public boolean evaluate(Map<String, String> resource) throws PropertyNotFoundException {
        return compareNumberStrings(getProperty(property, resource), value, true);
    }
}

