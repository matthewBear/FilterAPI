package token;

import java.util.Map;

public class IsPresent extends Comparable {

    private String property;

    public IsPresent(String property) {
        super();
        this.property = property;
    }

    @Override
    public String toString() {
        return "isPresent(" + property + ")";
    }

    @Override
    public boolean evaluate(Map<String, String> resource) {
        return resource.containsKey(property);
    }
}
