package lp2;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import exception.AtualizacaoException;
import exception.ErroLoginException;

public class Sistema implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, Usuario> usuarios;

	private Usuario logado;

	public Sistema() {
		this.usuarios = new HashMap<String, Usuario>();
	}

	// Inicia e Fecha Sistema
	public void iniciaSistema() {

	}

	public void fechaSistema() throws Exception {
		if (this.logado != null) {
			throw new Exception(
					"Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
	}

	// Cadastra usuario com ou sem foto
	public void cadastrarUsuario(String nome, String email, String senha,
			String data, String foto) throws Exception {

		if (!usuarios.containsKey(email)) {
			usuarios.put(email, new Usuario(nome, email, senha, data, foto));

		} else {
			throw new Exception("usuario existente");
		}
	}

	public void cadastrarUsuario(String nome, String email, String senha,
			String data) throws Exception {

		if (!usuarios.containsKey(email)) {
			usuarios.put(email, new Usuario(nome, email, senha, data));

		} else {
			throw new Exception("usuario existente");
		}
	}

	public void login(String email, String senha) throws Exception {
		Usuario user = pesquisarUsuario(email);

		if (this.logado != null) {
			throw new ErroLoginException("Um usuarix ja esta logadx: "
					+ this.logado.getNome() + ".");
		} else if (user == null) {
			throw new ErroLoginException("Um usuarix com email " + email
					+ " nao esta cadastradx.");
		} else {
			if (user.getSenha().equals(senha)) {
				this.logado = user;
			} else {
				throw new ErroLoginException("Senha invalida.");
			}
		}
	}

	public void logout() throws Exception {
		if (this.logado == null) {
			throw new Exception(
					"Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
		} else {
			this.logado = null;
		}

	}

	// Like e Deslike
	public void like() {

	}

	public void deslike() {

	}

	// Posts
	public void criaPost(String mensagem, String data) {

	}

	public void postarComentario(String mensagem, Amigo amigo) {
		this.logado.postarComentario(mensagem, amigo);
	}

	// Atualiza Perfil
	public void atualizaPerfil(String atributo, String valor) throws Exception {
		if (this.logado == null) {
			throw new Exception(
					"Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		} else {
			if (atributo.equals("Nome")) {
				this.isNomeValido(valor);
				this.logado.setNome(valor);
			} else if (atributo.equals("E-mail")) {
				this.isEmailValido(valor);
				this.usuarios.remove(this.logado.getEmail(), this.logado);
				this.logado.setEmail(valor);
				this.usuarios.put(valor, this.logado);
			} else if (atributo.equals("Senha")) {
				throw new Exception("Eh necessario digitar velha senha");
			} else if (atributo.equals("Foto")) {
				this.isFotoValida(valor);
				this.logado.setFoto(valor);
			} else if (atributo.equals("Data de Nascimento")) {
				this.isDataNascimentoValida(valor);
				this.logado.setDataNascimento(valor);
			} else {
				throw new Exception("Atributo invalido.");
			}
		}
	}

	public void atualizaPerfil(String atributo, String valor, String velhaSenha)
			throws Exception {
		if (this.logado == null) {
			throw new Exception(
					"Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		} else {
			if (atributo.equals("Senha")) {
				if (this.logado.getSenha().equals(velhaSenha)) {
					this.isSenhaValida(valor);
					this.logado.setSenha(valor);
				} else {
					throw new AtualizacaoException(
							"A senha fornecida esta incorreta.");
				}
			}
		}
	}

	// Controle de Usuarios: Pesquisa e Remocao
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

	public String getNome(String email) {
		if (pesquisarUsuario(email) == null) {
			return "O usuario com email " + email + " nao esta cadastrado.";
		} else {
			return this.usuarios.get(email).getNome();
		}
	}

	public String getInfoUsuario(String atributo, String emailUsuario)
			throws Exception {
		Usuario user = pesquisarUsuario(emailUsuario);
		if (user == null) {
			throw new Exception("Um usuarix com email " + emailUsuario
					+ " nao esta cadastradx.");
		} else {
			if (atributo.equals("Nome")) {
				return user.getNome();
			} else if (atributo.equals("E-mail")) {
				return user.getEmail();
			} else if (atributo.equals("Senha")) {
				throw new Exception("A senha dx usuarix eh protegida.");
			} else if (atributo.equals("Foto")) {
				return user.getFoto();
			} else if (atributo.equals("Data de Nascimento")) {
				return user.getDataNascimento();
			} else {
				throw new Exception("Atributo invalido.");
			}
		}
	}

	public String getInfoUsuario(String atributo) throws Exception {
		if (this.logado == null) {
			throw new Exception("Nao ha nenhum usuarix logadx.");
		} else {
			if (atributo.equals("Nome")) {
				return this.logado.getNome();
			} else if (atributo.equals("E-mail")) {
				return this.logado.getEmail();
			} else if (atributo.equals("Senha")) {
				throw new Exception("A senha dx usuarix eh protegida.");
			} else if (atributo.equals("Foto")) {
				return this.logado.getFoto();
			} else if (atributo.equals("Data de Nascimento")) {
				return this.logado.getDataNascimento();
			} else {
				throw new Exception("Atributo invalido.");
			}
		}

	}

	// Exception Atualizacao
	private void isDataNascimentoValida(String valor) throws Exception {
		if (valor == null || valor.trim().equals("")) {
			throw new AtualizacaoException(
					"Data de nascimento nao pode ser vazio");
		}
	}

	private void isFotoValida(String foto) throws Exception {
		if (foto == null || foto.equals("")) {
			throw new AtualizacaoException("Foto nao pode ser vazia");
		}
	}

	private void isEmailValido(String email) throws Exception {
		if (email == null) {
			throw new AtualizacaoException("email nao pode ser nulo");
		}
		if (email.trim().equals("")) {
			throw new AtualizacaoException("email nao pode ser vazio");
		}
		if (!email.contains("@")) {
			throw new AtualizacaoException("Formato de e-mail esta invalido.");
		}/*
		 * if (!(email.matches("[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+"))) {
		 * throw new Exception("Formato de e-mail esta invalido."); }
		 */

	}

	private void isNomeValido(String valor) throws Exception {
		if (valor == null) {
			throw new AtualizacaoException("Nome dx usuarix nao pode ser nulo.");
		}
		if (valor.trim().equals("")) {
			throw new AtualizacaoException(
					"Nome dx usuarix nao pode ser vazio.");
		}
		if (!(valor.matches("[A-Za-z�-�\\s]*+"))) {
			throw new AtualizacaoException("nome so pode conter caracteres");
		}

	}

	private void isSenhaValida(String valor) throws Exception {
		if (valor == null) {
			throw new AtualizacaoException("senha nao pode ser nula");
		}
		if (valor.trim().equals("")) {
			throw new AtualizacaoException("senha nao pode ser vazia");
		}
		if (valor.length() < 6) {
			throw new AtualizacaoException(
					"senha nao pode ter tamanho menor que 6");
		}

	}

}