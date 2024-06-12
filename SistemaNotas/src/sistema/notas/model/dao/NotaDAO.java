package sistema.notas.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import sistema.notas.conexao.Conexao;
import sistema.notas.model.Nota;

public class NotaDAO {

    public boolean cadastrarNota(Nota nota) {
        String sql = "INSERT INTO Nota (valorNota, idAvaliacao, idAluno) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setDouble(1, nota.getValorNota());
            ps.setInt(2, nota.getIdAvaliacao());
            ps.setInt(3, nota.getIdAluno());

            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
