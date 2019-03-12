package testBackend;

import backend.Magazzino;
import backend.TipoCialda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MagazzinoTest {

    private Magazzino m;
    @BeforeEach
    void setUp() {
        m=new Magazzino();
    }

    @Test
    void testGetStato() {
        Collection aux= m.getStato().values();
        Iterator<Integer> i= aux.iterator();
        while(i.hasNext()) assertEquals(i.next(), new Integer(0));
    }

    @Test
    void testAggiungiScatole(){
        m.aggiungiScatole(TipoCialda.CAFFÈ, 3);
        EnumMap aux=m.getStato();
        assertEquals(aux.get(TipoCialda.CAFFÈ), new Integer(3*50));
    }

    @Test
    void testRimuoviCialde(){
        assertFalse(m.rimuoviCialde(TipoCialda.CAFFÈ, 3));
        m.aggiungiScatole(TipoCialda.CAFFÈ, 1);
        assertTrue(m.rimuoviCialde(TipoCialda.CAFFÈ, 3));
        EnumMap aux=m.getStato();
        assertEquals(aux.get(TipoCialda.CAFFÈ), new Integer(50-3));
    }
}