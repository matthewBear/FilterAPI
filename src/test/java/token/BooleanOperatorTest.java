package token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import token.enums.TokenType;

public class BooleanOperatorTest {

    @Test
    public void testGetType() {
        Token orOperator = new OrOperator();
        Token andOperator = new AndOperator();
        assertEquals(TokenType.OPERATOR, orOperator.getType());
        assertEquals(TokenType.OPERATOR, andOperator.getType());
    }
    @Test
    public void testGetOrdinalValue() {
        OrOperator orToken = new OrOperator();
        AndOperator andToken = new AndOperator();

        assertEquals(1, orToken.getOperatorOrdinal());
        assertEquals(2, andToken.getOperatorOrdinal());
    }
}
