package post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {

	private static final int NUMERO_MAXIMO_DE_CARACTERES = 200;

	private List<Midia> conteudo;
	private int like, dislike;
	private LocalDateTime dataCriacao;
	private List<String> hashtags;

	public Post() {
		this.like = 0;
		this.dislike = 0;
	}

	public Post(String texto, LocalDateTime data) {

		this();

		conteudo = new ArrayList<Midia>();
		this.hashtags = new ArrayList<String>();
		this.dataCriacao = data;
	}

	public static int getNumeroMaximoDeCaracteres() {
		return NUMERO_MAXIMO_DE_CARACTERES;
	}

	public int getPopularidade() {
		return like - dislike;
	}

	public List<Midia> getConteudo() {
		return conteudo;
	}

	public void setConteudo(ArrayList<Midia> conteudo) {
		this.conteudo = conteudo;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}

}
