package token;

public abstract class NumericalComparison extends Comparable {

    public boolean compareNumberStrings(String a, String b, boolean isLessThan){
        if (a.chars().allMatch( Character::isDigit) && b.chars().allMatch( Character::isDigit)) {
            int propValueInt = Integer.parseInt(a);
            int valueInt = Integer.parseInt(b);
            return isLessThan ? valueInt < propValueInt : valueInt > propValueInt;
        } else {
            return isLessThan ? a.compareToIgnoreCase(b) < 0 : a.compareToIgnoreCase(b) > 0;
        }
    }
}
