package sistema.notas.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import sistema.notas.model.Avaliacao;
import sistema.notas.model.Disciplina;
import sistema.notas.model.Nota;
import sistema.notas.model.dao.AvaliacaoDAO;
import sistema.notas.model.dao.DisciplinaDAO;
import sistema.notas.model.dao.NotaDAO;
import sistema.notas.sessao.AlunoSessao;

public class PrincipalController {

	//Campo nome da disciplina
	@FXML
    private ComboBox<Disciplina> comboBoxDisciplinas;
	
	//Campos referentes a avaliação
	@FXML
	private ComboBox<String> comboBoxTipoAv;
	@FXML
	private ComboBox<Integer> comboBoxSemestre;
	@FXML
	private RadioButton rbBimestre1;
	@FXML 
	private RadioButton rbBimestre2;
	
	//Campo valorNota
	@FXML 
	private TextField tfValorNota;
	
	@FXML
	private Button btnSalvar;
	
	//Status das ações
	@FXML
	private Label lSttsNota;
	
	@FXML
    public void initialize() {
        preencherComboBoxDisciplinas();
        preencherComboBoxTipoAvaliacao();
        preencherComboBoxSemestre();
    }

    //Preenchendo ComboBox com dados do banco por meio de uma List
	private void preencherComboBoxDisciplinas() {
	    DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	    List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();

	    //é uma lista usada para observar alterações nos dados
	    ObservableList<Disciplina> obsDisciplinas = FXCollections.observableArrayList(disciplinas);
	    comboBoxDisciplinas.setItems(obsDisciplinas);
	}

    
    //Recuperando o ID da disciplina selecionada
    @FXML
    public void comboBoxDisciplinasSelecionada(ActionEvent event) {
        Disciplina disciplinaSelecionada = comboBoxDisciplinas.getValue();
        if (disciplinaSelecionada != null) {
            int idDisciplinaSelecionada = disciplinaSelecionada.getId();
            System.out.println("ID da disciplina selecionada: " + idDisciplinaSelecionada);
        }
    }
    
    //Preenchendo ComboBox com dados do banco por meio de uma List
    private void preencherComboBoxTipoAvaliacao() {
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        List<String> tiposAvaliacao = avaliacaoDAO.listarTipoAvaliacao();

        // Verificar se a lista de tipos de avaliação está sendo recuperada corretamente
        for (String tipo : tiposAvaliacao) {
            System.out.println("Tipo de Avaliação no ComboBox: " + tipo);
        }

        ObservableList<String> obsTiposAvaliacao = FXCollections.observableArrayList(tiposAvaliacao);
        comboBoxTipoAv.setItems(obsTiposAvaliacao);
    }
    
    //Preenchendo ComboBox com dados do banco por meio de uma List
    private void preencherComboBoxSemestre() {
        // Presumindo que o método listarSemestres existe e retorna uma lista de Integers
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        List<Integer> semestres = avaliacaoDAO.listarSemestre();

        //VVV
        ObservableList<Integer> obsSemestres = FXCollections.observableArrayList(semestres);
        comboBoxSemestre.setItems(obsSemestres);
    }
    
    //Método para adicionar uma nota, recebendo uma avaliacao como parâmetro
    private void adicionarNota(Avaliacao avaliacao) {
    	//System.out.println("Adicionar nota");
    	try {
            double valorNota = Double.parseDouble(tfValorNota.getText());
            int idAvaliacao = avaliacao.getIdAvaliacao(); // Obtendo o ID da avaliação selecionada no ComboBox
            //System.out.println("Avaliacao nota: " + idAvaliacao);
            int idAluno = AlunoSessao.getIdAlunoLogado(); //Obtendo o ID do aluno logado
            Nota nota = new Nota(valorNota, idAvaliacao, idAluno); //Instaciando uma nota, com base nos dados informados
            NotaDAO notaDAO = new NotaDAO(); //Instanciando DAO
            notaDAO.cadastrarNota(nota); //Passando nota como parâmetro de cadastro
        } catch (NumberFormatException e) { //Caso não seja digitado um número
        	e.printStackTrace();
        }
    }

    //Método que verifica se uma avaliação existe com base no que foi informado no ComboBox
    @FXML
    private void buscarAvaliacao(ActionEvent event) {
        try {
            Disciplina disciplinaSelecionada = comboBoxDisciplinas.getValue();
            String tipoAvaliacao = comboBoxTipoAv.getValue();
            int semestre = comboBoxSemestre.getValue();
            int bimestreSelecionado = 0;
            if (rbBimestre1.isSelected()) {
                bimestreSelecionado = 1;
            } else if (rbBimestre2.isSelected()) {
                bimestreSelecionado = 2;
            }

            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
            Avaliacao avaliacao = avaliacaoDAO.buscarAvaliacao(disciplinaSelecionada.getId(), tipoAvaliacao, semestre, bimestreSelecionado);

            if (avaliacao != null) {
            	adicionarNota(avaliacao);
            	lSttsNota.setText("Nota salvada com sucesso. Verifique seu histórico");
                //System.out.println("Avaliação encontrada: " + avaliacao.getTipoAvaliacao() + ", Semestre: " + avaliacao.getSemestre() + ", Bimestre: " + avaliacao.getBimestre());
            } else {
            	lSttsNota.setText("Avaliação não encontrada no sistema. Tente novamente.");
                //System.out.println("Avaliação não encontrada.");
            }
        } catch (Exception e) {
            lSttsNota.setText("Preencha os campos!");
        }
    }
}
