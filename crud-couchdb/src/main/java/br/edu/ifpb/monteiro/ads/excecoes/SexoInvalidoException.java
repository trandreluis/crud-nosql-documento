package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class SexoInvalidoException extends Exception{
	
	public SexoInvalidoException() {
		super("Sexo inválido!");
	}
}