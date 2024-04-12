package fr.hetic;
import fr.hetic.Exo4.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_fil_rouge {

    @Test
    public void testAddition() {
        double result = file_op.calculate(3, 2, '+');
        assertEquals(5, result);
    }

    @Test
    public void testSubtraction() {
        double result = file_op.calculate(5, 2, '-');
        assertEquals(3, result);
    }

    @Test
    public void testMultiplication() {
        double result = file_op.calculate(4, 3, '*');
        assertEquals(12, result);
    }

    @Test
    public void testDivision() {
        double result = file_op.calculate(6, 2, '/');
        assertEquals(3, result);
    }

    @Test
    public void testInvalidOperator() {
        double result = file_op.calculate(6, 2, '$');
        assertEquals(Double.NaN, result);
    }

    @Test
    public void testInvalidInput() {
        double result = file_op.calculate(Double.NaN, 2, '*');
        assertEquals(Double.NaN, result);
    }
}

