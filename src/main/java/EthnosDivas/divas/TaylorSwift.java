package EthnosDivas.divas;


import EthnosDivas.Contexto;
import EthnosDivas.Diva;
import EthnosDivas.Jogador;
import EthnosDivas.Mercado;
import EthnosDivas.Tabuleiro;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaylorSwift extends Diva {
    public TaylorSwift() {
        super("Taylor Swift", 12);
    }

    public void ativarPoder(Contexto ctx) {
        Scanner scanner = new Scanner(System.in);
        Jogador jogador = ctx.getJogadorAtivo();
        Tabuleiro tabuleiro = ctx.getTabuleiro();
        List<Mercado> mercados = tabuleiro.getMercados();
        List<Mercado> comContrato = new ArrayList();

        for(Mercado m : mercados) {
            if (m.getContratos(jogador) > 0) {
                comContrato.add(m);
            }
        }

        if (comContrato.isEmpty()) {
            System.out.println("  [Taylor Swift] Voce nao possui contratos para mover.");
        } else {
            System.out.println("  [Taylor Swift - The Eras Tour] Escolha um Mercado para REMOVER um contrato:");

            for(int i = 0; i < comContrato.size(); ++i) {
                System.out.println("    " + (i + 1) + ". " + String.valueOf(comContrato.get(i)));
            }

            System.out.print("  Opcao: ");
            int origem = scanner.nextInt() - 1;
            scanner.nextLine();
            if (origem >= 0 && origem < comContrato.size()) {
                Mercado mercadoOrigem = (Mercado)comContrato.get(origem);
                List<Mercado> destinos = new ArrayList();

                for(Mercado m : mercados) {
                    if (!m.equals(mercadoOrigem)) {
                        destinos.add(m);
                    }
                }

                System.out.println("  Escolha o Mercado de DESTINO:");

                for(int i = 0; i < destinos.size(); ++i) {
                    System.out.println("    " + (i + 1) + ". " + String.valueOf(destinos.get(i)));
                }

                System.out.print("  Opcao: ");
                int destino = scanner.nextInt() - 1;
                scanner.nextLine();
                if (destino >= 0 && destino < destinos.size()) {
                    Mercado mercadoDestino = (Mercado)destinos.get(destino);
                    mercadoOrigem.removerContrato(jogador);
                    mercadoDestino.registrarContrato(jogador);
                    PrintStream var10000 = System.out;
                    String var10001 = String.valueOf(mercadoOrigem);
                    var10000.println("  Contrato movido de " + var10001 + " para " + String.valueOf(mercadoDestino) + "!");
                } else {
                    System.out.println("  Opcao invalida. Poder nao ativado.");
                }
            } else {
                System.out.println("  Opcao invalida. Poder nao ativado.");
            }
        }
    }

    public String descricaoPoder() {
        return "The Eras Tour - Move um contrato de um Mercado para outro.";
    }
}
