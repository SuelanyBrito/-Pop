package post;

public abstract class Midia {

	private String caminho;

	public Midia(String caminho) {
		this.caminho = caminho;

	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

}
