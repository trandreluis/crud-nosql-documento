package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class NaoExisteDadosException extends Exception {
	
	public NaoExisteDadosException() {
		super("N�o existe dados no banco!");
	}

}
