package EthnosDivas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DivaTest {

    private Diva diva;

    /*
     * Classe fake utilizada apenas para testes,
     * já que Diva é abstrata.
     */
    private static class DivaFake extends Diva {

        public DivaFake(String nome, int qtdNoBaralho) {
            super(nome, qtdNoBaralho);
        }

        @Override
        public void ativarPoder(Contexto ctx) {
            // implementação vazia para teste
        }

        @Override
        public String descricaoPoder() {
            return "Poder de teste";
        }
    }

    @BeforeEach
    void setup() {

        diva = new DivaFake("Lady Gaga", 3);
    }

    @Test
    void deveRetornarNomeCorretamente() {

        assertEquals("Lady Gaga", diva.getNome());
    }

    @Test
    void deveRetornarQuantidadeNoBaralhoCorretamente() {

        assertEquals(3, diva.getQtdNoBaralho());
    }

    @Test
    void deveRetornarDescricaoDoPoder() {

        assertEquals("Poder de teste", diva.descricaoPoder());
    }

    @Test
    void deveExecutarAtivarPoderSemExcecao() {

        assertDoesNotThrow(() -> diva.ativarPoder(null));
    }

    @Test
    void deveRetornarNomeNoToString() {

        assertEquals("Lady Gaga", diva.toString());
    }

    @Test
    void deveManterNomeImutavel() {

        assertSame(diva.getNome(), diva.toString());
    }

    @Test
    void deveCriarDivaComValoresValidos() {

        assertNotNull(diva);
        assertNotNull(diva.getNome());
        assertTrue(diva.getQtdNoBaralho() > 0);
    }
}