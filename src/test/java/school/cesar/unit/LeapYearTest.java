package school.cesar.unit;

import com.sun.xml.internal.fastinfoset.algorithm.LongEncodingAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static school.cesar.unit.LeapYear.*;

public class LeapYearTest {

    private static Integer year;
    private static LeapYear leapYearStub;

    @BeforeAll
    public static void beforeAll(){
        leapYearStub = new LeapYear(){
            @Override
            public Integer getSystemCurrentYear(){
                return year;
            }
        };
    }

    @Test
    public void yearIsModFourZero (){
        year = 4;
        Assertions.assertTrue(leapYearStub.isLeap());    }

    @Test
    public void yearIsNotModFourZero (){
        year = 3;
        Assertions.assertTrue(!leapYearStub.isLeap());    }

    @Test
    public void yearIsModOneHundredZero_And_IsNotModFourHundredZero (){
        year = 100;
        Assertions.assertTrue(!leapYearStub.isLeap());    }

    @Test
    public void yearIsModOneHundredZero_And_IsModFourHundredZero (){
        year = 400;
        Assertions.assertTrue(leapYearStub.isLeap());
    }

}
