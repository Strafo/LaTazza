import businessLogicLayer.Cassa;
import utils.Euro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CassaTest {

    private Euro importo;
    private Cassa cassa, cassaEuro;

    @BeforeEach
    void setUp() throws Euro.OverflowEuroException {
        cassa = new Cassa();
        importo = new Euro(200, 20);
        cassaEuro= new Cassa(importo);
    }

    @Test
    void getSaldo() {
        assertEquals(Euro.compare(importo,cassaEuro.getCopySaldo()),0);
        assertEquals(Euro.compare(cassa.getCopySaldo(),(new Euro(500,0))),0);
    }

    @Test
    void incrementaSaldo() throws Euro.OverflowEuroException {
        Euro oldEuro= cassa.getCopySaldo();
        cassa.incrementaSaldo(importo);
        assertEquals(Euro.compare(cassa.getCopySaldo(),oldEuro.aggiungiImporto(importo)),0);
    }

    @Test
    void decrementaSaldo() throws Euro.InsufficientFundsException, Euro.OverflowEuroException {
        Euro oldEuro= cassa.getCopySaldo();
        cassa.decrementaSaldo(importo);
        assertEquals(Euro.compare(cassa.getCopySaldo(),oldEuro.sottraiImporto(importo)),0);
    }

}
