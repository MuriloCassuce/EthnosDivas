package EthnosDivas;

import java.util.*;

public class Tabuleiro {

    private final List<Mercado> mercados;

    public Tabuleiro() {
        this.mercados = new ArrayList<>();
        inicializarMercados();
    }

    private void inicializarMercados() {
        mercados.add(new Mercado("Oceania", "Rosa", new int[]{6, 4, 2}));
        mercados.add(new Mercado("Ásia", "Dourado", new int[]{8, 5, 3}));
        mercados.add(new Mercado("África", "Roxo", new int[]{5, 3, 1}));
        mercados.add(new Mercado("América", "Vermelho", new int[]{7, 4, 2}));
        mercados.add(new Mercado("Europa", "Azul", new int[]{6, 3, 1}));
        mercados.add(new Mercado("Spotify", "Verde", new int[]{7, 5, 3}));
    }

    public List<Mercado> getMercados() {
        return Collections.unmodifiableList(mercados);
    }

    public Mercado getMercado(String cor) {
        for (Mercado m : mercados) {
            if (m.getCor().equalsIgnoreCase(cor)) {
                return m;
            }
        }
        return null;
    }

    public Mercado getMercadoPorNome(String nome) {
        for (Mercado m : mercados) {
            if (m.getNome().equalsIgnoreCase(nome)) {
                return m;
            }
        }
        return null;
    }

    public void adicionarContrato(Jogador jogador, Mercado mercado) {
        mercado.registrarContrato(jogador);
    }


    public Map<Jogador, Integer> calcularPontuacaoEra(int era) {
        Map<Jogador, Integer> pontuacao = new LinkedHashMap<>();
        for (Mercado mercado : mercados) {
            if (!mercado.temContratos()) continue;
            List<Jogador> dominio = mercado.calcularDominio();
            int[] pts = mercado.getPontuacaoPorPosicao();

            if (!dominio.isEmpty()) {
                Jogador primeiro = dominio.get(0);
                pontuacao.merge(primeiro, pts[0], Integer::sum);
            }
            if (era >= 2 && dominio.size() >= 2) {
                Jogador segundo = dominio.get(1);
                pontuacao.merge(segundo, pts[1], Integer::sum);
            }
            if (era >= 3 && dominio.size() >= 3) {
                Jogador terceiro = dominio.get(2);
                pontuacao.merge(terceiro, pts[2], Integer::sum);
            }
        }
        return pontuacao;
    }

    public void resetarContratos() {
        for (Mercado m : mercados) {
            m.resetarContratos();
        }
    }

    public Mercado getMercadoVizinho(Mercado mercado) {
        int idx = mercados.indexOf(mercado);
        if (idx < 0) return null;
        return mercados.get((idx + 1) % mercados.size());
    }

    public void exibirEstado() {
        System.out.println("=== TABULEIRO - MERCADOS ===");
        for (Mercado m : mercados) {
            System.out.println("  " + m.getNome() + " (" + m.getCor() + "):");
            Map<Jogador, Integer> contratos = m.getContratosMap();
            if (contratos.isEmpty()) {
                System.out.println("    Nenhum contrato");
            } else {
                for (Map.Entry<Jogador, Integer> e : contratos.entrySet()) {
                    System.out.println("    " + e.getKey().getNome() + ": " + e.getValue() + " contrato(s)");
                }
            }
        }
    }
}
