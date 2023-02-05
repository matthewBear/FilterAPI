package token;

import java.util.Map;

public class OrOperator extends BooleanOperator {

    public OrOperator() {
        super();
    }

    @Override
    public String toString() {
        return "OR";
    }

    @Override
    public int getOperatorOrdinal() {
        return 1;
    }

    @Override
    public boolean booleanCompare(Comparable a, Comparable b, Map<String, String> resource)
            throws PropertyNotFoundException {
        return a.evaluate(resource) || b.evaluate(resource);
    }
}
