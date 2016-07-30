package br.edu.ifpb.monteiro.ads.execoes;

@SuppressWarnings("serial")
public class DadoSemIdException extends Exception {

	public DadoSemIdException() {
		super("Nao existe nenhum dado com o id passado!");
	}
	
}
