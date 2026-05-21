package EthnosDivas;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class BaralhoTest {


    private Baralho baralho;

    @BeforeEach
    void setup() {
        baralho = new Baralho();
    }

    @Test
    void deveAdicionarCartaAoBaralho() {

        Carta carta = Carta.criarEscandalo();

        baralho.adicionarCarta(carta);

        assertEquals(1, baralho.tamanho());
    }

    @Test
    void deveRetornarTrueQuandoBaralhoEstiverVazio() {

        assertTrue(baralho.isVazio());
    }

    @Test
    void deveRetornarFalseQuandoBaralhoNaoEstiverVazio() {

        Carta carta = Carta.criarEscandalo();

        baralho.adicionarCarta(carta);

        assertFalse(baralho.isVazio());
    }

    @Test
    void deveComprarCartaNormal() {

        Diva diva = new Diva("Anitta");
        Mercado mercado = new Mercado("Brasil", "Verde");

        Carta carta = new Carta(diva, mercado);

        baralho.adicionarCarta(carta);

        Carta resultado = baralho.comprar();

        Assertions.assertEquals(carta, resultado);
        assertEquals(0, baralho.tamanho());
    }

    @Test
    void deveRetornarNullQuandoComprarEscandalo() {

        Carta escandalo = Carta.criarEscandalo();

        baralho.adicionarCarta(escandalo);

        Carta resultado = baralho.comprar();

        assertNull(resultado);
    }

    @Test
    void deveIncrementarEscandalosRevelados() {

        Carta escandalo = Carta.criarEscandalo();

        baralho.adicionarCarta(escandalo);

        baralho.comprar();

        assertEquals(1, baralho.getEscandalosRevelados());
    }

    @Test
    void deveEncerrarEraQuandoTresEscandalosForemRevelados() {

        for (int i = 0; i < 3; i++) {
            baralho.adicionarCarta(Carta.criarEscandalo());
        }

        baralho.comprar();
        baralho.comprar();
        baralho.comprar();

        assertTrue(baralho.isEraEncerrada());
    }

    @Test
    void naoDeveEncerrarEraComMenosDeTresEscandalos() {

        for (int i = 0; i < 2; i++) {
            baralho.adicionarCarta(Carta.criarEscandalo());
        }

        baralho.comprar();
        baralho.comprar();

        assertFalse(baralho.isEraEncerrada());
    }

    @Test
    void deveRetornarNullAoComprarBaralhoVazio() {

        Carta resultado = baralho.comprar();

        assertNull(resultado);
    }

    @Test
    void deveAdicionarCartaVisivelSomenteSeNaoForEscandalo() {

        Diva diva = new Diva("Anitta");
        Mercado mercado = new Mercado("Brasil", "Verde");

        Carta carta = new Carta(diva, mercado);

        baralho.adicionarCartaVisivel(carta);

        assertEquals(1, baralho.getCartasVisiveis().size());
    }

    @Test
    void naoDeveAdicionarEscandaloComoCartaVisivel() {

        Carta escandalo = Carta.criarEscandalo();

        baralho.adicionarCartaVisivel(escandalo);

        assertEquals(0, baralho.getCartasVisiveis().size());
    }

    @Test
    void deveComprarCartaVisivelPorIndice() {

        Diva diva = new Diva("Anitta");
        Mercado mercado = new Mercado("Brasil", "Verde");

        Carta carta = new Carta(diva, mercado);

        baralho.adicionarCartaVisivel(carta);

        Carta resultado = baralho.comprarCartaVisivel(0);

        Assertions.assertEquals(carta, resultado);
    }

    @Test
    void deveRetornarNullQuandoIndiceForInvalido() {

        Carta resultado = baralho.comprarCartaVisivel(10);

        assertNull(resultado);
    }

    @Test
    void listaCartasVisiveisDeveSerImutavel() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> baralho.getCartasVisiveis().add(Carta.criarEscandalo())
        );
    }

    @Test
    void deveLimparCartasVisiveis() {

        Diva diva = new Diva("Anitta");
        Mercado mercado = new Mercado("Brasil", "Verde");

        Carta carta = new Carta(diva, mercado);

        baralho.adicionarCartaVisivel(carta);

        baralho.preencherCartasVisiveis();

        assertEquals(0, baralho.getCartasVisiveis().size());
    }
}
