package br.edu.ifpb.monteiro.ads.execoes;

@SuppressWarnings("serial")
public class SexoInvalidoException extends Exception{
	
	public SexoInvalidoException() {
		super("Sexo inválido!");
	}
}