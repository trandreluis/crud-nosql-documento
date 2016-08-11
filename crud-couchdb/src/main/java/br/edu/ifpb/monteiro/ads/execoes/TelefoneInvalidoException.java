package br.edu.ifpb.monteiro.ads.execoes;

@SuppressWarnings("serial")
public class TelefoneInvalidoException extends Exception{
	
	public TelefoneInvalidoException() {
		super("Telefone inválido!");
	}
}