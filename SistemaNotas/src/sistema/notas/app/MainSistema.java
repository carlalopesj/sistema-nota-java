package sistema.notas.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//HERANÇA
//Herança - extends Application - classe principal do aplicativo JavaFX.
public class MainSistema extends Application{

	@Override
	public void start (Stage primaryStage) { //Primeiro palco
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/sistema/notas/view/TelaLogin.fxml")); //Representa o conteúdo
			Scene scene = new Scene(root); //Cria-se a cena - contêiner
			
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Tela de Login"); //Título da tela
			primaryStage.show(); //Exibindo a tela
		} catch(Exception e) { //Caso dê erro ao carregar o arquivo FXML
			e.printStackTrace(); //Auxilia na depuração
		}
	}	
	
	public static void main(String[] args) {
		launch(args); //Método da classe Application que configura e inicia a aplicação JavaFX
	}
}
