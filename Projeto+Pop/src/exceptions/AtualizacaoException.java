package exceptions;

public class AtualizacaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String MENSAGEM_PADRAO = "Erro na atualizacao de perfil. ";

	public AtualizacaoException(String mensagem) {
		super(MENSAGEM_PADRAO + mensagem);
	}

	public AtualizacaoException() {
		super(MENSAGEM_PADRAO);
	}
}
