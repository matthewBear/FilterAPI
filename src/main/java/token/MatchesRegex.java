package token;

import java.util.Map;

public class MatchesRegex extends Comparable {

    private String property;
    private String regex;

    public MatchesRegex(String property, String regex) {
        super();
        this.property = property;
        this.regex = regex;
    }

    @Override
    public String toString() {
        return "matchesRegex(" + property + ", " + regex + ")";
    }

    @Override
    public boolean evaluate(Map<String, String> resource) throws PropertyNotFoundException {
        return getProperty(property, resource).matches(regex);
    }
}
