package sistema.notas.model;

import sistema.notas.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDAO {

	public void cadastrarAluno(Aluno aluno) {
		//Comendo DML - Linguagem de manipulação de dados
		String sql = "INSERT INTO Aluno (matriculaAluno, nomeAluno, emailAluno, cursoAluno, senhaAluno) VALUES (?, ?, ?, ?, ?)";
		
		//Preparando a declaração
		PreparedStatement ps = null;
		
		//Tentando adicionar as linhas
		try {
			
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setInt(1, aluno.getMatricula());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getEmail());
			ps.setString(4, aluno.getCurso());
			ps.setString(5, aluno.getSenha());
			
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace(); //Mostrar o erro
		}
		
	}
	
	public boolean validarAluno(int matriculaAluno, String senhaAluno) {
		boolean validado = false;
		
		String query = "SELECT * FROM Aluno WHERE matriculaAluno = ? AND senhaAluno = ?";
		
		try {
			PreparedStatement ps = Conexao.getConexao().prepareStatement(query);
			ps.setInt(1, matriculaAluno);
			ps.setString(2, senhaAluno);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				validado = true;
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return validado;
	}
	
}