package EthnosDivas.divas;

import EthnosDivas.Contexto;
import EthnosDivas.Diva;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Madonna extends Diva {
    public Madonna() {
        super("Madonna", 12);
    }

    public void ativarPoder(Contexto ctx) {
        Scanner scanner = ctx.getScanner();
        List<Diva> divasAtivas = ctx.getJogo().getDivasAtivas();
        List<Diva> opcoes = new ArrayList();

        for(Diva d : divasAtivas) {
            if (!(d instanceof Madonna)) {
                opcoes.add(d);
            }
        }

        if (opcoes.isEmpty()) {
            System.out.println("  [Madonna] Nenhuma outra Diva ativa para copiar.");
        } else {
            System.out.println("  [Madonna - Reinvencao] Escolha uma Diva para copiar o poder:");

            for(int i = 0; i < opcoes.size(); ++i) {
                System.out.println("    " + (i + 1) + ". " + ((Diva)opcoes.get(i)).getNome() + " - " + ((Diva)opcoes.get(i)).descricaoPoder());
            }

            System.out.print("  Opcao: ");
            int escolha = scanner.nextInt() - 1;
            scanner.nextLine();
            if (escolha >= 0 && escolha < opcoes.size()) {
                Diva copiada = (Diva)opcoes.get(escolha);
                System.out.println("  Copiando poder de " + copiada.getNome() + "...");
                copiada.ativarPoder(ctx);
            } else {
                System.out.println("  Opcao invalida. Poder nao ativado.");
            }
        }
    }

    public String descricaoPoder() {
        return "Reinvencao - Copia o poder de qualquer outra Diva ativa.";
    }
}
