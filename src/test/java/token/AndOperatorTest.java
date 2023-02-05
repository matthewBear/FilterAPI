package token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import token.enums.TokenType;

public class AndOperatorTest {
    @Test
    public void testGetType() {
        Token andOperator = new AndOperator();
        assertEquals(TokenType.OPERATOR, andOperator.getType());
    }

    @Test
    public void testGetOrdinalValue() {
        BooleanOperator andToken = new AndOperator();

        assertEquals(2, andToken.getOperatorOrdinal());
    }

    @Test
    public void testBooleanCompare() throws PropertyNotFoundException {
        AndOperator andOperator = new AndOperator();

        assertTrue(andOperator.booleanCompare(new Literal(true), new Literal(true), null));
        assertFalse(andOperator.booleanCompare(new Literal(true), new Literal(false), null));
        assertFalse(andOperator.booleanCompare(new Literal(false), new Literal(true), null));
        assertFalse(andOperator.booleanCompare(new Literal(false), new Literal(false), null));
    }
}
