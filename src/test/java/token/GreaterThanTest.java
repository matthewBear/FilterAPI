package token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import token.enums.TokenType;

public class GreaterThanTest {

    @Test
    public void testGetType() {
        Token test = new GreaterThan("test", "test");
        assertEquals(TokenType.COMPARABLE, test.getType());
    }

    @Test
    public void testGreaterThanString_True() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "b");

        Comparable test = new GreaterThan("test", "a");
        assertTrue(test.evaluate(user));
    }

    @Test
    public void testGreaterThanString_False() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "a");

        Comparable test = new GreaterThan("test", "b");
        assertFalse(test.evaluate(user));
    }

    @Test
    public void testGreaterThanNumber_True() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "10");

        Comparable test = new GreaterThan("test", "11");
        assertTrue(test.evaluate(user));
    }

    @Test
    public void testGreaterThanNumber_False() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "10");

        Comparable test = new GreaterThan("test", "9");
        assertFalse(test.evaluate(user));
    }

    @Test
    public void testGreaterThanNumberCheckAgaintString_False() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "10");

        Comparable test = new GreaterThan("test", "AAAA");
        assertFalse(test.evaluate(user));
    }


    @Test
    public void testGreaterThan_PropertyDoesntExist() {
        Map<String, String> user = new LinkedHashMap<String, String>();

        Comparable test = new GreaterThan("not there", "nope");
        assertThrows(PropertyNotFoundException.class, () -> test.evaluate(user));
    }
}
