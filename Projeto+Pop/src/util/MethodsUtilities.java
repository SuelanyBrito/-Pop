package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.CadastroException;
import exceptions.DataInvalidaException;
import exceptions.LoginException;
import exceptions.MensagemInvalidaException;
import usuario.Usuario;

public class MethodsUtilities {

	public void isDataValida(String data) throws DataInvalidaException {

		if (data == null) {
			throw new DataInvalidaException("Data invalida, campo nao pode ser nulo.");
		}
		if (data.length() != 10) {
			throw new DataInvalidaException("Formato de data esta invalida.");
		}

	}

	public void isNomeValido(String nome) throws Exception, CadastroException {
		if (nome == null) {
			throw new Exception("Nome dx usuarix nao pode ser vazio.");
		}
		if (nome.trim().equals("")) {
			throw new CadastroException("Nome dx usuarix nao pode ser vazio.");
		}
		if (!(nome.matches("[A-Za-z?-?\\s]*+"))) {
			throw new Exception("nome so pode conter caracteres");
		}
	}

	public void isSenhaValida(String senha) throws Exception {
		if (senha == null) {
			throw new Exception("senha nao pode ser nula");
		}
		if (senha.trim().equals("")) {
			throw new Exception("senha nao pode ser vazia");
		}
		if (senha.length() < 6) {
			throw new Exception("senha nao pode ter tamanho menor que 6");
		}
	}

	public void isMensagemValida(String mensagem) throws Exception {

		if (mensagem == null) {
			throw new MensagemInvalidaException("Mensagem invalida, campo nulo.");
		}
		if (mensagem.trim().equals("")) {
			throw new MensagemInvalidaException("Mensagem invalida, campo vazio.");
		}

	}

	public void isEmailValido(String email) throws Exception {
		
		ValidaEmail validade = null;
		if (email == null) {
			throw new Exception("email nao pode ser nulo");
		}
		if (email.trim().equals("")) {
			throw new Exception("email nao pode ser vazio");
		}
		if (!email.contains("@")) {
			throw new CadastroException("Formato de e-mail esta invalido.");
		} 
		if(validade.validarEmail(email) == false){
			throw new CadastroException("Formato de e-mail esta invalido.");
		}
		/*
			 * if (!(email.matches("[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+")))
			 * { throw new Exception("Formato de e-mail esta invalido."); }
			 */

	}

	public void isDataNascimentoValida(String dataNascimento) throws Exception {

		if (dataNascimento == null || dataNascimento.trim().equals("")) {
			throw new CadastroException("Data de nascimento nao pode ser vazio");
		}
	}

	public void isFotoValida(String foto) throws Exception {

		if (foto == null || foto.equals("")) {
			throw new CadastroException("Foto nao pode ser vazia");
		}
	}

	public LocalDate dataFormatChanges(String data) throws DataInvalidaException {
		
		CharSequence dia = data.substring(0,2);
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(data, formatter);
		
		return date;

//		String dia = data.substring(0, 2);
//
//		isDiaValido(dia);
//
//		String mes = data.substring(3, 5);
//
//		isMesValido(mes);
//
//		String ano = data.substring(6, 10);
//
//		String novoFormato = ano + "-" + mes + "-" + dia;
//
//		return novoFormato;

	}

	private void isMesValido(String mes) throws DataInvalidaException {
		if (Integer.parseInt(mes) > 12 || Integer.parseInt(mes) < 1) {
			throw new DataInvalidaException("Data nao existe.");
		}
	}

	private void isDiaValido(String dia) throws DataInvalidaException {
		if (Integer.parseInt(dia) > 31 || Integer.parseInt(dia) < 1) {
			throw new DataInvalidaException("Data nao existe.");
		}
	}
}
