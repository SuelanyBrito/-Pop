package facade;

import controller.Controller;
import usuario.Usuario;

public class Facade {

	private Controller controller;

	public Facade() {
		this.controller = new Controller();
	}

	public void iniciaSistema() {
		this.controller.iniciaSistema();
	}

	public void fechaSistema() throws Exception {
		this.controller.fechaSistema();
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataNascimento, String imagem)
			throws Exception {

		this.controller.cadastrarUsuario(nome, email, senha, dataNascimento, imagem);
		return email;
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataNascimento) throws Exception {

		this.controller.cadastrarUsuario(nome, email, senha, dataNascimento);
		return email;
	}

	// Login e Logout
	public void login(String email, String senha) throws Exception {
		this.controller.login(email, senha);
	}

	public void logout() throws Exception {
		this.controller.logout();
	}

	// Adiciona amigo
	public void adicionaAmigo(String email) throws Exception {
		this.controller.adicionaAmigo(email);
	}

	// Remover amigo
	public void removeAmigo(String email) throws Exception {
		this.controller.removeAmigo(email);
	}

	// Aceita Amizade
	public void aceitaAmizade(String email) throws Exception {
		this.controller.aceitaAmizade(email);
	}

	// Rejeita Amizade
	public void rejeitaAmizade(String email) throws Exception {
		this.controller.rejeitaAmizade(email);
	}

	// Post
	public void criaPost(String mensagem, String data) throws Exception {
		this.controller.criaPost(mensagem, data);
	}

	public void postarComentario(String mensagem, Usuario amigo) {
		this.controller.postarComentario(mensagem, amigo);
	}

	// Like e Deslike
	public void like() {
		this.controller.like();
	}

	public void deslike() {
		this.controller.deslike();
	}

	// Remove Usuario
	public void removeUsuario(String usuario) throws Exception {
		this.controller.removeUsuario(usuario);
	}

	// Atualiza Perfil
	public void atualizaPerfil(String atributo, String valor) throws Exception {
		this.controller.atualizaPerfil(atributo, valor);
	}

	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws Exception {
		this.controller.atualizaPerfil(atributo, valor, velhaSenha);
	}

	// Getters
	public String getNome(String email) throws Exception {
		return this.controller.getNome(email);
	}

	public String getInfoUsuario(String atributo, String usuario) throws Exception {
		return this.controller.getInfoUsuario(atributo, usuario);
	}

	public String getInfoUsuario(String atributo) throws Exception {
		return this.controller.getInfoUsuario(atributo);
	}

	public int getQtdAmigos() {
		return this.controller.getQtdAmigos();
	}

	public int getNotificacoes() {
		return this.controller.getNotificacoes();
	}

	public String getNextNotificacao() throws Exception {
		return this.controller.getNextNotificacao();
	}

}