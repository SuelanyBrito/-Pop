package sistema;

import easyaccept.EasyAccept;

public class Main {
	
	public static void main(String[] args) throws Exception {

		args = new String[] { "facade.Facade", "diretorio_testes/use_case1.txt", "diretorio_testes/use_case2.txt",
				"diretorio_testes/use_case3.txt", "diretorio_testes/use_case4.txt" };

		EasyAccept.main(args);

	}
}
