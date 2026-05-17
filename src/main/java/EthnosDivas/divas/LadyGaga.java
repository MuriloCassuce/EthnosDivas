package EthnosDivas.divas;

import EthnosDivas.Contexto;
import EthnosDivas.Diva;
import EthnosDivas.Jogador;
import EthnosDivas.Mercado;
import EthnosDivas.Tabuleiro;
import EthnosDivas.Turne;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LadyGaga extends Diva {
    public LadyGaga() {
        super("Lady Gaga", 12);
    }

    public void ativarPoder(Contexto ctx) {
        Scanner scanner = new Scanner(System.in);
        Jogador jogador = ctx.getJogadorAtivo();
        Tabuleiro tabuleiro = ctx.getTabuleiro();
        Turne turne = ctx.getTurneAtual();
        Mercado mercadoAlvo = turne.getMercadoAlvo();
        List<Mercado> mercados = tabuleiro.getMercados();
        List<Mercado> vizinhos = new ArrayList();
        int idx = -1;

        for(int i = 0; i < mercados.size(); ++i) {
            if (((Mercado)mercados.get(i)).equals(mercadoAlvo)) {
                idx = i;
                break;
            }
        }

        if (idx >= 0) {
            vizinhos.add((Mercado)mercados.get((idx + 1) % mercados.size()));
            if (mercados.size() > 2) {
                vizinhos.add((Mercado)mercados.get((idx - 1 + mercados.size()) % mercados.size()));
            }
        }

        if (vizinhos.isEmpty()) {
            System.out.println("  [Lady Gaga] Nenhum Mercado vizinho disponivel.");
        } else {
            System.out.println("  [Lady Gaga - The Monster Paw] Escolha um Mercado vizinho para contrato extra:");

            for(int i = 0; i < vizinhos.size(); ++i) {
                System.out.println("    " + (i + 1) + ". " + String.valueOf(vizinhos.get(i)));
            }

            System.out.print("  Opcao: ");
            int escolha = scanner.nextInt() - 1;
            scanner.nextLine();
            if (escolha >= 0 && escolha < vizinhos.size()) {
                Mercado vizinho = (Mercado)vizinhos.get(escolha);
                tabuleiro.adicionarContrato(jogador, vizinho);
                System.out.println("  Contrato extra colocado em " + String.valueOf(vizinho) + "!");
            } else {
                System.out.println("  Opcao invalida. Poder nao ativado.");
            }
        }
    }

    public String descricaoPoder() {
        return "The Monster Paw - Coloca contrato extra em Mercado vizinho/adjacente.";
    }
}   