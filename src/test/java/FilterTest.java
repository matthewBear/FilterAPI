import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import token.PropertyNotFoundException;

public class FilterTest {

    @Test
    public void testTrueLiteral() throws InvalidTokenOrderException, PropertyNotFoundException {
        Filter testFilter = new Filter.Builder()
                .trueLiteral().build();

        assertTrue(testFilter.matches(null));
    }

    @Test
    public void testFalseLiteral() throws InvalidTokenOrderException, PropertyNotFoundException {
        Filter testFilter = new Filter.Builder()
                .falseLiteral().build();

        assertFalse(testFilter.matches(null));
    }

    @Test
    public void testCaseSensitivity() throws InvalidTokenOrderException, PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("hello", "Joe");

        Filter testCaseCaps = new Filter.Builder()
                .isPresent("HELLO").build();
        Filter testLowercase = new Filter.Builder()
                .isPresent("hello").build();

        assertFalse(testCaseCaps.matches(user));
        assertTrue(testLowercase.matches(user));
    }

    @Test
    public void testOrFilter() throws InvalidTokenOrderException, PropertyNotFoundException {
        Filter testFilter = new Filter.Builder()
                .falseLiteral()
                .or()
                .trueLiteral()
                .build();

        assertTrue(testFilter.matches(null));
    }

    @Test
    public void testAndFilter() throws InvalidTokenOrderException, PropertyNotFoundException {
        Filter testFilter = new Filter.Builder()
                .falseLiteral()
                .and()
                .trueLiteral()
                .build();

        assertFalse(testFilter.matches(null));
    }

    @Test
    public void testOrderOfOperations() throws InvalidTokenOrderException, PropertyNotFoundException {
        Filter testFilter = new Filter.Builder()
                .trueLiteral()
                .and()
                .trueLiteral()
                .or()
                .falseLiteral()
                .and()
                .falseLiteral()
                .build();

        assertTrue(testFilter.matches(null));
    }

    @Test
    public void testToString() throws InvalidTokenOrderException {
        Filter testFilter = new Filter.Builder()
                .trueLiteral()
                .and()
                .falseLiteral()
                .or()
                .equalTo("eqProp", "eqVal")
                .and()
                .greaterThan("gtProp", "gtVal")
                .or()
                .isPresent("presentProp")
                .and()
                .lessThan("ltProp", "ltVal")
                .or()
                .matchesRegex("regexProp", "regexVal")
                .build();

        assertEquals("TRUE AND FALSE OR equalTo(eqProp, eqVal) AND greaterThan(gtProp, gtVal) OR "
                        + "isPresent(presentProp) AND lessThan(ltProp, ltVal) OR matchesRegex(regexProp, regexVal)",
                testFilter.toString());
    }

    @Test
    public void testTokenOrderError(){
        assertThrows(InvalidTokenOrderException.class, () -> new Filter.Builder()
                .trueLiteral()
                .and()
                .or()
                .build());

        assertThrows(InvalidTokenOrderException.class, () -> new Filter.Builder()
                .trueLiteral()
                .and()
                .trueLiteral()
                .equalTo(null, null)
                .build());
    }

    @Test
    public void testComplexMatching() throws InvalidTokenOrderException, PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("role", "administrator");
        user.put("age", "35");
        user.put("name", "Jim");

        Filter test = new Filter.Builder()
                .isPresent("age")
                .and()
                .lessThan("age", "30")
                .or()
                .equalTo("role", "engineer")
                .or()
                .falseLiteral()
                .or()
                .isPresent("name")
                .and()
                .equalTo("name", "Joe")
                .build();

        assertTrue(test.matches(user));
    }
}
