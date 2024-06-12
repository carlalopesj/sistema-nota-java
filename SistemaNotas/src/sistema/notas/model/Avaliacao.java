package sistema.notas.model;

public class Avaliacao {
	
	private int idAvaliacao;
	private String tipoAvaliacao;
	private int semestre;
	private int bimestre;
	
	public Avaliacao(int idAvaliacao, String tipoAvaliacao, int semestre, int bimestre) {
		this.idAvaliacao = idAvaliacao;
		this.tipoAvaliacao = tipoAvaliacao;
		this.semestre = semestre;
		this.bimestre = bimestre;
	}

	public int getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(int idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	
	public String getTipoAvaliacao() {
		return tipoAvaliacao;
	}

	public void setTipoAvaliacao(String tipoAvaliacao) {
		this.tipoAvaliacao = tipoAvaliacao;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getBimestre() {
		return bimestre;
	}

	public void setBimestre(int bimestre) {
		this.bimestre = bimestre;
	}
}
