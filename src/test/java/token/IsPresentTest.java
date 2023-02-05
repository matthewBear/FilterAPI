package token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import token.enums.TokenType;

public class IsPresentTest {

    @Test
    public void testGetType() {
        Token test = new IsPresent("test");
        assertEquals(TokenType.COMPARABLE, test.getType());
    }

    @Test
    public void testIsPresent_True() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "test");

        Comparable test = new IsPresent("test");
        assertTrue(test.evaluate(user));
    }

    @Test
    public void testIsPresent_False() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "test");

        Comparable test = new IsPresent("not there");
        assertFalse(test.evaluate(user));
    }
}
