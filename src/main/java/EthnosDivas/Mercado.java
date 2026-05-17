package EthnosDivas;

import java.util.*;


public class Mercado {

    private final String nome;
    private final String cor;
    private final int[] pontuacaoPorPosicao;
    private final Map<Jogador, Integer> contratos;

    public Mercado(String nome, String cor, int[] pontuacaoPorPosicao) {
        this.nome = nome;
        this.cor = cor;
        this.pontuacaoPorPosicao = pontuacaoPorPosicao;
        this.contratos = new LinkedHashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public int[] getPontuacaoPorPosicao() {
        return Arrays.copyOf(pontuacaoPorPosicao, pontuacaoPorPosicao.length);
    }

    public void registrarContrato(Jogador jogador) {
        contratos.merge(jogador, 1, Integer::sum);
    }

    public boolean removerContrato(Jogador jogador) {
        if (contratos.containsKey(jogador) && contratos.get(jogador) > 0) {
            int atual = contratos.get(jogador) - 1;
            if (atual <= 0) {
                contratos.remove(jogador);
            } else {
                contratos.put(jogador, atual);
            }
            return true;
        }
        return false;
    }

    public int getContratos(Jogador jogador) {
        return contratos.getOrDefault(jogador, 0);
    }


    public List<Jogador> calcularDominio() {
        List<Map.Entry<Jogador, Integer>> lista = new ArrayList<>(contratos.entrySet());
        lista.sort((a, b) -> {
            int cmp = Integer.compare(b.getValue(), a.getValue());
            if (cmp != 0) return cmp;
            if (a.getKey().isPossuiTokenBrat() && !b.getKey().isPossuiTokenBrat()) return -1;
            if (!a.getKey().isPossuiTokenBrat() && b.getKey().isPossuiTokenBrat()) return 1;
            return 0;
        });
        List<Jogador> resultado = new ArrayList<>();
        for (Map.Entry<Jogador, Integer> e : lista) {
            resultado.add(e.getKey());
        }
        return resultado;
    }

    public Jogador getPrimeiro() {
        List<Jogador> dom = calcularDominio();
        return dom.isEmpty() ? null : dom.get(0);
    }

    public Jogador getSegundo() {
        List<Jogador> dom = calcularDominio();
        return dom.size() < 2 ? null : dom.get(1);
    }

    public Jogador getTerceiro() {
        List<Jogador> dom = calcularDominio();
        return dom.size() < 3 ? null : dom.get(2);
    }

    public int getFamaTopo(int era) {
        return pontuacaoPorPosicao[0];
    }

    public void resetarContratos() {
        contratos.clear();
    }

    public boolean temContratos() {
        return !contratos.isEmpty();
    }

    public Map<Jogador, Integer> getContratosMap() {
        return Collections.unmodifiableMap(contratos);
    }

    @Override
    public String toString() {
        return nome + " (" + cor + ")";
    }
}
