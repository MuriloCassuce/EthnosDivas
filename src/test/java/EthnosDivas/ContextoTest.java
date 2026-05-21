package EthnosDivas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContextoTest {

    private Jogador jogador;
    private Turne turne;
    private Tabuleiro tabuleiro;
    private Baralho baralho;
    private Jogo jogo;

    private Contexto contexto;

    @BeforeEach
    void setup() {

        jogador = new Jogador();
        turne = new Turne();
        tabuleiro = new Tabuleiro();
        baralho = new Baralho();
        jogo = new Jogo();

        contexto = new Contexto(
                jogador,
                turne,
                tabuleiro,
                baralho,
                jogo
        );
    }

    @Test
    void deveRetornarJogadorAtivoCorretamente() {

        assertEquals(jogador, contexto.getJogadorAtivo());
    }

    @Test
    void deveRetornarTurneAtualCorretamente() {

        assertEquals(turne, contexto.getTurneAtual());
    }

    @Test
    void deveRetornarTabuleiroCorretamente() {

        assertEquals(tabuleiro, contexto.getTabuleiro());
    }

    @Test
    void deveRetornarBaralhoCorretamente() {

        assertEquals(baralho, contexto.getBaralho());
    }

    @Test
    void deveRetornarJogoCorretamente() {

        assertEquals(jogo, contexto.getJogo());
    }

    @Test
    void naoDeveRetornarObjetosNulos() {

        assertNotNull(contexto.getJogadorAtivo());
        assertNotNull(contexto.getTurneAtual());
        assertNotNull(contexto.getTabuleiro());
        assertNotNull(contexto.getBaralho());
        assertNotNull(contexto.getJogo());
    }

    @Test
    void deveManterAsMesmasReferenciasPassadasNoConstrutor() {

        assertSame(jogador, contexto.getJogadorAtivo());
        assertSame(turne, contexto.getTurneAtual());
        assertSame(tabuleiro, contexto.getTabuleiro());
        assertSame(baralho, contexto.getBaralho());
        assertSame(jogo, contexto.getJogo());
    }
}