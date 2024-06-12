package sistema.notas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.notas.conexao.Conexao;
import sistema.notas.model.Disciplina;

public class DisciplinaDAO {
	
	public boolean adicionarDisciplina(Disciplina disciplina) {
		String sql = "INSERT INTO Disciplina (nomeDisciplina) VALUES (?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, disciplina.getNomeDisciplina());

            ps.execute();
            return true;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Código SQLState para violação de unicidade (unique mysql)
                System.out.println("Disciplina já existe: " + disciplina.getNomeDisciplina()); //TO-DO tratar erro
                return true; // Continua o processo como desejado
            } else {
                e.printStackTrace();
                return false;
            }
        }
	}
	
	//Listar todas as disciplinas cadastradas no banco de dados
	public List<Disciplina> listarDisciplinas() {
	    List<Disciplina> disciplinas = new ArrayList<>();

	    String sql = "SELECT idDisciplina, nomeDisciplina FROM Disciplina";

	    PreparedStatement ps = null;
	    
	    try {
	    	
	    	ps = Conexao.getConexao().prepareStatement(sql);
	    	ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            int idDisciplina = rs.getInt("idDisciplina");
	            String nomeDisciplina = rs.getString("nomeDisciplina");
	            Disciplina disciplina = new Disciplina(idDisciplina, nomeDisciplina);
	            disciplinas.add(disciplina);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return disciplinas;
	   
	}

	
}
