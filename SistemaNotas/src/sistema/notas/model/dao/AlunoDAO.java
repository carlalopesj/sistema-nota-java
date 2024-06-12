package sistema.notas.model.dao;

import sistema.notas.conexao.Conexao;
import sistema.notas.model.Aluno;
import sistema.notas.sessao.AlunoSessao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDAO {

	//POLIMORFISMO
	public boolean cadastrarAluno(Aluno aluno) {
		//DML - Linguagem de manipulação de dados
		String sql = "INSERT INTO Aluno (matriculaAluno, nomeAluno, emailAluno, cursoAluno, senhaAluno) VALUES (?, ?, ?, ?, ?)";
		
		//Preparando a declaração
		PreparedStatement ps = null;
		
		//Tentando adicionar o aluno
		try {
			
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setInt(1, aluno.getMatricula());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getEmail());
			ps.setString(4, aluno.getCurso());
			ps.setString(5, aluno.getSenha());
			
			ps.execute();
			ps.close();
			
			return true; //Verificação para o AlunoController
		} catch (SQLException e) {
			System.out.println("Redundância de dados");
            return false; //Verificação para o AlunoController
		}
	}
	
	//Validar login do aluno
	public boolean validarAluno(int matriculaAluno, String senhaAluno) {
		boolean validado = false;
		//Consultando o aluno com os dados informados
		String query = "SELECT * FROM Aluno WHERE matriculaAluno = ? AND senhaAluno = ?";
		
		try {
			PreparedStatement ps = Conexao.getConexao().prepareStatement(query);
			ps.setInt(1, matriculaAluno);
			ps.setString(2, senhaAluno);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				AlunoSessao.setIdAlunoLogado(matriculaAluno); //Armazena o valor da matricula e inicia a sessão
				validado = true;
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return validado; //True ou False
	}
	
}