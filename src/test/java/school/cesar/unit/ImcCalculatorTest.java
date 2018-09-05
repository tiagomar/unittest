package school.cesar.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static school.cesar.unit.ImcCalculator.*;

public class ImcCalculatorTest {

    private static ImcCalculator calculator;

    @BeforeAll
    public static void setUp(){
        calculator =  new ImcCalculator();
    }

    @Test
    public void normalTest(){
        Assertions.assertEquals(NORMAL, calculator.calc(1,24));
    }

    @Test
    public void sobrepesoTest(){
        Assertions.assertEquals(SOBREPESO, calculator.calc(1,25));
    }

    @Test
    public void abaixoDoPesoTest(){
        Assertions.assertEquals(ABAIXO_DO_PESO, calculator.calc(1,17));
    }

    @Test
    public void obesidade(){
        Assertions.assertEquals(OBESIDADE, calculator.calc(1,30));
    }

    @Test
    public void obesidadeMorbida(){
        Assertions.assertEquals(OBESIDADE_MORBIDA, calculator.calc(1,40));
    }

    @Test
    public void desnutricao(){
        Assertions.assertEquals(DESNUTRICAO, calculator.calc(1,16));
    }


}
