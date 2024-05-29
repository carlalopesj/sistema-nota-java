package sistema.notas.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	//Conexão com o banco de dados mySql
	private static Connection conexao = null;
	
	//Método para realizar a conexão
	public static Connection getConexao() {
	    if (conexao == null) {
	        try {
	            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_trabalho", "root", "");
	            System.out.println("Conexão estabelecida com sucesso!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Falha ao conectar ao banco de dados.");
	        }
	    }
	    return conexao;
	}
 
}
