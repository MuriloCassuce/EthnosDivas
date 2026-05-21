package EthnosDivas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JogadorTest {

    private Jogador jogador;

    /*
     * Implementações fake para os testes.
     */
    private static class DivaFake extends Diva {

        public DivaFake(String nome, int qtdNoBaralho) {
            super(nome, qtdNoBaralho);
        }

        @Override
        public void ativarPoder(Contexto ctx) {
        }

        @Override
        public String descricaoPoder() {
            return "Poder fake";
        }
    }

    private static class TurneFake extends Turne {
    }

    @BeforeEach
    void setup() {

        jogador = new Jogador("Gleytton", "Azul");
    }

    @Test
    void deveCriarJogadorCorretamente() {

        assertEquals("Gleytton", jogador.getNome());
        assertEquals("Azul", jogador.getCor());
        assertEquals(0, jogador.getFama());
        assertEquals(0, jogador.tamanhoMao());
        assertFalse(jogador.isPossuiTokenBrat());
    }

    @Test
    void deveAdicionarFamaCorretamente() {

        jogador.adicionarFama(10);

        assertEquals(10, jogador.getFama());
    }

    @Test
    void deveSomarFamaCorretamente() {

        jogador.adicionarFama(5);
        jogador.adicionarFama(7);

        assertEquals(12, jogador.getFama());
    }

    @Test
    void deveRecrutarCartaCorretamente() {

        Carta carta = new Carta(
                new DivaFake("Lady Gaga", 2),
                new Mercado("Pop", "Rosa")
        );

        jogador.recrutarCarta(carta);

        assertEquals(1, jogador.tamanhoMao());
        assertTrue(jogador.getMao().contains(carta));
    }

    @Test
    void deveDescartarTodaMao() {

        Carta carta1 = new Carta(
                new DivaFake("Lady Gaga", 2),
                new Mercado("Pop", "Rosa")
        );

        Carta carta2 = new Carta(
                new DivaFake("Ariana Grande", 2),
                new Mercado("Pop", "Azul")
        );

        jogador.recrutarCarta(carta1);
        jogador.recrutarCarta(carta2);

        jogador.descartarMao();

        assertEquals(0, jogador.tamanhoMao());
        assertTrue(jogador.getMao().isEmpty());
    }

    @Test
    void deveRemoverCartasEspecificasDaMao() {

        Carta carta1 = new Carta(
                new DivaFake("Lady Gaga", 2),
                new Mercado("Pop", "Rosa")
        );

        Carta carta2 = new Carta(
                new DivaFake("Ariana Grande", 2),
                new Mercado("Pop", "Azul")
        );

        jogador.recrutarCarta(carta1);
        jogador.recrutarCarta(carta2);

        jogador.removerCartas(List.of(carta1));

        assertEquals(1, jogador.tamanhoMao());
        assertFalse(jogador.getMao().contains(carta1));
        assertTrue(jogador.getMao().contains(carta2));
    }

    @Test
    void deveRegistrarTurneCorretamente() {

        Turne turne = new TurneFake();

        jogador.registrarTurne(turne);

        assertEquals(1, jogador.getTurnesNaEra().size());
        assertTrue(jogador.getTurnesNaEra().contains(turne));
    }

    @Test
    void deveDetectarPrimeiraTurneNaEra() {

        assertTrue(jogador.possuiPrimeiraTurneNaEra());

        jogador.registrarTurne(new TurneFake());

        assertFalse(jogador.possuiPrimeiraTurneNaEra());
    }

    @Test
    void deveResetarTurnesDaEra() {

        jogador.registrarTurne(new TurneFake());
        jogador.registrarTurne(new TurneFake());

        jogador.resetarTurnesEra();

        assertTrue(jogador.getTurnesNaEra().isEmpty());
    }

    @Test
    void deveAlterarTokenBratCorretamente() {

        jogador.setPossuiTokenBrat(true);

        assertTrue(jogador.isPossuiTokenBrat());

        jogador.setPossuiTokenBrat(false);

        assertFalse(jogador.isPossuiTokenBrat());
    }

    @Test
    void listaDeMaoNaoDeveSerModificavelExternamente() {

        Carta carta = new Carta(
                new DivaFake("Lady Gaga", 2),
                new Mercado("Pop", "Rosa")
        );

        jogador.recrutarCarta(carta);

        List<Carta> mao = jogador.getMao();

        assertThrows(
                UnsupportedOperationException.class,
                () -> mao.add(carta)
        );
    }

    @Test
    void listaDeTurnesNaoDeveSerModificavelExternamente() {

        List<Turne> turnes = jogador.getTurnesNaEra();

        assertThrows(
                UnsupportedOperationException.class,
                () -> turnes.add(new TurneFake())
        );
    }

    @Test
    void deveRetornarToStringCorretamente() {

        assertEquals(
                "Gleytton (Azul)",
                jogador.toString()
        );
    }

    @Test
    void famaTotalDeveSerIgualAFama() {

        jogador.adicionarFama(15);

        assertEquals(
                jogador.getFama(),
                jogador.getFamaTotal()
        );
    }
}