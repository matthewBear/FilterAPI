package token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import token.enums.TokenType;

public class MatchesRegexTest {

    @Test
    public void testGetType() {
        Token test = new MatchesRegex("test", "test");
        assertEquals(TokenType.COMPARABLE, test.getType());
    }

    @Test
    public void testMatchesRegex_True() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "bbb");

        Comparable test = new MatchesRegex("test", ".bb");
        assertTrue(test.evaluate(user));
    }

    @Test
    public void testMatchesRegex_False() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("test", "bbb");

        Comparable test = new MatchesRegex("test", ".aa");
        assertFalse(test.evaluate(user));
    }

    @Test
    public void testMatchesRegex_PropertyDoesntExist() {
        Map<String, String> user = new LinkedHashMap<String, String>();

        Comparable test = new MatchesRegex("not there", "nope");
        assertThrows(PropertyNotFoundException.class, () -> test.evaluate(user));
    }
}
