package EthnosDivas.divas;

import EthnosDivas.*;


public class BillieEilish extends Diva {

    public BillieEilish() {
        super("Billie Eilish", 12);
    }

    @Override
    public void ativarPoder(Contexto ctx) {
        Jogador jogador = ctx.getJogadorAtivo();
        Baralho baralho = ctx.getBaralho();
        int escandalos = baralho.getEscandalosRevelados();
        int bonus = escandalos * 3;

        if (bonus > 0) {
            jogador.adicionarFama(bonus);
            System.out.println("  [Billie Eilish - Bad Guy] " + escandalos + " escandalo(s) revelado(s) = +" + bonus + " Fama bonus!");
        } else {
            System.out.println("  [Billie Eilish - Bad Guy] Nenhum escandalo revelado ainda. Bonus sera contabilizado ao revelar.");
        }
    }

    @Override
    public String descricaoPoder() {
        return "Bad Guy - Ganha 3 pontos de Fama por cada Escandalo revelado.";
    }
}
