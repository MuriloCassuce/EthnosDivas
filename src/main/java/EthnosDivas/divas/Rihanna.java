package EthnosDivas.divas;

import EthnosDivas.Contexto;
import EthnosDivas.Diva;
import EthnosDivas.Jogador;

public class Rihanna extends Diva {
    public Rihanna() {
        super("Rihanna", 12);
    }

    public void ativarPoder(Contexto ctx) {
        Jogador jogador = ctx.getJogadorAtivo();
        jogador.adicionarFama(2);
        System.out.println("  [Rihanna - Fenty Empire] +2 Fama direta! (em vez de contrato)");
        ctx.getJogo().setRihannaPularContrato(true);
    }

    public String descricaoPoder() {
        return "Fenty Empire - Ganha 2 pontos de Fama diretos em vez de colocar contrato.";
    }
}