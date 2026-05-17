package EthnosDivas;


public abstract class Diva {

    protected final String nome;
    protected final int qtdNoBaralho;

    protected Diva(String nome, int qtdNoBaralho) {
        this.nome = nome;
        this.qtdNoBaralho = qtdNoBaralho;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdNoBaralho() {
        return qtdNoBaralho;
    }


    public abstract void ativarPoder(Contexto ctx);

    public abstract String descricaoPoder();

    @Override
    public String toString() {
        return nome;
    }
}
