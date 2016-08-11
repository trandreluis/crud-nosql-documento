package br.edu.ifpb.monteiro.ads.execoes;

@SuppressWarnings("serial")
public class IdInvalidoException extends Exception {

	public IdInvalidoException() {
		super("ID inválido!");
	}
}
