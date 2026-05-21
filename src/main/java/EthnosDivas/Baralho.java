package EthnosDivas;

import java.util.*;


public class Baralho {

    private final List<Carta> cartas;
    private final List<Carta> cartasVisiveis;
    private int escandalosRevelados;
    private static final int MAX_VISIVEIS = 3;

    public Baralho() {
        this.cartas = new ArrayList<>();
        this.cartasVisiveis = new ArrayList<>();
        this.escandalosRevelados = 0;
    }

    public void adicionarCarta(Carta carta) {
        cartas.add(carta);
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }


    public Carta comprar() {
        if (cartas.isEmpty()) {
            return null;
        }
        Carta carta = cartas.remove(0);
        if (carta.ehEscandalo()) {
            revelarEscandalo();
            return null;
        }
        return carta;
    }

    private void revelarEscandalo() {
        escandalosRevelados++;
        System.out.println("  *** ESCANDALO REVELADO! (" + escandalosRevelados + "/3) ***");
        if (escandalosRevelados >= 3) {
            System.out.println("  *** TERCEIRO ESCANDALO! A ERA SERA ENCERRADA! ***");
        }
    }

    public boolean isEraEncerrada() {
        return escandalosRevelados >= 3;
    }

    public int getEscandalosRevelados() {
        return escandalosRevelados;
    }

    public List<Carta> getCartasVisiveis() {
        return Collections.unmodifiableList(cartasVisiveis);
    }

    public Carta comprarCartaVisivel(int indice) {
        if (indice < 0 || indice >= cartasVisiveis.size()) {
            return null;
        }
        Carta carta = cartasVisiveis.remove(indice);
        reporCartasVisiveis();
        return carta;
    }

    public void adicionarCartaVisivel(Carta carta) {
        if (!carta.ehEscandalo()) {
            cartasVisiveis.add(carta);
        }
    }

    private void reporCartasVisiveis() {
        while (cartasVisiveis.size() < MAX_VISIVEIS && !cartas.isEmpty()) {
            Carta carta = cartas.remove(0);
            if (carta.ehEscandalo()) {
                revelarEscandalo();
            } else {
            cartasVisiveis.add(carta);
            }
        }
    }

    public void preencherCartasVisiveis() {
        cartasVisiveis.clear();
        reporCartasVisiveis();
    }

    /** Retorna true se o baralho está vazio. */
    public boolean isVazio() {
        return cartas.isEmpty();
    }

    /** Retorna o tamanho do baralho. */
    public int tamanho() {
        return cartas.size();
    }
}
