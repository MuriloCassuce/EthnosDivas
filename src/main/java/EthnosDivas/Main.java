package EthnosDivas;

import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jogo jogo = new Jogo(scanner);
        jogo.iniciar();
        scanner.close();
    }
}
