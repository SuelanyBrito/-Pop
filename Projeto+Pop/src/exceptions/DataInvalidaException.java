package exceptions;

public class DataInvalidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String MENSAGEM_PADRAO = "Erro no cadastro de Usuarios. ";

	public DataInvalidaException(String mensagem) {
		super(MENSAGEM_PADRAO + mensagem);
	}

	public DataInvalidaException() {
		super(MENSAGEM_PADRAO);

	}
}
