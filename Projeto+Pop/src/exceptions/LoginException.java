package exceptions;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String MENSAGEM_PADRAO = "";

	public LoginException(String mensagem) {
		super(MENSAGEM_PADRAO + mensagem);
	}

	public LoginException() {
		super(MENSAGEM_PADRAO);
	}
}
