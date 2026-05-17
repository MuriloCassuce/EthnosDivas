package EthnosDivas;

public class Carta {

    private final Mercado mercado;
    private final Diva diva;
    private final boolean ehEscandalo;

    public Carta(Diva diva, Mercado mercado) {
        this.diva = diva;
        this.mercado = mercado;
        this.ehEscandalo = false;
    }

    private Carta() {
        this.diva = null;
        this.mercado = null;
        this.ehEscandalo = true;
    }

    public static Carta criarEscandalo() {
        return new Carta();
    }

    public Mercado getMercado() {
        return mercado;
    }

    public Diva getDiva() {
        return diva;
    }

    public boolean ehEscandalo() {
        return ehEscandalo;
    }

    @Override
    public String toString() {
        if (ehEscandalo) {
            return "[ESCANDALO]";
        }
        return "[" + diva.getNome() + " | " + mercado.getNome() + " (" + mercado.getCor() + ")]";
    }
}
