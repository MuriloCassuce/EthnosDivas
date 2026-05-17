package EthnosDivas.divas;

import EthnosDivas.*;


public class Beyonce extends Diva {

    public Beyonce() {
        super("Beyonce", 12);
    }

    @Override
    public void ativarPoder(Contexto ctx) {
        System.out.println("  [Beyonce - Surprise Drop] Voce pode lancar uma segunda Turne neste turno!");
        ctx.getJogo().setSurpriseDropAtivo(true);
    }

    @Override
    public String descricaoPoder() {
        return "Surprise Drop - Permite lancar uma segunda Turne no mesmo turno.";
    }
}
