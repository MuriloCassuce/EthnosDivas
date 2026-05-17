package EthnosDivas.divas;

import EthnosDivas.*;
import java.util.*;


public class ArianaGrande extends Diva {

    public ArianaGrande() {
        super("Ariana Grande", 12);
    }

    @Override
    public void ativarPoder(Contexto ctx) {
        Scanner scanner = new Scanner(System.in);
        Jogador jogador = ctx.getJogadorAtivo();
        Baralho baralho = ctx.getBaralho();
        List<Carta> visiveis = baralho.getCartasVisiveis();

        if (visiveis.isEmpty()) {
            System.out.println("  [Ariana Grande] Nenhuma carta visivel disponivel.");
            return;
        }

        System.out.println("  [Ariana Grande - 7 Rings] Escolha uma carta da Area Visivel:");
        for (int i = 0; i < visiveis.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + visiveis.get(i));
        }
        System.out.print("  Opcao: ");
        int escolha = scanner.nextInt() - 1;
        scanner.nextLine();

        Carta carta = baralho.comprarCartaVisivel(escolha);
        if (carta != null) {
            jogador.recrutarCarta(carta);
            System.out.println("  Carta " + carta + " adicionada a sua mao!");
        } else {
            System.out.println("  Opcao invalida. Poder nao ativado.");
        }
    }

    @Override
    public String descricaoPoder() {
        return "7 Rings - Pega uma carta da Area Visivel gratuitamente.";
    }
}
