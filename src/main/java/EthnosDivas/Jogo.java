package EthnosDivas;

import EthnosDivas.divas.*;
import java.util.*;


public class Jogo {

    private int eraAtual;
    private EstadoJogo estado;
    private final List<Jogador> jogadores;
    private int indiceJogadorAtual;
    private Jogador jogadorAtual;
    private Baralho baralho;
    private final Tabuleiro tabuleiro;
    private final List<Diva> todasDivas;
    private List<Diva> divasAtivas;

    private boolean surpriseDropAtivo;
    private boolean lordePoderAtivo;
    private boolean rihannaPularContrato;

    private final Scanner scanner;

    private static final int TOTAL_ERAS = 3;
    private static final int DIVAS_ATIVAS = 6;
    private static final int CARTAS_INICIAIS = 1;
    private static final int NUM_ESCANDALOS = 3;

    private final Map<Jogador, int[]> pontuacaoPorEra;

    public Jogo(Scanner scanner) {
        this.scanner = scanner;
        this.jogadores = new ArrayList<>();
        this.tabuleiro = new Tabuleiro();
        this.todasDivas = criarTodasDivas();
        this.pontuacaoPorEra = new LinkedHashMap<>();
        this.estado = EstadoJogo.CADASTRO;
        this.eraAtual = 0;
    }

    private List<Diva> criarTodasDivas() {
        List<Diva> divas = new ArrayList<>();
        divas.add(new TaylorSwift());
        divas.add(new Beyonce());
        divas.add(new BritneySpears());
        divas.add(new ArianaGrande());
        divas.add(new DuaLipa());
        divas.add(new BillieEilish());
        divas.add(new Madonna());
        divas.add(new Lorde());
        divas.add(new CharliXCX());
        divas.add(new LadyGaga());
        divas.add(new Rihanna());
        divas.add(new OliviaRodrigo());
        return divas;
    }


    public void iniciar() {
        System.out.println("========================================");
        System.out.println("        POP DIVAS - O JOGO");
        System.out.println("========================================");
        System.out.println();

        cadastrarJogadores();
        sortearDivasAtivas();
        exibirDivasAtivas();

        for (Jogador j : jogadores) {
            pontuacaoPorEra.put(j, new int[TOTAL_ERAS]);
        }

        for (eraAtual = 1; eraAtual <= TOTAL_ERAS; eraAtual++) {
            iniciarEra();
            loopDeJogo();
            finalizarEra();
            if (eraAtual < TOTAL_ERAS) {
                System.out.println("\nPressione ENTER para iniciar a Era " + (eraAtual + 1) + "...");
                scanner.nextLine();
            }
        }

        exibirRankingFinal();
        estado = EstadoJogo.JOGO_ENCERRADO;
    }

    private void cadastrarJogadores() {
        System.out.print("Quantos jogadores (2-6)? ");
        int numJogadores = lerInteiro(2, 6);

        String[] coresDisponiveis = {"Branco", "Preto", "Amarelo", "Laranja", "Cinza", "Marrom"};

        for (int i = 0; i < numJogadores; i++) {
            System.out.print("Nome do Jogador " + (i + 1) + ": ");
            String nome = scanner.nextLine().trim();
            while (nome.isEmpty()) {
                System.out.print("Nome nao pode ser vazio. Digite novamente: ");
                nome = scanner.nextLine().trim();
            }
            System.out.println("Cores disponiveis:");
            List<String> coresLivres = new ArrayList<>();
            for (String cor : coresDisponiveis) {
                boolean usada = false;
                for (Jogador j : jogadores) {
                    if (j.getCor().equalsIgnoreCase(cor)) {
                        usada = true;
                        break;
                    }
                }
                if (!usada) coresLivres.add(cor);
            }
            for (int c = 0; c < coresLivres.size(); c++) {
                System.out.println("  " + (c + 1) + ". " + coresLivres.get(c));
            }
            System.out.print("Escolha a cor: ");
            int corIdx = lerInteiro(1, coresLivres.size()) - 1;
            String cor = coresLivres.get(corIdx);
            jogadores.add(new Jogador(nome, cor));
            System.out.println("Jogador " + nome + " (" + cor + ") cadastrado!\n");
        }
    }

    private void sortearDivasAtivas() {
        List<Diva> copia = new ArrayList<>(todasDivas);
        Collections.shuffle(copia);
        divasAtivas = new ArrayList<>(copia.subList(0, DIVAS_ATIVAS));
    }

    private void exibirDivasAtivas() {
        System.out.println("=== DIVAS ATIVAS NESTA PARTIDA ===");
        for (Diva d : divasAtivas) {
            System.out.println("  * " + d.getNome() + " - " + d.descricaoPoder());
        }
        System.out.println();
    }


    private void iniciarEra() {
        System.out.println("\n========================================");
        System.out.println("           ERA " + eraAtual + " DE " + TOTAL_ERAS);
        System.out.println("========================================\n");

        estado = EstadoJogo.EM_ANDAMENTO;
        montarBaralho();
        baralho.embaralhar();
        baralho.preencherCartasVisiveis();

        tabuleiro.resetarContratos();
        for (Jogador j : jogadores) {
            j.resetarTurnesEra();
            j.descartarMao();
            j.setPossuiTokenBrat(false);
        }

        distribuirCartasIniciais();

        indiceJogadorAtual = 0;
        jogadorAtual = jogadores.get(0);
    }

    private void montarBaralho() {
        baralho = new Baralho();
        List<Mercado> mercados = tabuleiro.getMercados();

        for (Diva diva : divasAtivas) {
            int cartasPorDiva = diva.getQtdNoBaralho();
            int mercadoIdx = 0;
            for (int i = 0; i < cartasPorDiva; i++) {
                Mercado mercado = mercados.get(mercadoIdx % mercados.size());
                baralho.adicionarCarta(new Carta(diva, mercado));
                mercadoIdx++;
            }
        }

        for (int i = 0; i < NUM_ESCANDALOS; i++) {
            baralho.adicionarCarta(Carta.criarEscandalo());
        }

        baralho.embaralhar();
        System.out.println("Baralho montado com " + baralho.tamanho() + " cartas (incluindo " + NUM_ESCANDALOS + " Escandalos).\n");
    }

    private void distribuirCartasIniciais() {
        for (Jogador j : jogadores) {
            for (int i = 0; i < CARTAS_INICIAIS; i++) {
                Carta c = baralho.comprar();
                if (c != null) {
                    j.recrutarCarta(c);
                }
            }
        }
    }


    private void loopDeJogo() {
        while (!baralho.isEraEncerrada() && estado == EstadoJogo.EM_ANDAMENTO) {
            jogadorAtual = jogadores.get(indiceJogadorAtual);
            surpriseDropAtivo = false;
            lordePoderAtivo = false;
            rihannaPularContrato = false;

            executarTurno();

            if (baralho.isEraEncerrada()) {
                break;
            }

            if (surpriseDropAtivo) {
                System.out.println("\n  [Surprise Drop] Voce pode lancar uma segunda Turne!");
                surpriseDropAtivo = false;
                lordePoderAtivo = false;
                rihannaPularContrato = false;
                executarAcaoTurne();
                if (baralho.isEraEncerrada()) break;
            }

            proximoTurno();
        }
    }

    private void executarTurno() {
        System.out.println("\n----------------------------------------");
        System.out.println("Turno de: " + jogadorAtual + " | Era: " + eraAtual + " | Fama: " + jogadorAtual.getFama());
        System.out.println("Baralho: " + baralho.tamanho() + " cartas | Escandalos: " + baralho.getEscandalosRevelados() + "/3");
        System.out.println("----------------------------------------");

        System.out.println("\nSua mao:");
        jogadorAtual.listarCartas();

        List<Carta> visiveis = baralho.getCartasVisiveis();
        if (!visiveis.isEmpty()) {
            System.out.println("\nArea Visivel:");
            for (int i = 0; i < visiveis.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + visiveis.get(i));
            }
        }

        System.out.println("\nAcoes:");
        System.out.println("  1. Recrutar carta (comprar do baralho)");
        System.out.println("  2. Recrutar carta (pegar da Area Visivel)");
        System.out.println("  3. Lancar Turne");
        System.out.println("  4. Ver Tabuleiro");
        System.out.print("Escolha: ");

        int acao = lerInteiro(1, 4);
        switch (acao) {
            case 1:
                recrutarCartaBaralho();
                break;
            case 2:
                recrutarCartaVisivel();
                break;
            case 3:
                executarAcaoTurne();
                break;
            case 4:
                tabuleiro.exibirEstado();
                executarTurno();
                break;
        }
    }


    private void recrutarCartaBaralho() {
        if (baralho.isVazio()) {
            System.out.println("  Baralho vazio! Nao e possivel comprar.");
            return;
        }
        Carta carta = baralho.comprar();
        if (carta != null) {
            jogadorAtual.recrutarCarta(carta);
            System.out.println("  Voce recrutou: " + carta);
        } else {
            System.out.println("  Voce revelou um Escandalo! Nenhuma carta recrutada.");
        }
    }

    private void recrutarCartaVisivel() {
        List<Carta> visiveis = baralho.getCartasVisiveis();
        if (visiveis.isEmpty()) {
            System.out.println("  Area Visivel vazia! Nao ha cartas para pegar.");
            return;
        }
        System.out.println("  Cartas na Area Visivel:");
        for (int i = 0; i < visiveis.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + visiveis.get(i));
        }
        System.out.print("  Escolha (0 para cancelar): ");
        int escolha = lerInteiro(0, visiveis.size());
        if (escolha == 0) {
            executarTurno();
            return;
        }
        Carta carta = baralho.comprarCartaVisivel(escolha - 1);
        if (carta != null) {
            jogadorAtual.recrutarCarta(carta);
            System.out.println("  Voce recrutou: " + carta);
        } else {
            System.out.println("  Carta invalida!");
        }
    }

    private void executarAcaoTurne() {
        List<Carta> mao = jogadorAtual.getMao();
        if (mao.isEmpty()) {
            System.out.println("  Sua mao esta vazia! Nao e possivel lancar Turne.");
            return;
        }

        System.out.println("\n  Sua mao:");
        for (int i = 0; i < mao.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + mao.get(i));
        }
        System.out.println("  Digite os numeros das cartas para a Turne (separados por espaco):");
        System.out.println("  (ou 0 para cancelar)");
        System.out.print("  > ");
        String linha = scanner.nextLine().trim();

        if (linha.equals("0")) return;

        String[] partes = linha.split("\\s+");
        List<Carta> cartasSelecionadas = new ArrayList<>();
        Set<Integer> indicesUsados = new HashSet<>();
        boolean invalido = false;

        for (String parte : partes) {
            try {
                int idx = Integer.parseInt(parte) - 1;
                if (idx < 0 || idx >= mao.size() || indicesUsados.contains(idx)) {
                    invalido = true;
                    break;
                }
                indicesUsados.add(idx);
                cartasSelecionadas.add(mao.get(idx));
            } catch (NumberFormatException e) {
                invalido = true;
                break;
            }
        }

        if (invalido || cartasSelecionadas.isEmpty()) {
            System.out.println("  Selecao invalida!");
            return;
        }

        lancarTurne(cartasSelecionadas);
    }

    public void lancarTurne(List<Carta> cartas) {
        Turne turne = new Turne(cartas, jogadorAtual);

        if (!turne.validarMesmaDivaOuMercado()) {
            System.out.println("  ERRO: As cartas nao sao todas da mesma Diva nem do mesmo Mercado!");
            return;
        }

        Carta lider = escolherLider(cartas);
        if (lider == null) return;
        turne.definirLider(lider);

        Mercado mercadoAlvo = turne.getMercadoAlvo();
        int contratosAdversarios = maiorContratoAdversario(mercadoAlvo, jogadorAtual);
        boolean podeColocarContrato = lordePoderAtivo || turne.getTamanho() > contratosAdversarios;

        int fama = turne.calcularFama();
        jogadorAtual.adicionarFama(fama);
        jogadorAtual.registrarTurne(turne);
        System.out.println("  Turne lancada! +" + fama + " Fama (total: " + jogadorAtual.getFama() + ")");

        Diva divaLider = lider.getDiva();
        System.out.println("  Ativando poder de " + divaLider.getNome() + "...");
        Contexto ctx = new Contexto(jogadorAtual, turne, tabuleiro, baralho, this, scanner);
        divaLider.ativarPoder(ctx);

        if (!rihannaPularContrato) {
            if (podeColocarContrato) {
                tabuleiro.adicionarContrato(jogadorAtual, mercadoAlvo);
                System.out.println("  Contrato colocado em " + mercadoAlvo + "!");
            } else {
                System.out.println("  Turne muito pequena para colocar contrato em " + mercadoAlvo
                        + " (precisa de mais de " + contratosAdversarios + " cartas).");
            }
        }

        jogadorAtual.removerCartas(cartas);
        for (Carta c : cartas) {
            baralho.adicionarCartaVisivel(c);
        }

        if (baralho.isEraEncerrada()) {
            estado = EstadoJogo.ERA_FINALIZADA;
        }
    }

    private Carta escolherLider(List<Carta> cartas) {
        if (cartas.size() == 1) {
            return cartas.get(0);
        }
        System.out.println("  Escolha a carta Lider da Turne:");
        for (int i = 0; i < cartas.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + cartas.get(i));
        }
        System.out.print("  Lider: ");
        int idx = lerInteiro(1, cartas.size()) - 1;
        return cartas.get(idx);
    }

    private int maiorContratoAdversario(Mercado mercado, Jogador jogador) {
        int max = 0;
        Map<Jogador, Integer> contratos = mercado.getContratosMap();
        for (Map.Entry<Jogador, Integer> e : contratos.entrySet()) {
            if (!e.getKey().equals(jogador) && e.getValue() > max) {
                max = e.getValue();
            }
        }
        return max;
    }


    private void finalizarEra() {
        System.out.println("\n========================================");
        System.out.println("        FIM DA ERA " + eraAtual);
        System.out.println("========================================\n");

        Map<Jogador, Integer> pontuacaoMercados = tabuleiro.calcularPontuacaoEra(eraAtual);
        System.out.println("Pontuacao de Mercados na Era " + eraAtual + ":");
        String regra;
        if (eraAtual == 1) regra = "(apenas 1o colocado pontua)";
        else if (eraAtual == 2) regra = "(1o e 2o colocados pontuam)";
        else regra = "(1o, 2o e 3o colocados pontuam)";
        System.out.println(regra);

        tabuleiro.exibirEstado();

        for (Jogador j : jogadores) {
            int pontosEra = pontuacaoMercados.getOrDefault(j, 0);
            j.adicionarFama(pontosEra);
            pontuacaoPorEra.get(j)[eraAtual - 1] = pontosEra;
            System.out.println("  " + j.getNome() + ": +" + pontosEra + " Fama de Mercados (Total: " + j.getFama() + ")");
        }

        estado = EstadoJogo.ERA_FINALIZADA;
    }

    private void proximoTurno() {
        indiceJogadorAtual = (indiceJogadorAtual + 1) % jogadores.size();
    }


    private void exibirRankingFinal() {
        System.out.println("\n========================================");
        System.out.println("        RANKING FINAL - POP DIVAS");
        System.out.println("========================================\n");

        List<Jogador> ranking = new ArrayList<>(jogadores);
        ranking.sort((a, b) -> Integer.compare(b.getFama(), a.getFama()));

        System.out.printf("%-4s %-20s", "Pos", "Jogador");
        for (int e = 1; e <= TOTAL_ERAS; e++) {
            System.out.printf(" %-10s", "Era " + e);
        }
        System.out.printf(" %-10s%n", "TOTAL");

        System.out.println("-".repeat(70));

        int pos = 1;
        for (Jogador j : ranking) {
            int[] eras = pontuacaoPorEra.get(j);
            System.out.printf("%-4d %-20s", pos, j.getNome() + " (" + j.getCor() + ")");
            for (int e = 0; e < TOTAL_ERAS; e++) {
                System.out.printf(" %-10d", eras[e]);
            }
            System.out.printf(" %-10d%n", j.getFama());
            pos++;
        }

        System.out.println();
        Jogador vencedor = ranking.get(0);
        System.out.println("*** VENCEDOR: " + vencedor.getNome() + " com " + vencedor.getFama() + " pontos de Fama! ***");
    }


    public Jogador getJogadorAtual() {
        return jogadorAtual;
    }

    public List<Diva> getDivasAtivas() {
        return Collections.unmodifiableList(divasAtivas);
    }

    public void setSurpriseDropAtivo(boolean ativo) {
        this.surpriseDropAtivo = ativo;
    }

    public void setLordePoderAtivo(boolean ativo) {
        this.lordePoderAtivo = ativo;
    }

    public void setRihannaPularContrato(boolean pular) {
        this.rihannaPularContrato = pular;
    }

    public int getEraAtual() {
        return eraAtual;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public void exibirEstado() {
        System.out.println("Era: " + eraAtual + " | Estado: " + estado);
        for (Jogador j : jogadores) {
            System.out.println("  " + j + " | Fama: " + j.getFama() + " | Cartas: " + j.tamanhoMao());
        }
    }


    private int lerInteiro(int min, int max) {
        while (true) {
            try {
                String linha = scanner.nextLine().trim();
                int valor = Integer.parseInt(linha);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.print("  Valor invalido (" + min + "-" + max + "): ");
            } catch (NumberFormatException e) {
                System.out.print("  Digite um numero (" + min + "-" + max + "): ");
            }
        }
    }
}
