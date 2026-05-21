package EthnosDivas;

import java.util.Scanner;

public class Contexto {

    private final Jogador jogadorAtivo;
    private final Turne turneAtual;
    private final Tabuleiro tabuleiro;
    private final Baralho baralho;
    private final Jogo jogo;
    private final Scanner scanner;

    public Contexto(Jogador jogadorAtivo, Turne turneAtual, Tabuleiro tabuleiro, Baralho baralho, Jogo jogo, Scanner scanner) {
        this.jogadorAtivo = jogadorAtivo;
        this.turneAtual = turneAtual;
        this.tabuleiro = tabuleiro;
        this.baralho = baralho;
        this.jogo = jogo;
        this.scanner = scanner;
    }

    public Jogador getJogadorAtivo() {
        return jogadorAtivo;
    }

    public Turne getTurneAtual() {
        return turneAtual;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
