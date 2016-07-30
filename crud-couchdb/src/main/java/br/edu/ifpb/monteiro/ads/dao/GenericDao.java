package br.edu.ifpb.monteiro.ads.dao;

import java.util.ArrayList;

import org.lightcouch.CouchDbClient;

import br.edu.ifpb.monteiro.ads.execoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.execoes.DadoInvalidoException;
import br.edu.ifpb.monteiro.ads.execoes.DadoSemIdException;
import br.edu.ifpb.monteiro.ads.execoes.IdDuplicadoException;
import br.edu.ifpb.monteiro.ads.model.Pessoa;

/**
 * Dao Generico que ira fornecer as operacoes basicas de insercao, busca,
 * edicao, e exclusao no banco.
 */

public abstract class GenericDao<T, ID> {

	/**
	 * 'Conexao' com o CouchDB
	 */
	protected CouchDbClient dbClient;

	/**
	 * Metodo construtor que inicializa o cliente do banco pegando uma conexao
	 * gerada pela classe ConexaoCouchDB
	 */
	public GenericDao() {
		this.dbClient = new ConexaoCouchDB().getCouchDbClient();
	}

	/**
	 * Metodo abstrato para realizar a persistencia de um dado generico no banco
	 * 
	 * @param dado
	 *            Dado de tipo generico que sera persistido
	 * @throws DadoInvalidoException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 * @throws DadoSemIdException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 * @throws IdDuplicadoException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 */
	abstract void salvar(T dado) throws DadoInvalidoException, DadoSemIdException, IdDuplicadoException;

	/**
	 * Metodo responsavel por atualizar o documento passado
	 * 
	 * @param dado
	 *            Dado de tipo generico que sera atualizado
	 * @throws DadoInexistenteException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 * @throws IdDuplicadoException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 * @throws DadoSemIdException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 * @throws DadoInvalidoException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 */
	abstract void atualizar(T dado)
			throws DadoInexistenteException, IdDuplicadoException, DadoSemIdException, DadoInvalidoException;

	/**
	 * Metodo responsavel por realizar e remocao de um tipio de dado generico
	 * 
	 * @param dado
	 *            Dado de tipo generico que sera deletado
	 * @throws DadoInvalidoException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 * @throws DadoSemIdException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 * @throws DadoInexistenteException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 */
	abstract void apagar(T dado) throws DadoInvalidoException, DadoSemIdException, DadoInexistenteException;

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
	abstract T buscar(ID dado) throws DadoInexistenteException;

	/**
	 * Metodo que sera responsavel por relizar a busca de todos os dados de um
	 * determinado tipo T
	 */
	abstract ArrayList<T> buscarTodos();

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

	/**
	 * Metodo responsavel por validar um objeto com o qual se quer realizar
	 * algumas operacoes
	 * 
	 * @param dado
	 *            ado de tipo generico que sera validado
	 * @throws DadoInvalidoException
	 *             Excecao que deve ser lancada quando o dado passado como
	 *             parametro e nulo
	 * @throws DadoSemIdException
	 *             Excecao que pode ser lancada quando o dado passado como
	 *             parametro tem o _id nulo
	 */
	public void validarObjeto(Pessoa dado) throws DadoInvalidoException, DadoSemIdException {

		if (dado == null) {
			throw new DadoInvalidoException();
		} else if (dado.get_id() == null) {
			throw new DadoSemIdException();
		}

	}

}
