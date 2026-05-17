package EthnosDivas.divas;

import EthnosDivas.Contexto;
import EthnosDivas.Diva;
import EthnosDivas.Jogador;
import EthnosDivas.Turne;

public class OliviaRodrigo extends Diva {
    public OliviaRodrigo() {
        super("Olivia Rodrigo", 12);
    }

    public void ativarPoder(Contexto ctx) {
        Jogador jogador = ctx.getJogadorAtivo();
        Turne turne = ctx.getTurneAtual();
        if (jogador.getTurnesNaEra().size() == 1) {
            int famaExtra = turne.calcularFama();
            jogador.adicionarFama(famaExtra);
            System.out.println("  [Olivia Rodrigo - Drivers License] Primeira Turne na Era! Fama DOBRADA: +" + famaExtra + " extra!");
        } else {
            System.out.println("  [Olivia Rodrigo - Drivers License] Nao e a primeira Turne na Era. Poder nao aplicado.");
        }

    }

    public String descricaoPoder() {
        return "Drivers License - Dobra a Fama da Turne se for a primeira na Era.";
    }
}