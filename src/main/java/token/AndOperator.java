package token;

import java.util.Map;

public class AndOperator extends BooleanOperator {

    public AndOperator() {
        super();
    }

    @Override
    public String toString() {
        return "AND";
    }

    @Override
    public int getOperatorOrdinal() {
        return 2;
    }

    @Override
    public boolean booleanCompare(Comparable a, Comparable b, Map<String, String> resource)
            throws PropertyNotFoundException {
        return a.evaluate(resource) && b.evaluate(resource);
    }
}
