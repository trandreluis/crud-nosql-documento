package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class DadoInexistenteException extends Exception {

	public DadoInexistenteException() {
		super("Nao existe nenhum dado com o id passado!");
	}
	
}
