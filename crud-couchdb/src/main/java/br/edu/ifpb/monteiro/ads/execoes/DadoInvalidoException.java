package br.edu.ifpb.monteiro.ads.execoes;

@SuppressWarnings("serial")
public class DadoInvalidoException extends Exception {

	public DadoInvalidoException() {
		super("Nao existe nenhum dado com o id passado!");
	}
	
}
