package sistema.notas.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistema.notas.model.Aluno;
import sistema.notas.model.AlunoDAO;

public class AlunoController {
	
	//Parte do Cadastro
	@FXML
	private TextField tfMatricula;
	@FXML
	private TextField tfNome;
	@FXML
	private TextField tfEmail;
	@FXML
	private TextField tfCurso;
	@FXML
	private TextField tfSenha;
	@FXML
	private Label lStatusCad;
	
	@FXML
	private void cadastrarAluno() {
		System.out.println("Botão cadastrar foi clicado");
		String matriculaStr = tfMatricula.getText();
		String nome = tfNome.getText();
		String email = tfEmail.getText();
		String curso = tfCurso.getText();
		String senha = tfSenha.getText();
		
		if (matriculaStr.isEmpty() || nome.isEmpty()|| email.isEmpty() || curso.isEmpty() || senha.isEmpty()) {
			lStatusCad.setText("Por favor, preencha todos os campos.");
	        return;
	    } else {
	    	int matricula = Integer.parseInt(matriculaStr);
	    	
	    	Aluno aluno = new Aluno(matricula, nome, email,curso, senha);
			AlunoDAO alunoDAO = new AlunoDAO();
			alunoDAO.cadastrarAluno(aluno);
			
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/sistema/notas/view/TelaLogin.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				//Fechar a tela atual
				Stage StageAtual = (Stage) tfMatricula.getScene().getWindow();
	            StageAtual.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
	
	//Parte do Login
	@FXML
	private TextField tfMatriculaLogin;
	@FXML
	private TextField tfSenhaLogin;
	@FXML 
	private Label lStatusLogin;
	
	@FXML
	private void loginAluno() {
	    System.out.println("Botão entrar foi clicado");
	    String matriculaStr = tfMatriculaLogin.getText();
	    String senha = tfSenhaLogin.getText();
	    
	    if (matriculaStr.isEmpty() || senha.isEmpty()) {
	        lStatusLogin.setText("Por favor, preencha todos os campos.");
	        return;
	    }
	    try {
	        int matricula = Integer.parseInt(matriculaStr);
	        
	        AlunoDAO alunoDAO = new AlunoDAO();
	        boolean iniciarSistema = alunoDAO.validarAluno(matricula, senha);
	        if (iniciarSistema) {
	            System.out.println("Login com Sucesso - Tela Principal"); 
	        } else {
	            lStatusLogin.setText("Ooops. Matrícula ou senha inválidos.");
	        }
	    } catch (NumberFormatException e) {
	        lStatusLogin.setText("Por favor, insira uma matrícula válida.");
	    }
	}
	
	//Método para mudar da tela do login para cadastro
	@FXML
	public void MudarTelaLogParaCad(ActionEvent event) {
		System.out.println("Mudar tela clicado");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/sistema/notas/view/TelaCadastro.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
			Stage StageAtual = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			StageAtual.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
