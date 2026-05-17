package EthnosDivas;

import java.util.*;


public class Jogador {

    private final String nome;
    private final String cor;
    private int fama;
    private final List<Carta> mao;
    private final List<Turne> turnesNaEra;
    private boolean possuiTokenBrat;

    public Jogador(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
        this.fama = 0;
        this.mao = new ArrayList<>();
        this.turnesNaEra = new ArrayList<>();
        this.possuiTokenBrat = false;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public int getFama() {
        return fama;
    }

    public int getFamaTotal() {
        return fama;
    }

    public void listarCartas() {
        if (mao.isEmpty()) {
            System.out.println("  (mao vazia)");
            return;
        }
        for (int i = 0; i < mao.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + mao.get(i));
        }
    }

    public void recrutarCarta(Carta carta) {
        mao.add(carta);
    }

    public void registrarTurne(Turne turne) {
        turnesNaEra.add(turne);
    }

    public void descartarMao() {
        mao.clear();
    }

    public void adicionarFama(int pontos) {
        this.fama += pontos;
    }

    public void removerCartas(List<Carta> cartas) {
        for (Carta c : cartas) {
            mao.remove(c);
        }
    }

    public boolean possuiPrimeiraTurneNaEra() {
        return turnesNaEra.isEmpty();
    }

    public void resetarTurnesEra() {
        turnesNaEra.clear();
    }

    public List<Carta> getMao() {
        return Collections.unmodifiableList(mao);
    }

    public List<Turne> getTurnesNaEra() {
        return Collections.unmodifiableList(turnesNaEra);
    }

    public boolean isPossuiTokenBrat() {
        return possuiTokenBrat;
    }

    public void setPossuiTokenBrat(boolean possuiTokenBrat) {
        this.possuiTokenBrat = possuiTokenBrat;
    }

    public int tamanhoMao() {
        return mao.size();
    }

    @Override
    public String toString() {
        return nome + " (" + cor + ")";
    }
}
