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
	private void cadastrarAluno(ActionEvent event) {
		System.out.println("Botão cadastrar foi clicado"); //Teste do botão
		//Recuperando entrada do usuário
		String matriculaStr = tfMatricula.getText();
		String nome = tfNome.getText();
		String email = tfEmail.getText();
		String curso = tfCurso.getText();
		String senha = tfSenha.getText();
		
		//Validando campos preenchidos
		if (matriculaStr.isEmpty() || nome.isEmpty()|| email.isEmpty() || curso.isEmpty() || senha.isEmpty()) {
			lStatusCad.setText("Por favor, preencha todos os campos.");
	        return;
	    } else {
	    	try {
	    		//Converte string para inteiro
	    		int matricula = Integer.parseInt(matriculaStr);
	    		
	    		//Instancia um novo aluno, com os atributos informados
	    		Aluno aluno = new Aluno(matricula, nome, email,curso, senha);
	    		//Acesso ao banco de dados por meio do DAO
	    		AlunoDAO alunoDAO = new AlunoDAO();
	    		if(!alunoDAO.cadastrarAluno(aluno)) {
	    			lStatusCad.setText("Matrícula já cadastrada.");
	    		} else {
	    			//Ao se cadastrar, abre-se a tela de login
	    			trocarTela("/sistema/notas/view/TelaLogin.fxml", event);
	    		}
	    	} catch (NumberFormatException e) { //Verifica se foi digitado um inteiro
	    		lStatusCad.setText("Matrícula inválida.");
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
	private void loginAluno(ActionEvent event) {
	    System.out.println("Botão entrar foi clicado"); 
	    String matriculaStr = tfMatriculaLogin.getText();
	    String senha = tfSenhaLogin.getText();

	    try {
	        int matricula = Integer.parseInt(matriculaStr);
	        
	        AlunoDAO alunoDAO = new AlunoDAO();
	        boolean iniciarSistema = alunoDAO.validarAluno(matricula, senha); //Verificando se existe esse aluno no banco de dados
	        if (iniciarSistema) { //Se existir, será levado a tela principal
	            System.out.println("Login com Sucesso - Tela Principal");
	            trocarTela("/sistema/notas/view/TelaPrincipal.fxml", event);
	        } else {
	            lStatusLogin.setText("Ooops. Matrícula ou senha inválidos."); //Caso contrário, informa-se um erro
	        }
	    } catch (NumberFormatException e) { //Caso insira outro tipo na matrícula que não seja inteiro
	        lStatusLogin.setText("Por favor, insira uma matrícula válida.");
	    }
	}
	
	//Método para mudar da tela do login para cadastro por meio de um evento no botão
	@FXML
	private void mudarTelaLogParaCad(ActionEvent event) {
		System.out.println("Mudar tela clicado");
		trocarTela("/sistema/notas/view/TelaCadastro.fxml", event);
	}
	
	//Método para trocar de tela sem o evento
	 private void trocarTela(String caminhoFXML, ActionEvent event) {
	        try {
	            Parent root = FXMLLoader.load(getClass().getResource(caminhoFXML));
	            Stage stage = new Stage();
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	            // Fechar a tela atual
	            stage.setResizable(false);
	            Stage stageAtual = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	            stageAtual.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
}
