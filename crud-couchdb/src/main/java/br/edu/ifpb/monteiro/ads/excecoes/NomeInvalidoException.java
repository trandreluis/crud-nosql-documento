package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class NomeInvalidoException extends Exception{
	
	public NomeInvalidoException() {
		super("Nome inválido");
	}

}
