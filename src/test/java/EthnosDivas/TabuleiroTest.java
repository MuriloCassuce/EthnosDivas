package EthnosDivas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TabuleiroTest {

    private Tabuleiro tabuleiro;

    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogador3;

    @BeforeEach
    void setup() {

        tabuleiro = new Tabuleiro();

        jogador1 = new Jogador("Gleytton", "Azul");
        jogador2 = new Jogador("Maria", "Vermelho");
        jogador3 = new Jogador("Joao", "Verde");
    }

    @Test
    void deveInicializarTodosOsMercados() {

        List<Mercado> mercados = tabuleiro.getMercados();

        assertEquals(6, mercados.size());
    }

    @Test
    void listaDeMercadosNaoDeveSerModificavelExternamente() {

        List<Mercado> mercados = tabuleiro.getMercados();

        assertThrows(
                UnsupportedOperationException.class,
                () -> mercados.add(
                        new Mercado(
                                "Teste",
                                "Cinza",
                                new int[]{1, 1, 1}
                        )
                )
        );
    }

    @Test
    void deveBuscarMercadoPorCor() {

        Mercado mercado = tabuleiro.getMercado("Azul");

        assertNotNull(mercado);
        assertEquals("Europa", mercado.getNome());
    }

    @Test
    void deveBuscarMercadoPorCorIgnorandoMaiusculasEMinusculas() {

        Mercado mercado = tabuleiro.getMercado("azUl");

        assertNotNull(mercado);
        assertEquals("Europa", mercado.getNome());
    }

    @Test
    void deveRetornarNullAoBuscarCorInexistente() {

        Mercado mercado = tabuleiro.getMercado("Preto");

        assertNull(mercado);
    }

    @Test
    void deveBuscarMercadoPorNome() {

        Mercado mercado = tabuleiro.getMercadoPorNome("Spotify");

        assertNotNull(mercado);
        assertEquals("Verde", mercado.getCor());
    }

    @Test
    void deveBuscarMercadoPorNomeIgnorandoMaiusculasEMinusculas() {

        Mercado mercado = tabuleiro.getMercadoPorNome("spOtIfY");

        assertNotNull(mercado);
        assertEquals("Verde", mercado.getCor());
    }

    @Test
    void deveRetornarNullAoBuscarNomeInexistente() {

        Mercado mercado = tabuleiro.getMercadoPorNome("TikTok");

        assertNull(mercado);
    }

    @Test
    void deveAdicionarContratoCorretamente() {

        Mercado mercado = tabuleiro.getMercado("Azul");

        tabuleiro.adicionarContrato(jogador1, mercado);

        assertEquals(
                1,
                mercado.getContratos(jogador1)
        );
    }

    @Test
    void deveCalcularPontuacaoDaEra1() {

        Mercado europa = tabuleiro.getMercado("Azul");

        europa.registrarContrato(jogador1);
        europa.registrarContrato(jogador1);

        europa.registrarContrato(jogador2);

        Map<Jogador, Integer> pontuacao =
                tabuleiro.calcularPontuacaoEra(1);

        assertEquals(
                6,
                pontuacao.get(jogador1)
        );

        assertNull(pontuacao.get(jogador2));
    }

    @Test
    void deveCalcularPontuacaoDaEra2() {

        Mercado europa = tabuleiro.getMercado("Azul");

        europa.registrarContrato(jogador1);
        europa.registrarContrato(jogador1);

        europa.registrarContrato(jogador2);

        Map<Jogador, Integer> pontuacao =
                tabuleiro.calcularPontuacaoEra(2);

        assertEquals(
                6,
                pontuacao.get(jogador1)
        );

        assertEquals(
                3,
                pontuacao.get(jogador2)
        );
    }

    @Test
    void deveCalcularPontuacaoDaEra3() {

        Mercado spotify = tabuleiro.getMercado("Verde");

        spotify.registrarContrato(jogador1);
        spotify.registrarContrato(jogador1);
        spotify.registrarContrato(jogador1);

        spotify.registrarContrato(jogador2);
        spotify.registrarContrato(jogador2);

        spotify.registrarContrato(jogador3);

        Map<Jogador, Integer> pontuacao =
                tabuleiro.calcularPontuacaoEra(3);

        assertEquals(
                7,
                pontuacao.get(jogador1)
        );

        assertEquals(
                5,
                pontuacao.get(jogador2)
        );

        assertEquals(
                3,
                pontuacao.get(jogador3)
        );
    }

    @Test
    void naoDevePontuarMercadosSemContratos() {

        Map<Jogador, Integer> pontuacao =
                tabuleiro.calcularPontuacaoEra(3);

        assertTrue(pontuacao.isEmpty());
    }

    @Test
    void deveResetarTodosOsContratos() {

        Mercado europa = tabuleiro.getMercado("Azul");
        Mercado spotify = tabuleiro.getMercado("Verde");

        europa.registrarContrato(jogador1);
        spotify.registrarContrato(jogador2);

        tabuleiro.resetarContratos();

        assertFalse(europa.temContratos());
        assertFalse(spotify.temContratos());
    }

    @Test
    void deveRetornarMercadoVizinhoCorretamente() {

        Mercado europa = tabuleiro.getMercadoPorNome("Europa");

        Mercado vizinho =
                tabuleiro.getMercadoVizinho(europa);

        assertNotNull(vizinho);
        assertEquals("Spotify", vizinho.getNome());
    }

    @Test
    void deveRetornarPrimeiroMercadoComoVizinhoDoUltimo() {

        Mercado spotify = tabuleiro.getMercadoPorNome("Spotify");

        Mercado vizinho =
                tabuleiro.getMercadoVizinho(spotify);

        assertNotNull(vizinho);
        assertEquals("Oceania", vizinho.getNome());
    }

    @Test
    void deveRetornarNullParaMercadoInexistente() {

        Mercado mercadoFake = new Mercado(
                "Fake",
                "Cinza",
                new int[]{1, 1, 1}
        );

        Mercado vizinho =
                tabuleiro.getMercadoVizinho(mercadoFake);

        assertNull(vizinho);
    }

    @Test
    void exibirEstadoNaoDeveLancarExcecao() {

        assertDoesNotThrow(() -> tabuleiro.exibirEstado());
    }
}