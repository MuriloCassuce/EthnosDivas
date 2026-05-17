package EthnosDivas.divas;

import EthnosDivas.Contexto;
import EthnosDivas.Diva;

public class Lorde extends Diva {
    public Lorde() {
        super("Lorde", 12);
    }

    public void ativarPoder(Contexto ctx) {
        System.out.println("  [Lorde - Pure Heroine] O contrato sera colocado ignorando exigencia de tamanho minimo!");
        ctx.getJogo().setLordePoderAtivo(true);
    }

    public String descricaoPoder() {
        return "Pure Heroine - Coloca contrato ignorando tamanho minimo exigido.";
    }
}
