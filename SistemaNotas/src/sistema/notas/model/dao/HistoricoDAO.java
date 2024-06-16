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

	// Listar o histórico
	public List<Historico> listarHistorico(int idAluno) {
		List<Historico> historicos = new ArrayList<>();

		String sql = "SELECT nomeDisciplina, notaBimestral1, notaBimestral2, mediaSemestral, notaRecuperacao, mediaFinal FROM notas_historico WHERE idAluno = ?";

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
				double notaRecuperacao = rs.getDouble("notaRecuperacao");
				double mediaFinal = rs.getDouble("mediaFinal");
				Historico historico = new Historico(idAluno, null, nomeDisciplina, notaBimestral1, notaBimestral2,
						mediaSemestral, notaRecuperacao, mediaFinal);
				historicos.add(historico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return historicos;
	}

	// Método para adicionar uma nota de recuperação por meio de um histórico
	public boolean adicionarNotaRecuperacao(Historico historico) {
		String sql = "INSERT INTO Nota_recuperacao (idAluno, nomeDisciplina, notaRec) VALUES (?, ?, ?)";

		PreparedStatement ps = null;
		try {

			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setInt(1, historico.getIdAluno());
			ps.setString(2, historico.getNomeDisciplina());
			ps.setDouble(3, historico.getNotaRecuperacao());
			ps.execute();
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
