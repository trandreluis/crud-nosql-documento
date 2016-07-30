package br.edu.ifpb.monteiro.ads.execoes;

@SuppressWarnings("serial")
public class DadoSemIdException extends Exception {

	public DadoSemIdException() {
		super("O dado passado nao possui um ID!");
	}
	
}
