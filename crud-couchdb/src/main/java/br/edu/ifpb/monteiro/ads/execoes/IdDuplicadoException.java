package br.edu.ifpb.monteiro.ads.execoes;

@SuppressWarnings("serial")
public class IdDuplicadoException extends Exception {

	public IdDuplicadoException() {
		super("Ja existe um dado com o id passado!");
	}
	
}
