package init.test;

import init.Calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTests {

    @Test
    void additionTest() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.add(1,1), "The output should be the sum of the two arguments");
    }
    
    @Test
    void soustractionTest() {
        Calculator calc = new Calculator();
        assertEquals(0, calc.soustract(1,1), "The output should be the sum of the two arguments");
    }
    
    @Test
    void failSoustractionTest() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.soustract(1,1), "The output should be the sum of the two arguments");
    }
}
