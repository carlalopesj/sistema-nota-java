package sistema.notas.controller;

import java.text.DecimalFormat;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import sistema.notas.model.Avaliacao;
import sistema.notas.model.Disciplina;
import sistema.notas.model.Historico;
import sistema.notas.model.Nota;
import sistema.notas.model.dao.AvaliacaoDAO;
import sistema.notas.model.dao.DisciplinaDAO;
import sistema.notas.model.dao.HistoricoDAO;
import sistema.notas.model.dao.NotaDAO;
import sistema.notas.sessao.AlunoSessao;

public class PrincipalController {

	// Campo nome da disciplina
	@FXML
	private ComboBox<Disciplina> comboBoxDisciplinas;
	// Campos referentes a avaliação
	@FXML
	private ComboBox<String> comboBoxTipoAv;
	@FXML
	private ComboBox<Integer> comboBoxSemestre;
	@FXML
	private ToggleGroup bimestreGrupo;
	@FXML
	private RadioButton rbBimestre1;
	@FXML
	private RadioButton rbBimestre2;
	// Campo valorNota
	@FXML
	private TextField tfValorNota;
	@FXML
	private Button btnSalvar;
	// Status das ações
	@FXML
	private Label lSttsNota;

	// Parte da tabela de histórico
	@FXML
	private TableView<Historico> tableViewHistorico;
	@FXML
	private TableColumn<Historico, String> colDisciplina;
	@FXML
	private TableColumn<Historico, Double> colN1;
	@FXML
	private TableColumn<Historico, Double> colN2;
	@FXML
	private TableColumn<Historico, Double> colNF;
	@FXML
	private TableColumn<Historico, Double> colNR;
	@FXML
	private TableColumn<Historico, Double> colMF;
	@FXML
	private Label lStatusAluno;
	@FXML
	private Label lNotaRec;
	@FXML
	private TextField tfNotaRec;
	@FXML
	private Button btnAddNotaRec;

	DecimalFormat df = new DecimalFormat("#.##"); // Formatar 2 casas decimais, após a vírgula

	@FXML
	public void initialize() {
		preencherComboBoxDisciplinas();
		preencherComboBoxTipoAvaliacao();
		preencherComboBoxSemestre();

		// Inicializa os valores das colunas da tabela do histórico
		colDisciplina.setCellValueFactory(new PropertyValueFactory<>("nomeDisciplina"));
		colN1.setCellValueFactory(new PropertyValueFactory<>("notaBimestral1"));
		colN2.setCellValueFactory(new PropertyValueFactory<>("notaBimestral2"));
		colNF.setCellValueFactory(new PropertyValueFactory<>("mediaSemestral"));
		colNR.setCellValueFactory(new PropertyValueFactory<>("notaRecuperacao"));
		colMF.setCellValueFactory(new PropertyValueFactory<>("mediaFinal"));
		exibirHistorico();
	}

	// Preenchendo ComboBox com dados do banco por meio de uma List
	private void preencherComboBoxDisciplinas() {
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
		// É uma lista usada para observar alterações nos dados
		ObservableList<Disciplina> obsDisciplinas = FXCollections.observableArrayList(disciplinas);
		comboBoxDisciplinas.setItems(obsDisciplinas);
	}

	private void preencherComboBoxTipoAvaliacao() {
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
		List<String> tiposAvaliacao = avaliacaoDAO.listarTipoAvaliacao();
		ObservableList<String> obsTiposAvaliacao = FXCollections.observableArrayList(tiposAvaliacao);
		comboBoxTipoAv.setItems(obsTiposAvaliacao);
	}

	private void preencherComboBoxSemestre() {
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
		List<Integer> semestres = avaliacaoDAO.listarSemestre();
		ObservableList<Integer> obsSemestres = FXCollections.observableArrayList(semestres);
		comboBoxSemestre.setItems(obsSemestres);
	}

	// Método para adicionar uma nota, recebendo uma avaliacao como parâmetro
	private void adicionarNota(Avaliacao avaliacao) {
		// System.out.println("Adicionar nota");
		try {
			double valorNota = Double.parseDouble(tfValorNota.getText());
			int idAvaliacao = avaliacao.getIdAvaliacao(); // Obtendo o ID da avaliação selecionada no ComboBox
			// System.out.println("Avaliacao nota: " + idAvaliacao);
			int idAluno = AlunoSessao.getIdAlunoLogado(); // Obtendo o ID do aluno logado
			Nota nota = new Nota(valorNota, idAvaliacao, idAluno); // Instaciando uma nota, com base nos dados
																	// informados
			NotaDAO notaDAO = new NotaDAO(); // Instanciando DAO
			notaDAO.cadastrarNota(nota); // Passando nota como parâmetro de cadastro
		} catch (NumberFormatException e) { // Caso não seja digitado um número
			e.printStackTrace();
		}
	}

	// Método que verifica se uma avaliação existe com base no que foi informado no
	// ComboBox
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
			Avaliacao avaliacao = avaliacaoDAO.buscarAvaliacao(disciplinaSelecionada.getId(), tipoAvaliacao, semestre,
					bimestreSelecionado);

			if (avaliacao != null) {
				adicionarNota(avaliacao);
				// Atualiza o histórico conforme notas forem adicionadas
				exibirHistorico();
				lSttsNota.setText("Nota salvada com sucesso. Verifique seu histórico");
			} else {
				lSttsNota.setText("Avaliação não encontrada no sistema. Tente novamente.");
			}
		} catch (Exception e) {
			lSttsNota.setText("Preencha os campos!");
		}
	}

	// Método para exibir o histórico por meio de uma lista e utilizando a TableView
	@FXML
	private void exibirHistorico() {
		int idAluno = AlunoSessao.getIdAlunoLogado();
		HistoricoDAO historicoDAO = new HistoricoDAO();
		ObservableList<Historico> historicos = FXCollections.observableArrayList(historicoDAO.listarHistorico(idAluno));
		tableViewHistorico.setItems(historicos);
	}

	// Método para receber dados ao clicar em uma linha da tabela
	@FXML
	private void tbHistoricoClicar() {
		int i = tableViewHistorico.getSelectionModel().getFocusedIndex();
		Historico historico = tableViewHistorico.getItems().get(i); // Armazena em um histórico
		calcularResultado(historico); // Calcula o resultado e mostra na tela o status
	}

	// Calcular a nota com base em algumas condições
	private void calcularResultado(Historico historicoSel) {
		double notaB1 = historicoSel.getNotaBimestral1();
		double notaB2 = historicoSel.getNotaBimestral2();
		double mediaSemestral = historicoSel.getMediaSemestral();
		double notaRec = historicoSel.getNotaRecuperacao();
		
		if (notaB1 == 0.0) {
			lStatusAluno.setText("Você precisa ter tirado " + df.format(12 - notaB2) + " no 1º Bimestre.");
		} else if (notaB2 == 0.0) {
			lStatusAluno.setText("Você precisa tirar " + df.format(12 - notaB1) + " no 2º Bimestre.");
		} else if (mediaSemestral >= 6.0) {
			lStatusAluno.setText("Parabéns, você foi aprovado!!!");
			historicoSel.setMediaFinal(mediaSemestral);
			desabilitarCamposRecuperacao();
		} else if (notaRec != 0.0) {
			double mediaFinal = (mediaSemestral + notaRec) / 2;
			historicoSel.setMediaFinal(mediaFinal);
			if (mediaFinal >= 6.0) {
				lStatusAluno.setText("Parabéns, você foi aprovado na recuperação!");
			} else {
				lStatusAluno.setText("Infelizmente, você não foi aprovado.");
			}
			desabilitarCamposRecuperacao();
		} else {
			lStatusAluno.setText(
					"Você está de recuperação. Faça a prova de recuperação e tire: " + df.format(12 - mediaSemestral));
			habilitarCamposRecuperacao();
		}
	}

	// Adiciona uma nota de recuperação ao clicar no botão de adicionar
	@FXML
	private void adicionarNotaRecuperacao() {
		int i = tableViewHistorico.getSelectionModel().getFocusedIndex();
		Historico historicoSel = tableViewHistorico.getItems().get(i);

		try {
			double notaRecuperacao = Double.parseDouble(tfNotaRec.getText());
			historicoSel.setNotaRecuperacao(notaRecuperacao);
			double mediaFinal = (historicoSel.getMediaSemestral() + notaRecuperacao) / 2;
			historicoSel.setMediaFinal(mediaFinal);

			if (mediaFinal >= 6.0) {
				lStatusAluno.setText("Parabéns, você foi aprovado na recuperação!");
			} else {
				lStatusAluno.setText("Infelizmente, você não foi aprovado.");
			}

			HistoricoDAO historicoDAO = new HistoricoDAO(); // Instanciando DAO
			historicoDAO.adicionarNotaRecuperacao(historicoSel); // Adicionando a nota

			// Atualizar a tabela
			tableViewHistorico.refresh();
			desabilitarCamposRecuperacao();
		} catch (NumberFormatException e) { // Caso não insira um número
			lStatusAluno.setText("Por favor, insira um valor válido para a nota de recuperação.");
		}
	}

	// Métodos para habilitar ou desabilitar a parte de adicionar uma nota de recuperação
	private void habilitarCamposRecuperacao() {
		tfNotaRec.setDisable(false);
		btnAddNotaRec.setDisable(false);
		lNotaRec.setDisable(false);
	}

	private void desabilitarCamposRecuperacao() {
		tfNotaRec.setDisable(true);
		btnAddNotaRec.setDisable(true);
		lNotaRec.setDisable(true);
	}
	
}
