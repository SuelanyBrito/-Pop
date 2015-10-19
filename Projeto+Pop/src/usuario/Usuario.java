package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exceptions.CadastroException;
import post.Post;

public class Usuario {

	private String nome, email, senha, imagem;
	private LocalDate dataDeNascimento;
	private TipoUsuario tipoUsuario;

	private List<Usuario> amigos;
	private List<String> notificacoes;
	private List<String> pedidosDeAmizade;
	private List<Post> postagens;

	public Usuario(String nome, String email, String senha, LocalDate dataNascimento, String imagem) throws Exception {

		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.imagem = imagem;
		this.dataDeNascimento = dataNascimento;
		this.amigos = new ArrayList<Usuario>();
		this.notificacoes = new ArrayList<String>();
		this.pedidosDeAmizade = new ArrayList<String>();

	}

	public Usuario(String nome, String email, String senha, LocalDate dataNascimento) throws CadastroException, Exception {

		this(nome, email, senha, dataNascimento, "resources/default.jpg");
	}

	public void adicionaAmigo(Usuario usuario) {
		this.amigos.add(usuario);
	}

	public void removeAmigo(Usuario usuario) {
		for (int i = 0; i < this.amigos.size(); i++) {
			if (this.amigos.get(i).equals(usuario)) {
				this.amigos.remove(i);
			}
		}
	}

	public void rejeitaAmizade(String email) {
		int qtdeDePedidos = getPedidosDeAmizade().size();

		for (int i = 0; i < qtdeDePedidos; i++) {
			if (getPedidosDeAmizade().get(i).equals(email)) {
				this.pedidosDeAmizade.remove(i);
			}
		}
	}

	public void adicionaNotificacao(String mensagem) {
		this.notificacoes.add(mensagem);
	}

	public void adicionaPedidoDeAmizade(String email) {
		this.pedidosDeAmizade.add(email);
	}

	public void postarMensagem(String mensagem) throws Exception {
		// <= 200 caracteres
		// pode possuir imagem,audio (Strings)
		// Hashtags (usadas como pesquisa)
	}

	// Getters
	public String getEmail() {
		return this.email;
	}

	public String getImagem() {
		return this.imagem;
	}

	public String getSenha() {
		return this.senha;
	}

	public LocalDate getDataNascimento() {
		return this.dataDeNascimento;
	}

	public String getNome() {
		return this.nome;
	}

	public List<Usuario> getAmigos() {
		return this.amigos;
	}

	public List<String> getNotificacoes() {
		return this.notificacoes;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public String getNextNotificacao() {
		String notificacao = this.notificacoes.get(0);
		this.notificacoes.remove(0);
		return notificacao;
	}

	public List<String> getPedidosDeAmizade() {
		return this.pedidosDeAmizade;
	}

	// Setters
	public void setEmail(String email) {
		this.email = email;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setNome(String nome) throws Exception {
		this.nome = nome;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataDeNascimento = dataNascimento;
	}

	// Post

	public void adicionarPostagem(Post post) {
		postagens.add(post);
	}

	public void deletarPostagem(Post post) {
		postagens.remove(post);
	}

	public int calculaPopularidade() {
		int popularidade = 0;
		for (Post post : postagens) {
			popularidade += post.getPopularidade();
		}
		return popularidade;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario u = (Usuario) obj;
			return this.getEmail().equals(u.getEmail());
		}
		return false;
	}

	@Override
	public String toString() {
		return "Nome: " + getNome() + ";" + " Email: " + getEmail();
	}

}