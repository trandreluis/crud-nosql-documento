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
	 * Definindo o numero maximo de Threads que podem realizar operacoes simultaneas no banco.
	 */
	private static final int NUMERO_DE_THREADS = 100;
	
	/**
	 * Definindo o numero maximo de documentos que uma Thread pode manipular
	 */
	private static final int DOCUMENTOS_POR_THREAD = 5;
	
	/**
	 * Numero maximo de conexoes simultaneas ativas
	 */
	private static final int NUMERO_MAXIMO_DE_CONEXOES = 10;
	
	public ConexaoCouchDB() {
		
		/**
		 * Definindo propriedades da conexao e do banco (nome do banco, endereco IP e porta)
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
	
	public void salvar(Dado dado) {
		dbClient.save(dado);
	}
	
}
