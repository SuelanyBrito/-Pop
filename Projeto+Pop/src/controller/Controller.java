package controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import exceptions.AtualizacaoException;
import exceptions.LoginException;
import exceptions.UsuarioException;
import usuario.Usuario;
import util.MethodsUtilities;

public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, Usuario> usuarios;

	boolean situacaoSistema = false; 
	
	private Usuario logado;

	private MethodsUtilities util;

	public Controller() {
		this.usuarios = new HashMap<String, Usuario>();
		util = new MethodsUtilities();
	}

	/**
	 * Inicia Sistema
	 */
	public void iniciaSistema() {
		this.situacaoSistema = true;
	}

	/**
	 * Fecha Sistema
	 * 
	 * @throws Exception
	 */
	public void fechaSistema() throws LoginException {
		if (this.logado != null) {
			throw new LoginException("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
		this.situacaoSistema = false;
	}

	public void cadastrarUsuario(String nome, String email, String senha, String data, String foto) throws Exception {

		util.isNomeValido(nome);
		util.isSenhaValida(senha);
		util.isDataValida(data);
		util.isEmailValido(email);
		util.isFotoValida(foto);

		if (!usuarios.containsKey(email)) {
			usuarios.put(email, new Usuario(nome, email, senha, util.dataFormatChanges(data), foto));

		} else {
			throw new UsuarioException("usuario existente");
		}
	}

	public void cadastrarUsuario(String nome, String email, String senha, String data) throws Exception {

		util.isNomeValido(nome);
		util.isSenhaValida(senha);
		util.isDataValida(data);
		util.isEmailValido(email);

		if (!usuarios.containsKey(email)) {
			usuarios.put(email, new Usuario(nome, email, senha, util.dataFormatChanges(data)));

		} else {
			throw new UsuarioException("usuario existente");
		}

	}

	public void login(String email, String senha) throws Exception {
		Usuario user = pesquisarUsuario(email);
		if (this.logado != null) {
			throw new LoginException("Um usuarix ja esta logadx: " + logado.getNome() + ".");
		} else if (user == null) {
			throw new LoginException("Um usuarix com email " + email + " nao esta cadastradx.");
		} else {
			if (user.getSenha().equals(senha)) {
				this.logado =  user;
			} else {
				throw new LoginException("Senha invalida.");
			}
		}
	}

	public void logout() throws Exception {
		if (this.logado == null) {
			throw new LoginException("Nao eh possivel realizar logout.");
		} else {
			this.logado = null;
		}

	}

	// Adiciona Amigo
	public void adicionaAmigo(String email) throws Exception {
		if (this.logado == null) {
			throw new LoginException("Nao eh possivel adicionar um amigo.");
		} else {
			int qtdeDePedidos = this.logado.getPedidosDeAmizade().size();

			if (qtdeDePedidos == 0) {
				solicitaAmizade(email);
			} else {
				for (int i = 0; i < qtdeDePedidos; i++) {
					if (this.logado.getPedidosDeAmizade().get(i).equals(email)) {
						Usuario amigo = pesquisarUsuario(email);
						this.logado.adicionaAmigo(amigo);
						amigo.adicionaAmigo(this.logado);
					} else
						if (qtdeDePedidos - 1 == i && this.logado.getPedidosDeAmizade().get(i).equals(email) == false) {
						solicitaAmizade(email);
					}
				}
			}
		}
	}

	private void solicitaAmizade(String email) throws Exception {
		Usuario usuario = pesquisarUsuario(email);
		if (usuario == null) {
			throw new Exception("O usuario " + email + " nao esta cadastrado no +pop.");
		}
		usuario.adicionaNotificacao(this.logado.getNome() + " quer sua amizade.");
		usuario.adicionaPedidoDeAmizade(this.logado.getEmail());
	}

	// Remove Amigo
	public void removeAmigo(String email) throws Exception {
		if (this.logado == null) {
			throw new LoginException("Nao eh possivel adicionar um amigo.");
		} else {
			for (int i = 0; i < this.logado.getAmigos().size(); i++) {
				Usuario amigo = this.logado.getAmigos().get(i);
				if (this.logado.getAmigos().get(i).getEmail().equals(email)) {
					this.logado.removeAmigo(amigo);
					amigo.removeAmigo(this.logado);
					amigo.adicionaNotificacao(this.logado.getNome() + " removeu a sua amizade.");
				}
			}
		}
	}

	// Aceita Amizade
	public void aceitaAmizade(String email) throws Exception {
		if (this.logado == null) {
			throw new LoginException("Nao eh possivel aceitar amizade.");
		} else {
			int qtdeDePedidos = this.logado.getPedidosDeAmizade().size();
			Usuario usuario = pesquisarUsuario(email);

			if (qtdeDePedidos == 0 && usuario != null) {
				throw new Exception(usuario.getNome() + " nao lhe enviou solicitacoes de amizade.");

			} else if (usuario == null) {
				throw new Exception("O usuario " + email + " nao esta cadastrado no +pop.");

			} else {
				for (int i = 0; i < qtdeDePedidos; i++) {
					if (this.logado.getPedidosDeAmizade().get(i).equals(email)) {
						this.logado.adicionaAmigo(usuario);
						usuario.adicionaAmigo(this.logado);

					} else
						if (i == qtdeDePedidos - 1 && this.logado.getPedidosDeAmizade().get(i).equals(email) == false) {
						throw new Exception(usuario.getNome() + " nao lhe enviou solicitacoes de amizade.");
					}
				}
			}
		}
	}

	// Rejeita Amizade
	public void rejeitaAmizade(String email) throws Exception {
		if (this.logado == null) {
			throw new LoginException("Nao eh possivel rejeita amizade.");
		} else {
			int qtdeDePedidos = this.logado.getPedidosDeAmizade().size();
			Usuario usuario = pesquisarUsuario(email);

			if (qtdeDePedidos == 0 && usuario != null) {
				throw new Exception(usuario.getNome() + " nao lhe enviou solicitacoes de amizade.");

			} else if (usuario == null) {
				throw new Exception("O usuario " + email + " nao esta cadastrado no +pop.");

			} else {
				for (int i = 0; i < qtdeDePedidos; i++) {
					if (this.logado.getPedidosDeAmizade().get(i).equals(email)) {
						this.logado.rejeitaAmizade(email);
						usuario.adicionaNotificacao(this.logado.getNome() + " rejeitou sua amizade.");
					} else
						if (i == qtdeDePedidos - 1 && this.logado.getPedidosDeAmizade().get(i).equals(email) == false) {
						throw new Exception(usuario.getNome() + " nao lhe enviou solicitacoes de amizade.");
					}
				}
			}
		}
	}

	// Like e Deslike
	public void like() {

	}

	public void deslike() {

	}

	// Posts
	public void criaPost(String mensagem, String dataEHora) throws Exception {
		if (this.logado == null) {
			throw new LoginException("Nao eh possivel criar um post.");
		} else {
			util.isMensagemValida(mensagem);
		}

	}

	public void postarComentario(String mensagem, Usuario amigo) {
		// this.logado.postarComentario(mensagem, amigo);
	}

	// Atualiza Perfil
	public void atualizaPerfil(String atributo, String valor) throws Exception {
		if (this.logado == null) {
			throw new LoginException("Nao eh possivel atualizar um perfil.");
		} else {
			if (atributo.equals("Nome")) {
				this.util.isNomeValido(valor);
				this.logado.setNome(valor);
			} else if (atributo.equals("E-mail")) {
				this.util.isEmailValido(valor);
				this.usuarios.remove(this.logado.getEmail(), this.logado);
				this.logado.setEmail(valor);
				this.usuarios.put(valor, this.logado);
			} else if (atributo.equals("Senha")) {
				throw new Exception("Eh necessario digitar velha senha");
			} else if (atributo.equals("Foto")) {
				this.util.isFotoValida(valor);
				this.logado.setImagem(valor);
			} else if (atributo.equals("Data de Nascimento")) {
				this.util.isDataNascimentoValida(valor);
				this.logado.setDataNascimento(util.dataFormatChanges(valor));
			} else {
				throw new Exception("Atributo invalido.");
			}
		}
	}

	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws Exception {
		if (this.logado == null) {
			throw new LoginException("Nao eh possivel atualizar um perfil.");
		} else {
			if (atributo.equals("Senha")) {
				if (this.logado.getSenha().equals(velhaSenha)) {
					this.util.isSenhaValida(valor);
					this.logado.setSenha(valor);
				} else {
					throw new AtualizacaoException("A senha fornecida esta incorreta.");
				}
			}
		}
	}

	private Usuario pesquisarUsuario(String email) {
		if (usuarios.containsKey(email)) {
			return usuarios.get(email);
		}
		return null;
	}

	public void removeUsuario(String email) throws Exception {
		if (this.usuarios.containsKey(email)) {
			this.usuarios.remove(email);
		} else {
			throw new Exception("Email inexistente");
		}
	}

	// Getters
	public Collection<Usuario> getListaUsuarios() {
		return usuarios.values();
	}

	public Usuario getLogado() {
		return this.logado;
	}

	public int getNotificacoes() {
		return this.logado.getNotificacoes().size();
	}

	public int getQtdAmigos() {
		return this.logado.getAmigos().size();
	}

	public String getNextNotificacao() throws Exception {
		if (getNotificacoes() == 0) {
			throw new Exception("Nao ha mais notificacoes.");
		} else {
			return this.logado.getNextNotificacao();
		}
	}

	public String getNome(String email) {
		if (pesquisarUsuario(email) == null) {
			return "O usuario com email " + email + " nao esta cadastrado.";
		} else {
			return this.usuarios.get(email).getNome();
		}
	}

	public String getInfoUsuario(String atributo, String emailUsuario) throws Exception {
		Usuario user = pesquisarUsuario(emailUsuario);
		if (user == null) {
			throw new Exception("Um usuarix com email " + emailUsuario + " nao esta cadastradx.");
		} else {
			if (atributo.equals("Nome")) {
				return user.getNome();
			} else if (atributo.equals("E-mail")) {
				return user.getEmail();
			} else if (atributo.equals("Senha")) {
				throw new Exception("A senha dx usuarix eh protegida.");
			} else if (atributo.equals("Foto")) {
				return user.getImagem();
			} else if (atributo.equals("Data de Nascimento")) {
				return user.getDataNascimento().toString();
			} else {
				throw new Exception("Atributo invalido.");
			}
		}
	}

	public String getInfoUsuario(String atributo) throws Exception {
		if (this.logado == null) {
			throw new LoginException("Nao eh possivel verificar informacoes do usuario.");
		} else {
			if (atributo.equals("Nome")) {
				return this.logado.getNome();
			} else if (atributo.equals("E-mail")) {
				return this.logado.getEmail();
			} else if (atributo.equals("Senha")) {
				throw new Exception("A senha dx usuarix eh protegida.");
			} else if (atributo.equals("Foto")) {
				return this.logado.getImagem();
			} else if (atributo.equals("Data de Nascimento")) {
				return this.logado.getDataNascimento().toString();
			} else {
				throw new Exception("Atributo invalido.");
			}
		}

	}

}