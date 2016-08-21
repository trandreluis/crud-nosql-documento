package br.edu.ifpb.monteiro.ads.dao;

import javax.swing.JOptionPane;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbException;
import org.lightcouch.CouchDbProperties;

public class ConexaoCouchDB {

	/**
	 * Pegando instancia de cliente do banco CouchDB
	 */
	private CouchDbClient dbClient;

	public ConexaoCouchDB() {

		/**
		 * Definindo propriedades da conexao e do banco (que ele seja criado
		 * caso ainda nao exista, nome do banco, protocolo, endereco IP e porta)
		 */
		CouchDbProperties propriedades = new CouchDbProperties();
		propriedades.setCreateDbIfNotExist(true);
		propriedades.setDbName("crud_bd2");
		propriedades.setProtocol("http");
		propriedades.setHost("127.0.0.1");
		propriedades.setPort(5984);

		/**
		 * Estabalecendo conexao e criando o banco caso ele ainda nao exista
		 */
		try {
			dbClient = new CouchDbClient(propriedades);
		} catch (CouchDbException exc) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível obter uma conexão com o CouchDB, talvez ele não esteja instalado na máquina ou não tenha sido iniciado.");
			System.exit(0);
		}

	}

	/**
	 * Metodo que retorna um client do CouchDB para os Dao's
	 * 
	 * @return Cliente/'connection' do CouchDB
	 */
	public CouchDbClient getCouchDbClient() {
		return this.dbClient;
	}

}
