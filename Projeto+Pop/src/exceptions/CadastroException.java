package exceptions;

public class CadastroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String MENSAGEM_PADRAO = "Erro no cadastro de Usuarios. ";

	public CadastroException(String mensagem) {
		super(MENSAGEM_PADRAO + mensagem);
	}

	public CadastroException() {
		super(MENSAGEM_PADRAO);
	}
}
