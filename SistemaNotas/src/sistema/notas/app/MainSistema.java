package sistema.notas.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainSistema extends Application{

	@Override
	public void start (Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/sistema/notas/view/TelaLogin.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Tela de Login");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}
