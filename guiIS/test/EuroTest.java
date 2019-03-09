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
        sum=new Euro(_e1,_c1);
    }

    @Test
    void testEquals() {
    }

    @Test
    void testAggiungiImporto(Euro euroDaSott){


    }
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
            Euro e=new Euro(Long.MAX_VALUE,101);
        } catch (Euro.OverflowEuroException e1) {
            
        }


    }

    @Test
    void testInsufficentFund(){

    }

}
