import backend.Euro;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EuroTest {

    private Euro s1;
    private final long _e1=100;
    private final int _c1=20;

    private Euro s2;
    private final long _e2=90;
    private final int _c2=99;

    private Euro s3;
    private final long _e3=101;
    private final int _c3=20020;


    @BeforeEach
    void setUp(){
        try {
            s1 = new Euro(_e1, _c1);
            s2 = new Euro(_e2, _c2);
            s3 = new Euro(_e3, _c3);
        }catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    @Test
    void testEquals() {
        try {
            assertTrue(s1.equals(new Euro(_e1,_c1)));
            assertTrue(s2.equals(new Euro(_e2,_c2)));
            assertTrue(s3.equals(new Euro(_e3,_c3)));
            assertFalse(s1.equals(s2));
            assertFalse(s1.equals(s3));
            assertFalse(s2.equals(s1));
            assertFalse(s2.equals(s3));
            assertFalse(s3.equals(s2));
            assertFalse(s3.equals(s1));
        } catch (Euro.OverflowEuroException e) {
            fail(e.getMessage());
        }
    }

   /* @Test
    void testAggiungiImporto(Euro euroDaSott){


    }*/
    @Test
    void testSottraiImporto(){

    }


    @Test
    void testCompare(){
        assertEquals(0, Euro.compare(s1, s1));
        assertEquals(1,Euro.compare(s1,s2));
        assertEquals(-1,Euro.compare(s2,s1));
    }


    @Test
    void testGetEuro(){
        assertEquals(_e3+200,s3.getEuro());
        assertEquals(_e1,s1.getEuro());
        assertEquals(_e2,s2.getEuro());

    }

    @Test
    void testGetCentesimi(){
        assertEquals(_c3%100,s3.getCentesimi());
        assertEquals(_c1,s1.getCentesimi());
        assertEquals(_c2,s2.getCentesimi());
    }

    @Test
    void testCheckOverFlow(){
        try {
            Euro e1=new Euro(Long.MAX_VALUE,101);
            fail("Exception not thrown e1");
        } catch (Exception exc) {
            assertTrue(exc instanceof Euro.OverflowEuroException);
        }
        try {
            Euro e2=new Euro(Long.MAX_VALUE-21474835,Integer.MAX_VALUE);
            fail("Exception not thrown e2");
        } catch (Exception exc) {
            assertTrue(exc instanceof Euro.OverflowEuroException);
        }

    }


}
