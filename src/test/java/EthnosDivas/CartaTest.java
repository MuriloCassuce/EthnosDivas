package EthnosDivas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartaTest {

    @Test
    void deveCriarCartaNormalCorretamente() {

        Mercado mercado = new Mercado("Pop", "Rosa");
        Diva diva = new Diva("Lady Gaga");

        Carta carta = new Carta(diva, mercado);

        assertEquals(diva, carta.getDiva());
        assertEquals(mercado, carta.getMercado());
        assertFalse(carta.ehEscandalo());
    }

    @Test
    void deveCriarCartaEscandalo() {

        Carta carta = Carta.criarEscandalo();

        assertTrue(carta.ehEscandalo());
        assertNull(carta.getDiva());
        assertNull(carta.getMercado());
    }

    @Test
    void deveRetornarToStringDaCartaNormal() {

        Mercado mercado = new Mercado("Pop", "Rosa");
        Diva diva = new Diva("Lady Gaga");

        Carta carta = new Carta(diva, mercado);

        String esperado = "[Lady Gaga | Pop (Rosa)]";

        assertEquals(esperado, carta.toString());
    }

    @Test
    void deveRetornarToStringDaCartaEscandalo() {

        Carta carta = Carta.criarEscandalo();

        assertEquals("[ESCANDALO]", carta.toString());
    }

    @Test
    void cartaNormalNaoDeveSerEscandalo() {

        Mercado mercado = new Mercado("K-pop", "Azul");
        Diva diva = new Diva("Jennie");

        Carta carta = new Carta(diva, mercado);

        assertFalse(carta.ehEscandalo());
    }

    @Test
    void cartaEscandaloDeveSerIdentificadaCorretamente() {

        Carta carta = Carta.criarEscandalo();

        assertTrue(carta.ehEscandalo());
    }
}