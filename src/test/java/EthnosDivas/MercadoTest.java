package EthnosDivas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MercadoTest {

    private Mercado mercado;

    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogador3;

    @BeforeEach
    void setup() {

        mercado = new Mercado(
                "Pop",
                "Rosa",
                new int[]{10, 6, 3}
        );

        jogador1 = new Jogador("Gleytton", "Azul");
        jogador2 = new Jogador("Maria", "Vermelho");
        jogador3 = new Jogador("Joao", "Verde");
    }

    @Test
    void deveCriarMercadoCorretamente() {

        assertEquals("Pop", mercado.getNome());
        assertEquals("Rosa", mercado.getCor());

        assertArrayEquals(
                new int[]{10, 6, 3},
                mercado.getPontuacaoPorPosicao()
        );
    }

    @Test
    void deveRetornarCopiaDaPontuacao() {

        int[] pontuacao = mercado.getPontuacaoPorPosicao();

        pontuacao[0] = 999;

        assertNotEquals(
                999,
                mercado.getPontuacaoPorPosicao()[0]
        );
    }

    @Test
    void deveRegistrarContratoCorretamente() {

        mercado.registrarContrato(jogador1);

        assertEquals(
                1,
                mercado.getContratos(jogador1)
        );
    }

    @Test
    void deveSomarContratosDoMesmoJogador() {

        mercado.registrarContrato(jogador1);
        mercado.registrarContrato(jogador1);

        assertEquals(
                2,
                mercado.getContratos(jogador1)
        );
    }

    @Test
    void deveRemoverContratoCorretamente() {

        mercado.registrarContrato(jogador1);
        mercado.registrarContrato(jogador1);

        boolean removido = mercado.removerContrato(jogador1);

        assertTrue(removido);
        assertEquals(
                1,
                mercado.getContratos(jogador1)
        );
    }

    @Test
    void deveRemoverJogadorQuandoContratosZerarem() {

        mercado.registrarContrato(jogador1);

        mercado.removerContrato(jogador1);

        assertEquals(
                0,
                mercado.getContratos(jogador1)
        );

        assertFalse(
                mercado.getContratosMap().containsKey(jogador1)
        );
    }

    @Test
    void naoDeveRemoverContratoInexistente() {

        boolean removido = mercado.removerContrato(jogador1);

        assertFalse(removido);
    }

    @Test
    void deveCalcularDominioCorretamente() {

        mercado.registrarContrato(jogador1);
        mercado.registrarContrato(jogador1);

        mercado.registrarContrato(jogador2);

        mercado.registrarContrato(jogador3);
        mercado.registrarContrato(jogador3);
        mercado.registrarContrato(jogador3);

        List<Jogador> dominio = mercado.calcularDominio();

        assertEquals(jogador3, dominio.get(0));
        assertEquals(jogador1, dominio.get(1));
        assertEquals(jogador2, dominio.get(2));
    }

    @Test
    void deveUsarTokenBratComoDesempate() {

        mercado.registrarContrato(jogador1);
        mercado.registrarContrato(jogador2);

        jogador2.setPossuiTokenBrat(true);

        List<Jogador> dominio = mercado.calcularDominio();

        assertEquals(jogador2, dominio.get(0));
        assertEquals(jogador1, dominio.get(1));
    }

    @Test
    void deveRetornarPrimeiroSegundoETerceiro() {

        mercado.registrarContrato(jogador1);

        mercado.registrarContrato(jogador2);
        mercado.registrarContrato(jogador2);

        mercado.registrarContrato(jogador3);
        mercado.registrarContrato(jogador3);
        mercado.registrarContrato(jogador3);

        assertEquals(jogador3, mercado.getPrimeiro());
        assertEquals(jogador2, mercado.getSegundo());
        assertEquals(jogador1, mercado.getTerceiro());
    }

    @Test
    void deveRetornarNullQuandoNaoHouverPosicoes() {

        assertNull(mercado.getPrimeiro());
        assertNull(mercado.getSegundo());
        assertNull(mercado.getTerceiro());
    }

    @Test
    void deveRetornarFamaDoTopo() {

        assertEquals(
                10,
                mercado.getFamaTopo(1)
        );
    }

    @Test
    void deveResetarContratos() {

        mercado.registrarContrato(jogador1);
        mercado.registrarContrato(jogador2);

        mercado.resetarContratos();

        assertTrue(
                mercado.getContratosMap().isEmpty()
        );
    }

    @Test
    void deveVerificarSeTemContratos() {

        assertFalse(mercado.temContratos());

        mercado.registrarContrato(jogador1);

        assertTrue(mercado.temContratos());
    }

    @Test
    void mapaDeContratosNaoDeveSerModificavelExternamente() {

        mercado.registrarContrato(jogador1);

        Map<Jogador, Integer> mapa = mercado.getContratosMap();

        assertThrows(
                UnsupportedOperationException.class,
                () -> mapa.put(jogador2, 5)
        );
    }

    @Test
    void deveRetornarToStringCorretamente() {

        assertEquals(
                "Pop (Rosa)",
                mercado.toString()
        );
    }
}
