package exceptions;

public class MensagemInvalidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String MENSAGEM_PADRAO = "";

	public MensagemInvalidaException(String mensagem) {
		super(MENSAGEM_PADRAO + mensagem);
	}

	public MensagemInvalidaException() {
		super(MENSAGEM_PADRAO);
	}

}
