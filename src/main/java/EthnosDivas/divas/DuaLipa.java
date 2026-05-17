package EthnosDivas.divas;

import EthnosDivas.*;
import java.util.*;


public class DuaLipa extends Diva {

    public DuaLipa() {
        super("Dua Lipa", 12);
    }

    @Override
    public void ativarPoder(Contexto ctx) {
        Scanner scanner = new Scanner(System.in);
        Jogador jogador = ctx.getJogadorAtivo();
        Tabuleiro tabuleiro = ctx.getTabuleiro();
        List<Mercado> mercados = tabuleiro.getMercados();

        List<Mercado> mercadosComAdversarios = new ArrayList<>();
        for (Mercado m : mercados) {
            Map<Jogador, Integer> contratos = m.getContratosMap();
            for (Map.Entry<Jogador, Integer> e : contratos.entrySet()) {
                if (!e.getKey().equals(jogador) && e.getValue() > 0) {
                    mercadosComAdversarios.add(m);
                    break;
                }
            }
        }

        if (mercadosComAdversarios.isEmpty()) {
            System.out.println("  [Dua Lipa] Nenhum adversario possui contratos em nenhum Mercado.");
            return;
        }

        System.out.println("  [Dua Lipa - Houdini] Escolha um Mercado para remover contrato de adversario:");
        for (int i = 0; i < mercadosComAdversarios.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + mercadosComAdversarios.get(i));
        }
        System.out.print("  Opcao: ");
        int mIdx = scanner.nextInt() - 1;
        scanner.nextLine();

        if (mIdx < 0 || mIdx >= mercadosComAdversarios.size()) {
            System.out.println("  Opcao invalida. Poder nao ativado.");
            return;
        }
        Mercado mercadoAlvo = mercadosComAdversarios.get(mIdx);

        Map<Jogador, Integer> contratos = mercadoAlvo.getContratosMap();
        List<Jogador> adversarios = new ArrayList<>();
        for (Map.Entry<Jogador, Integer> e : contratos.entrySet()) {
            if (!e.getKey().equals(jogador) && e.getValue() > 0) {
                adversarios.add(e.getKey());
            }
        }

        System.out.println("  Escolha o adversario:");
        for (int i = 0; i < adversarios.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + adversarios.get(i) + " (" + mercadoAlvo.getContratos(adversarios.get(i)) + " contrato(s))");
        }
        System.out.print("  Opcao: ");
        int aIdx = scanner.nextInt() - 1;
        scanner.nextLine();

        if (aIdx < 0 || aIdx >= adversarios.size()) {
            System.out.println("  Opcao invalida. Poder nao ativado.");
            return;
        }

        Jogador alvo = adversarios.get(aIdx);
        mercadoAlvo.removerContrato(alvo);
        System.out.println("  Contrato de " + alvo.getNome() + " removido do Mercado " + mercadoAlvo + "!");
    }

    @Override
    public String descricaoPoder() {
        return "Houdini - Remove um contrato de adversario de qualquer Mercado.";
    }
}
