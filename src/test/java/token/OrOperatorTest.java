package token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import token.enums.TokenType;

public class OrOperatorTest {
    @Test
    public void testGetType() {
        Token orOperator = new OrOperator();
        assertEquals(TokenType.OPERATOR, orOperator.getType());
    }

    @Test
    public void testGetOrdinalValue() {
        OrOperator orToken = new OrOperator();

        assertEquals(1, orToken.getOperatorOrdinal());
    }

    @Test
    public void testBooleanCompare() throws PropertyNotFoundException {
        OrOperator orToken = new OrOperator();

        assertTrue(orToken.booleanCompare(new Literal(true), new Literal(true), null));
        assertTrue(orToken.booleanCompare(new Literal(true), new Literal(false), null));
        assertTrue(orToken.booleanCompare(new Literal(false), new Literal(true), null));
        assertFalse(orToken.booleanCompare(new Literal(false), new Literal(false), null));
    }
}
