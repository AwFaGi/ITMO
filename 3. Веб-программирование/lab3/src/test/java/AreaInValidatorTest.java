import org.example.beans.ExtendedPoint;
import org.example.util.AreaInValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AreaInValidatorTest {

    private static ExtendedPoint extendedPoint1;
    private static ExtendedPoint extendedPoint2;
    private static ExtendedPoint extendedPoint3;
    private static ExtendedPoint extendedPoint4;

    @BeforeAll
    public static void createExtendedPoints(){
        extendedPoint1 = new ExtendedPoint();
        extendedPoint1.setX(-1);
        extendedPoint1.setY(1);
        extendedPoint1.setR(2);

        extendedPoint2 = new ExtendedPoint();
        extendedPoint2.setX(2);
        extendedPoint2.setY(2);
        extendedPoint2.setR(2);

        extendedPoint3 = new ExtendedPoint();
        extendedPoint3.setX(-1);
        extendedPoint3.setY(-1);
        extendedPoint3.setR(2);

        extendedPoint4 = new ExtendedPoint();
        extendedPoint4.setX(1);
        extendedPoint4.setY(-1);
        extendedPoint4.setR(3);
    }

    @Test
    public void validateTest(){

        assertTrue(AreaInValidator.validate(extendedPoint1));
        assertFalse(AreaInValidator.validate(extendedPoint2));
        assertFalse(AreaInValidator.validate(extendedPoint3));
        assertTrue(AreaInValidator.validate(extendedPoint4));

    }
}
