package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class IdInvalidoException extends Exception {

	public IdInvalidoException() {
		super("ID inválido!");
	}
}
