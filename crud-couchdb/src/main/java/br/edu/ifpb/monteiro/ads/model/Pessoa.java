package br.edu.ifpb.monteiro.ads.model;

/**
 * Classe do modelo de dados
 */

public class Pessoa {

	/**
	 * O _id da pessoa é o seu CPF, a variavel está nomeada desta forma para que
	 * o CouchDB já a interprete como o ID do docuemnto
	 */
	private String _id;
	private String nome;
	private char sexo;
	private String telefone;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String toString() {
		return "ID-CPF: " + this._id + "\n" + "Nome: " + this.nome + "\n" + "Sexo: " + this.sexo + "\n" + "Telefone: "
				+ this.telefone;
	}

}
