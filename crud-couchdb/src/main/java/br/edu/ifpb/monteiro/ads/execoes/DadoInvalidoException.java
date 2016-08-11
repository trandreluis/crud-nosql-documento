package br.edu.ifpb.monteiro.ads.execoes;

@SuppressWarnings("serial")
public class DadoInvalidoException extends Exception {

	public DadoInvalidoException() {
		super("Preencha todos os campos corretamente!");
	}
	
}
