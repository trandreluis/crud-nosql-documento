package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class TelefoneInvalidoException extends Exception{
	
	public TelefoneInvalidoException() {
		super("Telefone inválido!");
	}
}