package sistema.notas.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.notas.conexao.Conexao;
import sistema.notas.model.Avaliacao;

public class AvaliacaoDAO {
	
	//Método para buscar no banco de dados uma avaliação
	public Avaliacao buscarAvaliacao(int idDisciplina, String tipoAvaliacao, int semestre, int bimestre) {
	    String sql = "SELECT * FROM Avaliacao WHERE idDisciplina = ? AND tipoAvaliacao = ? AND semestre = ? AND bimestre = ?";

	    PreparedStatement ps = null;
	    
	    try {
	    	ps = Conexao.getConexao().prepareStatement(sql);
	        ps.setInt(1, idDisciplina);
	        ps.setString(2, tipoAvaliacao);
	        ps.setInt(3, semestre);
	        ps.setInt(4, bimestre);

	        try (ResultSet rs = ps.executeQuery()) { //garante que o ResultSet seja fechado automaticamente quando o bloco for concluído
                if (rs.next()) {
                    // Recuperando o ID da avaliação, será utilizado para cadastrar uma nota
                    int idAvaliacao = rs.getInt("idAvaliacao");
                    //System.out.println("ID avaliacao" + idAvaliacao);
                    // Recuperando os outros dados da avaliação
                    String tipoAvaliacaoBusca = rs.getString("tipoAvaliacao");
                    int semestreBusca = rs.getInt("semestre");
                    int bimestreBusca = rs.getInt("bimestre");

                    // Criando e retornando um objeto Avaliacao com os dados recuperados, incluindo o ID
                    return new Avaliacao(idAvaliacao, tipoAvaliacaoBusca, semestreBusca, bimestreBusca);
                } else {
                    // Se não houver avaliação encontrada, retornar null
                    return null;
                }
            }
	    } catch (SQLException e) {
	        // Lidando com exceções de SQL
	        e.printStackTrace();
	        return null;
	    }
	}

	//Método para listar os tipos de avaliações existentes (será usado no ComboBox)
	public List<String> listarTipoAvaliacao() {
	    List<String> tipoAvaliacoes = new ArrayList<>();

	    //DISTINCT para evitar duplicações
	    String sql = "SELECT DISTINCT tipoAvaliacao FROM Avaliacao";

	    PreparedStatement ps = null;
	    
	    try {
	    	ps = Conexao.getConexao().prepareStatement(sql);
	    	ResultSet rs = ps.executeQuery();
	    	
	        while (rs.next()) {
	            String tipoAvaliacao = rs.getString("tipoAvaliacao");
	            //System.out.println("Tipo de Avaliação: " + tipoAvaliacao); 
	            tipoAvaliacoes.add(tipoAvaliacao);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tipoAvaliacoes;
	}

	//Método para listar os semestres existentes (será usado no ComboBox)
	public List<Integer> listarSemestre() {
	    List<Integer> semestres = new ArrayList<>();

	    String sql = "SELECT DISTINCT semestre FROM Avaliacao";

	    PreparedStatement ps = null;
	    
	    try {
	    	
	    	ps = Conexao.getConexao().prepareStatement(sql);
	    	ResultSet rs = ps.executeQuery();
	    	while (rs.next()) {
                semestres.add(rs.getInt("semestre"));
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return semestres;
	} 
	
}
