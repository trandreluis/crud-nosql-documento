package br.edu.ifpb.monteiro.ads.dao;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;

public class ConexaoCouchDB {

	/**
	 * Pegando instancia de cliente do banco CouchDB
	 */
	private static CouchDbClient dbClient;

	/**
	 * Numero maximo de conexoes simultaneas ativas
	 */
	private static final int NUMERO_MAXIMO_DE_CONEXOES = 10;

	public ConexaoCouchDB() {

		/**
		 * Definindo propriedades da conexao e do banco (nome do banco, endereco
		 * IP e porta)
		 */
		CouchDbProperties propriedades = new CouchDbProperties();
		propriedades.setCreateDbIfNotExist(true);
		propriedades.setDbName("crud_bd2");
		propriedades.setProtocol("http");
		propriedades.setHost("127.0.0.1");
		propriedades.setPort(5984);
		propriedades.setMaxConnections(NUMERO_MAXIMO_DE_CONEXOES);

		/**
		 * Estabalecendo conexao e criando o banco caso ele ainda nao exista
		 */
		dbClient = new CouchDbClient(propriedades);

	}

	/**
	 * Metodo salvar que recebe um objeto (Object) qualquer e o mapeia O
	 * mapeamento é feito pegando os atributos existentes no objeto passado e
	 * criando um documento com os respectivos campos e valores que os atributos
	 * vierem a possuir
	 * 
	 * @param dado
	 *            Um tipo de dado qualquer que o metodo espera para persistir
	 *            (salvar) no banco de dados.
	 */
	public void salvar(Object dado) {
		dbClient.save(dado);
	}

}
