package sistema.notas.model;

public class Historico {
    private int idAluno;
    private String nomeAluno;
    private String nomeDisciplina;
    private double notaBimestral1;
    private double notaBimestral2;
    private double mediaSemestral;
    private double notaRecuperacao;
    private double mediaFinal;

    // Construtor de histórico
    public Historico(int idAluno, String nomeAluno, String nomeDisciplina, double notaBimestral1, double notaBimestral2, double mediaSemestral, double notaRecuperacao, double mediaFinal) {
        this.idAluno = idAluno;
        this.nomeAluno = nomeAluno;
        this.nomeDisciplina = nomeDisciplina;
        this.notaBimestral1 = notaBimestral1;
        this.notaBimestral2 = notaBimestral2;
        this.mediaSemestral = mediaSemestral;
        this.notaRecuperacao = notaRecuperacao;
        this.mediaFinal = mediaFinal;
    }

    //Métodos Get e Set
    public int getIdAluno() {
        return idAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public double getNotaBimestral1() {
        return notaBimestral1;
    }

    public double getNotaBimestral2() {
        return notaBimestral2;
    }

    public double getMediaSemestral() {
        return mediaSemestral;
    }
    
    public double getNotaRecuperacao() {
        return notaRecuperacao;
    }

    public void setNotaRecuperacao(double notaRecuperacao) {
        this.notaRecuperacao = notaRecuperacao;
    }

    public double getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }
}
