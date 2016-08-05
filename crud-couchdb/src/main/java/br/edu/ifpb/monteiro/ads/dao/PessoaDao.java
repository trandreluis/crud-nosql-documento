package br.edu.ifpb.monteiro.ads.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.edu.ifpb.monteiro.ads.execoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.execoes.DadoInvalidoException;
import br.edu.ifpb.monteiro.ads.execoes.DadoSemIdException;
import br.edu.ifpb.monteiro.ads.execoes.IdDuplicadoException;
import br.edu.ifpb.monteiro.ads.model.Pessoa;
import netscape.javascript.JSObject;

/**
 * Classe Dao de manipulacao dos objetos do tipo Pessoa
 */
public class PessoaDao extends GenericDao<Pessoa, String> {

	/**
	 * Metodo salvar que recebe uma pessoa e a mapeia - o mapeamento � feito
	 * pegando os atributos existentes no objeto passado e criando um documento
	 * com os respectivos campos e valores que os atributos vierem a possuir
	 * 
	 * @param dado
	 *            Uma pessoa que o metodo recebe para persistir (salvar) no
	 *            banco de dados.
	 * @throws DadoInvalidoException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 * @throws DadoSemIdException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 * @throws IdDuplicadoException
	 *             Excecao a ser lancada quando a Pessoa passada tiver o mesmo
	 *             _id de um documento ja cadastrado
	 * @throws DadoSemRevException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 */
	@Override
	public void salvar(Pessoa dado) throws DadoInvalidoException, DadoSemIdException, IdDuplicadoException {

		validarObjeto(dado);

		if (existe(dado.get_id()))
			throw new IdDuplicadoException();

		dbClient.save(dado);

	}

	/**
	 * Metodo que recebe uma pessoa e a atualiza no banco (faz isso pegando os
	 * valores do _id e do _rev)
	 */
	@Override
	public void atualizar(Pessoa dado)
			throws DadoInexistenteException, IdDuplicadoException, DadoSemIdException, DadoInvalidoException {

		validarObjeto(dado);
		dbClient.update(dado);

	}

	/**
	 * Metodo que recebe uma pessoa e a apaga do banco (faz isso pegando os
	 * valores do _id e do _rev)
	 */
	@Override
	public void apagar(Pessoa dado) throws DadoInvalidoException, DadoSemIdException, DadoInexistenteException {

		validarObjeto(dado);

		if (!existe(dado.get_id()))
			throw new DadoInexistenteException();

		dbClient.remove(dado);

	}

	/**
	 * Metodo que realiza a busca nos documentos cadatrados no banco - faz a
	 * busca pelo _id passado
	 */
	@Override
	public Pessoa buscar(String id) throws DadoInexistenteException {

		if (!existe(id))
			throw new DadoInexistenteException();

		Pessoa dado = dbClient.find(Pessoa.class, id);

		return dado;

	}

	/**
	 * Metodo que devera retornar todas os documentos referentes a Pessoas
	 * cadastradas no banco
	 * @throws DadoInexistenteException 
	 */
	@Override
	public List<Pessoa> buscarTodos() throws DadoInexistenteException {

		List<JsonObject> todosOsDocumentos = dbClient.view("_all_docs").query(JsonObject.class);

		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		for (JsonObject json : todosOsDocumentos) {
			String id = json.get("id").getAsString();
			System.out.println(id);
			System.out.println(existe(id));
			pessoas.add(buscar(id));
		}

		return pessoas;
	}

}
