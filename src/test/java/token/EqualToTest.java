package token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import token.enums.TokenType;

public class EqualToTest {

    @Test
    public void testGetType() {
        Token test = new EqualTo("test", "test");
        assertEquals(TokenType.COMPARABLE, test.getType());
    }

    @Test
    public void testEqualTo_True() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "test");

        Comparable test = new EqualTo("test", "test");
        assertTrue(test.evaluate(user));
    }

    @Test
    public void testEqualToIgnoreCase_True() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "TEST");

        Comparable test = new EqualTo("test", "test");
        assertTrue(test.evaluate(user));
    }

    @Test
    public void testEqualTo_False() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "test");

        Comparable test = new EqualTo("test", "nope");
        assertFalse(test.evaluate(user));
    }

    @Test
    public void testEqualTo_PropertyDoesntExist() {
        Map<String, String> user = new LinkedHashMap<String, String>();

        Comparable test = new EqualTo("not there", "nope");
        assertThrows(PropertyNotFoundException.class, () -> test.evaluate(user));
    }
}
