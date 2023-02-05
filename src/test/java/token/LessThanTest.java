package token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import token.enums.TokenType;

public class LessThanTest {

    @Test
    public void testGetType() {
        Token test = new LessThan("test", "test");
        assertEquals(TokenType.COMPARABLE, test.getType());
    }

    @Test
    public void testLessThan_True() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "a");

        Comparable test = new LessThan("test", "b");
        assertTrue(test.evaluate(user));
    }

    @Test
    public void testLessThan_False() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "b");

        Comparable test = new LessThan("test", "a");
        assertFalse(test.evaluate(user));
    }

    @Test
    public void testLessThanNumber_True() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "10");

        Comparable test = new LessThan("test", "9");
        assertTrue(test.evaluate(user));
    }

    @Test
    public void testLessThanNumber_False() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "10");

        Comparable test = new LessThan("test", "11");
        assertFalse(test.evaluate(user));
    }

    @Test
    public void testLessThanNumberCheckAgaintString_False() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "10");

        Comparable test = new LessThan("test", "    ");
        assertFalse(test.evaluate(user));
    }

    @Test
    public void testLessThan_PropertyDoesntExist() {
        Map<String, String> user = new LinkedHashMap<String, String>();

        Comparable test = new LessThan("not there", "nope");
        assertThrows(PropertyNotFoundException.class, () -> test.evaluate(user));
    }
}
