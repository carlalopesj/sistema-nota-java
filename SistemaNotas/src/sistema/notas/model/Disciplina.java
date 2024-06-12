package sistema.notas.model;

public class Disciplina {
	private int idDisciplina;
	private String nomeDisciplina;

	public Disciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	
	public Disciplina(int idDisciplina, String nomeDisciplina) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
    }

	
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public int getId() {
		return idDisciplina;
	}

	public void setId(int id) {
		this.idDisciplina = id;
	}
	
	 @Override
	 public String toString() { //Sobreescrita
	      return nomeDisciplina; //Retornar o nome de uma disciplina no comboBox
	 }
}
