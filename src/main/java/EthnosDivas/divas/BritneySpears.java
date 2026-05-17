package EthnosDivas.divas;

import EthnosDivas.*;


public class BritneySpears extends Diva {

    public BritneySpears() {
        super("Britney Spears", 12);
    }

    @Override
    public void ativarPoder(Contexto ctx) {
        Jogador jogador = ctx.getJogadorAtivo();
        Turne turne = ctx.getTurneAtual();
        Baralho baralho = ctx.getBaralho();

        int alvo = turne.getTamanho();
        int compradas = 0;
        System.out.println("  [Britney Spears - The Comeback] Comprando cartas ate ter " + alvo + " na mao...");

        while (jogador.tamanhoMao() < alvo && !baralho.isVazio() && !baralho.isEraEncerrada()) {
            Carta carta = baralho.comprar();
            if (carta != null) {
                jogador.recrutarCarta(carta);
                compradas++;
            }
        }
        System.out.println("  " + compradas + " carta(s) comprada(s). Mao agora tem " + jogador.tamanhoMao() + " carta(s).");
    }

    @Override
    public String descricaoPoder() {
        return "The Comeback - Compra cartas ate ter o mesmo numero que o tamanho da Turne.";
    }
}
