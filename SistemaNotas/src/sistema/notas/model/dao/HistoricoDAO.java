package sistema.notas.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.notas.conexao.Conexao;
import sistema.notas.model.Historico;
import sistema.notas.sessao.AlunoSessao;

public class HistoricoDAO {

	//Listar o histórico
		public List<Historico> listarHistorico(int idAluno) {
		    List<Historico> historicos = new ArrayList<>();

		    String sql = "SELECT nomeDisciplina, notaBimestral1, notaBimestral2, mediaSemestral FROM historico_notas WHERE idAluno = ?";

		    PreparedStatement ps = null;
		    try {
		    	
		    	ps = Conexao.getConexao().prepareStatement(sql);
		    	ps.setInt(1, AlunoSessao.getIdAlunoLogado());
		    	ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            String nomeDisciplina = rs.getString("nomeDisciplina");
		            double notaBimestral1 = rs.getDouble("notaBimestral1");
		            double notaBimestral2 = rs.getDouble("notaBimestral2");
		            double mediaSemestral = rs.getDouble("mediaSemestral");
		            Historico historico = new Historico(idAluno, null, nomeDisciplina, notaBimestral1, notaBimestral2, mediaSemestral);
		            historicos.add(historico);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return historicos;
		   
		}
	
	
}
