import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void hasInvalidSequenceTest() {
		assertFalse(InfixExpressionValidator.hasInvalidSequence("3 + 5"));
		assertTrue(InfixExpressionValidator.hasInvalidSequence("3 + + 5"));
		assertFalse(InfixExpressionValidator.hasInvalidSequence("3 - - 5"));
		assertTrue(InfixExpressionValidator.hasInvalidSequence("3 + + + 5"));
		assertTrue(InfixExpressionValidator.hasInvalidSequence("3 + * 5"));
		assertTrue(InfixExpressionValidator.hasInvalidSequence("3 + 5 / * 2"));
	}
	
	@Test
	void hasMissingOperatorTest() {
		assertTrue(InfixExpressionValidator.hasMissingOperator("3 512"));
		assertTrue(InfixExpressionValidator.hasMissingOperator("3 + 61 5"));
		assertTrue(InfixExpressionValidator.hasMissingOperator("3 + 61 523 1"));
		assertFalse(InfixExpressionValidator.hasMissingOperator("-3 + 52"));
	}

	@Test
	void replaceUnaryMinusTest() {
		assertEquals(InfixExpressionValidator.replaceUnaryMinus("-3 - 6"),"@3-6");
		assertEquals(InfixExpressionValidator.replaceUnaryMinus("-3 + -6"),"@3+@6");
		assertEquals(InfixExpressionValidator.replaceUnaryMinus("-3 + (-6 * 2)"),"@3+(@6*2)");
		assertEquals(InfixExpressionValidator.replaceUnaryMinus("-3 + (-3)"),"@3+(@3)");
	}
}
