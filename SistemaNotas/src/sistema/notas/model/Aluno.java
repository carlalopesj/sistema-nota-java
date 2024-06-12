package sistema.notas.model;
//ABSTRAÇÃO

public class Aluno {
	
	//ENCAPSULAMENTO
	//Atributos de Aluno
	private int matricula;
	private String nome;
	private String email;
	private String curso;
	private String senha;
	
	//Classe Construtora
	public Aluno (int matricula, String nome, String email, String curso, String senha) {
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.curso = curso;
		this.senha = senha;
	}
	
	//Métodos Get e Set
	public int getMatricula() {
		return matricula;
	}
	
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCurso() {
		return curso;
	}
	
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
