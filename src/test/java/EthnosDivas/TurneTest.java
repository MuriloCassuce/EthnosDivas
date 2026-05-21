package EthnosDivas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TurneTest {

    private Jogador jogador;

    private Mercado mercadoPop;
    private Mercado mercadoRock;

    private Carta carta1;
    private Carta carta2;
    private Carta carta3;

    /*
     * Classe fake para testes.
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

    @BeforeEach
    void setup() {

        jogador = new Jogador("Gleytton", "Azul");

        mercadoPop = new Mercado(
                "Pop",
                "Rosa",
                new int[]{10, 5, 2}
        );

        mercadoRock = new Mercado(
                "Rock",
                "Preto",
                new int[]{8, 4, 1}
        );

        Diva diva1 = new DivaFake("Lady Gaga", 3);
        Diva diva2 = new DivaFake("Ariana Grande", 2);

        carta1 = new Carta(diva1, mercadoPop);
        carta2 = new Carta(diva1, mercadoRock);
        carta3 = new Carta(diva2, mercadoPop);
    }

    @Test
    void deveCriarTurneCorretamente() {

        Turne turne = new Turne(
                List.of(carta1, carta2),
                jogador
        );

        assertEquals(jogador, turne.getJogador());
        assertEquals(2, turne.getTamanho());
        assertFalse(turne.isValida());
    }

    @Test
    void deveValidarTurneComMesmaDiva() {

        Turne turne = new Turne(
                List.of(carta1, carta2),
                jogador
        );

        boolean resultado =
                turne.validarMesmaDivaOuMercado();

        assertTrue(resultado);
        assertTrue(turne.isValida());
    }

    @Test
    void deveValidarTurneComMesmoMercado() {

        Turne turne = new Turne(
                List.of(carta1, carta3),
                jogador
        );

        boolean resultado =
                turne.validarMesmaDivaOuMercado();

        assertTrue(resultado);
        assertTrue(turne.isValida());
    }

    @Test
    void naoDeveValidarTurneInvalida() {

        Turne turne = new Turne(
                List.of(carta2, carta3),
                jogador
        );

        boolean resultado =
                turne.validarMesmaDivaOuMercado();

        assertFalse(resultado);
        assertFalse(turne.isValida());
    }

    @Test
    void naoDeveValidarTurneSemCartas() {

        Turne turne = new Turne(
                List.of(),
                jogador
        );

        boolean resultado =
                turne.validarMesmaDivaOuMercado();

        assertFalse(resultado);
        assertFalse(turne.isValida());
    }

    @Test
    void deveDefinirLiderCorretamente() {

        Turne turne = new Turne(
                List.of(carta1, carta2),
                jogador
        );

        boolean resultado =
                turne.definirLider(carta1);

        assertTrue(resultado);
        assertEquals(carta1, turne.getLider());
        assertEquals(
                mercadoPop,
                turne.getMercadoAlvo()
        );
    }

    @Test
    void naoDeveDefinirLiderQueNaoPertenceATurne() {

        Carta cartaExterna = new Carta(
                new DivaFake("Beyonce", 2),
                mercadoPop
        );

        Turne turne = new Turne(
                List.of(carta1, carta2),
                jogador
        );

        boolean resultado =
                turne.definirLider(cartaExterna);

        assertFalse(resultado);
        assertNull(turne.getLider());
    }

    @Test
    void deveCalcularFamaCorretamente() {

        Turne turne2 = new Turne(
                List.of(carta1, carta2),
                jogador
        );

        Turne turne4 = new Turne(
                List.of(carta1, carta2, carta3, carta1),
                jogador
        );

        assertEquals(1, turne2.calcularFama());
        assertEquals(6, turne4.calcularFama());
    }

    @Test
    void deveLimitarFamaAoValorMaximoDaTabela() {

        Turne turne = new Turne(
                List.of(
                        carta1,
                        carta2,
                        carta3,
                        carta1,
                        carta2,
                        carta3,
                        carta1,
                        carta2
                ),
                jogador
        );

        assertEquals(15, turne.calcularFama());
    }

    @Test
    void listaDeCartasNaoDeveSerModificavelExternamente() {

        Turne turne = new Turne(
                List.of(carta1, carta2),
                jogador
        );

        List<Carta> cartas = turne.getCartas();

        assertThrows(
                UnsupportedOperationException.class,
                () -> cartas.add(carta3)
        );
    }

    @Test
    void deveRetornarTamanhoCorretamente() {

        Turne turne = new Turne(
                List.of(carta1, carta2, carta3),
                jogador
        );

        assertEquals(3, turne.getTamanho());
    }

    @Test
    void deveRetornarToStringSemLider() {

        Turne turne = new Turne(
                List.of(carta1, carta2),
                jogador
        );

        assertEquals(
                "Turne [2 cartas]",
                turne.toString()
        );
    }

    @Test
    void deveRetornarToStringComLider() {

        Turne turne = new Turne(
                List.of(carta1, carta2),
                jogador
        );

        turne.definirLider(carta1);

        assertEquals(
                "Turne [2 cartas] Lider: " + carta1,
                turne.toString()
        );
    }
}
