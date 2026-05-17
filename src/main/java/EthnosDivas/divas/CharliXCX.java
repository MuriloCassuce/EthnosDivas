package EthnosDivas.divas;

import EthnosDivas.*;


public class CharliXCX extends Diva {

    public CharliXCX() {
        super("Charli XCX", 12);
    }

    @Override
    public void ativarPoder(Contexto ctx) {
        Jogador jogador = ctx.getJogadorAtivo();
        jogador.setPossuiTokenBrat(true);
        System.out.println("  [Charli XCX - Brat Summer] " + jogador.getNome() + " recebeu o token Brat! Vitoria automatica em empates de Mercado.");
    }

    @Override
    public String descricaoPoder() {
        return "Brat Summer - Token que garante vitoria em empates de controle de Mercado.";
    }
}
