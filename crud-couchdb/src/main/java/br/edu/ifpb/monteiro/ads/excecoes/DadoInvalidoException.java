package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class DadoInvalidoException extends Exception {

	public DadoInvalidoException() {
		super("Preencha todos os campos corretamente!");
	}
	
}
