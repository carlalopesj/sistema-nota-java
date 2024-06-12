package sistema.notas.model;

public class Nota {

    private double valorNota;
    private int idAvaliacao;
    private int idAluno;

    public Nota(double valorNota, int idAvaliacao, int idAluno) {
        this.valorNota = valorNota;
        this.idAvaliacao = idAvaliacao;
        this.idAluno = idAluno;
    }

    public double getValorNota() {
        return valorNota;
    }

    public void setValorNota(double valorNota) {
        this.valorNota = valorNota;
    }

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }
}
