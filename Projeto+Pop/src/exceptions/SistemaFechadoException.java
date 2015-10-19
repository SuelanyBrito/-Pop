package exceptions;

public class SistemaFechadoException extends Exception{
	
	private static final long serialVersionUID = 1L;
	public static final String MENSAGEM_PADRAO = "O Sistema esta fechado. ";

	public SistemaFechadoException(String mensagem) {
		super(MENSAGEM_PADRAO + mensagem);
	}

	public SistemaFechadoException() {
		super(MENSAGEM_PADRAO);
	}
}
