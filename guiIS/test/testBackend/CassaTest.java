package testBackend;
import backend.businessLogicLayer.Cassa;
import utils.Euro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CassaTest {

    private Euro e;
    private Cassa c, c1;

    @BeforeEach
    void setUp() throws Euro.OverflowEuroException {
        c = new Cassa();
        e = new Euro(200, 20);
        c1 = new Cassa(e);
    }

    @Test
    void getSaldo() {
        assertEquals(c.getCopySaldo().getEuro(), 500);
        assertEquals(Euro.compare(c1.getCopySaldo(), e), 0);
    }

    @Test
    void incrementaSaldo() throws Euro.OverflowEuroException {
        Cassa c2 = new Cassa();
        c2.incrementaSaldo(e);
        Euro e1 = new Euro(700, 20);
        assertEquals(Euro.compare(c2.getCopySaldo(), e1), 0);
    }

    @Test
    void decrementaSaldo() throws Euro.InsufficientFundsException, Euro.OverflowEuroException {
        Cassa c2 = new Cassa();
        c2.decrementaSaldo(e);
        Euro e1 = new Euro(299, 80);
        assertEquals(Euro.compare(c2.getCopySaldo(), e1), 0);
    }

}
