package EthnosDivas;

import java.util.*;

public class Turne {

    private final List<Carta> cartas;
    private Carta lider;
    private final Jogador jogador;
    private boolean valida;
    private Mercado mercadoAlvo;

    private static final int[] TABELA_FAMA = {0, 0, 1, 3, 6, 10, 15};

    public Turne(List<Carta> cartas, Jogador jogador) {
        this.cartas = new ArrayList<>(cartas);
        this.jogador = jogador;
        this.valida = false;
    }


    public boolean validarMesmaDivaOuMercado() {
        if (cartas.isEmpty()) {
            valida = false;
            return false;
        }
        Diva primeiraDiva = cartas.get(0).getDiva();
        boolean mesmaDiva = cartas.stream().allMatch(c -> c.getDiva().equals(primeiraDiva));
        Mercado primeiroMercado = cartas.get(0).getMercado();
        boolean mesmoMercado = cartas.stream().allMatch(c -> c.getMercado().equals(primeiroMercado));

        valida = mesmaDiva || mesmoMercado;
        return valida;
    }

    public boolean isValida() {
        return valida;
    }

    public boolean definirLider(Carta lider) {
        if (!cartas.contains(lider)) {
            return false;
        }
        this.lider = lider;
        this.mercadoAlvo = lider.getMercado();
        return true;
    }

    public int calcularFama() {
        int tamanho = cartas.size();
        if (tamanho >= TABELA_FAMA.length) {
            return TABELA_FAMA[TABELA_FAMA.length - 1];
        }
        return TABELA_FAMA[tamanho];
    }

    public Carta getLider() {
        return lider;
    }

    public Mercado getMercadoAlvo() {
        return mercadoAlvo;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public List<Carta> getCartas() {
        return Collections.unmodifiableList(cartas);
    }

    public int getTamanho() {
        return cartas.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Turne [").append(cartas.size()).append(" cartas]");
        if (lider != null) {
            sb.append(" Lider: ").append(lider);
        }
        return sb.toString();
    }
}
