package exceptions;

public class UsuarioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String MENSAGEM_PADRAO = "";

	public UsuarioException(String mensagem) {
		super(MENSAGEM_PADRAO + mensagem);
	}

	public UsuarioException() {
		super(MENSAGEM_PADRAO);
	}

}
