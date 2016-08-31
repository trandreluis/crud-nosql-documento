package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class NaoExisteDadosException extends Exception {
	
	public NaoExisteDadosException() {
		super("Não existe dados no banco!");
	}

}
