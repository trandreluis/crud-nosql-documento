package br.edu.ifpb.monteiro.ads.dao;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;

import br.edu.ifpb.monteiro.ads.model.Dado;

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

		/**
		 * Este metodo, ao tentar salvar o objeto, retorna uma Response
		 * (resposta http do banco de dados para a solicitacao).
		 * No entanto, ela nao interessa para esta aplicacao.
		 */
		dbClient.save(dado);

	}

	/**
	 * Metodo que realiza a busca dos dados cadastrados no BD de acordo com o _id passado
	 * 
	 * @param id _id do objeto buscado
	 * @return objeto do tipo Dado encontrado
	 */
	public Dado buscarPeloID(String id) {
		
		Dado dado = dbClient.find(Dado.class, id);
		return dado;
		
	}

}
