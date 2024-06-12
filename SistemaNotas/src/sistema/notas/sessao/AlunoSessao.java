package sistema.notas.sessao;

//Classe para iniciar a sessão do aluno com o sua matricula correspondente (será usada para armazenar a nota)
public class AlunoSessao {
	private static int idAlunoLogado;	

    public static int getIdAlunoLogado() {
        return idAlunoLogado;
    }

    public static void setIdAlunoLogado(int idAlunoLogado) {
    	AlunoSessao.idAlunoLogado = idAlunoLogado;
    }
}
