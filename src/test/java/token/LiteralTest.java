package token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import token.enums.TokenType;

public class LiteralTest {

    @Test
    public void testGetType() {
        Token test = new Literal(true);
        assertEquals(TokenType.COMPARABLE, test.getType());
    }

    @Test
    public void testIsPresent_True() throws PropertyNotFoundException {
        Comparable test = new Literal(true);
        assertTrue(test.evaluate(null));
    }

    @Test
    public void testIsPresent_False() throws PropertyNotFoundException {
        Map<String, String> user = new LinkedHashMap<String, String>();

        Comparable test = new Literal(false);
        assertFalse(test.evaluate(user));
    }
}
