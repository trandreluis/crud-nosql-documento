package br.edu.ifpb.monteiro.ads.dao;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;

import br.edu.ifpb.monteiro.ads.execoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.execoes.DadoInvalidoException;
import br.edu.ifpb.monteiro.ads.execoes.DadoSemIdException;
import br.edu.ifpb.monteiro.ads.execoes.IdDuplicadoException;
import br.edu.ifpb.monteiro.ads.model.Pessoa;

public class ConexaoCouchDB {

	/**
	 * Pegando instancia de cliente do banco CouchDB
	 */
	private static CouchDbClient dbClient;

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
	 * @throws DadoInvalidoException
	 * @throws DadoSemIdException
	 * @throws IdDuplicadoException
	 */
	public void salvar(Pessoa dado) throws DadoInvalidoException, DadoSemIdException, IdDuplicadoException {

		if (dado != null) {
			if (dado.get_id() != null) {
				if (!existe(dado.get_id())) {

					/**
					 * Este metodo, ao tentar salvar o objeto, retorna uma
					 * Response (resposta http do banco de dados para a
					 * solicitacao). No entanto, ela nao interessa para esta
					 * aplicacao.
					 */
					dbClient.save(dado);

				} else {
					throw new IdDuplicadoException();
				}

			} else {
				throw new DadoSemIdException();
			}
		} else {
			throw new DadoInvalidoException();
		}

	}

	/**
	 * Metodo que realiza a busca dos dados cadastrados no BD de acordo com o
	 * _id passado
	 * 
	 * @param id
	 *            _id do objeto buscado
	 * @return objeto do tipo Dado encontrado
	 * @throws DadoInexistenteException
	 *             Excecao lancada quando nao existe dado no banco com o id
	 *             passado
	 */
	public Pessoa buscarPeloID(String id) throws DadoInexistenteException {

		if (!existe(id)) {
			throw new DadoInexistenteException();
		}

		Pessoa dado = dbClient.find(Pessoa.class, id);
		return dado;

	}

	/**
	 * Metodo que verifica no banco a existencia de um documento com o ID
	 * 
	 * @param id
	 *            ID do dado a ser verificado
	 * @return Se ja existe ou nao um documento ja cadastrado com o ID passado
	 */

	public boolean existe(String id) {

		return dbClient.contains(id);

	}

}
