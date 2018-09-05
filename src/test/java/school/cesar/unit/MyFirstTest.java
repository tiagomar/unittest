package school.cesar.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyFirstTest {

    private boolean isTrue = false;

    @BeforeEach
    public void setUp(){
        isTrue = true;
    }

    @Test
    public void myTest(){
        Assertions.assertTrue(isTrue);

    }


}
