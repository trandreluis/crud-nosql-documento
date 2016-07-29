package br.edu.ifpb.monteiro.ads.model;

/**
 * Dado qualquer utilizado para testes iniciais
 */

public class Dado {

	/**
	 * _id: O atributo deve ser nomeado dessa forma para que o CouchDB utilize o
	 * _id definido pelo sistema e nao gere um automaticamente com um hash do
	 * objeto
	 */
	String _id;

	String nome;
	int tamanho;

	public String get_Id() {
		return _id;
	}

	public void set_Id(String _id) {
		this._id = _id;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return "Nome: " + this.nome + "\n" + "Tamanho: " + this.tamanho;
	}

}
