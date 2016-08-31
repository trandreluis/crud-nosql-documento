package br.edu.ifpb.monteiro.ads.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.monteiro.ads.excecoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.excecoes.DadoInvalidoException;
import br.edu.ifpb.monteiro.ads.excecoes.IdDuplicadoException;
import br.edu.ifpb.monteiro.ads.excecoes.IdInvalidoException;
import br.edu.ifpb.monteiro.ads.excecoes.NomeInvalidoException;
import br.edu.ifpb.monteiro.ads.excecoes.SexoInvalidoException;
import br.edu.ifpb.monteiro.ads.excecoes.TelefoneInvalidoException;
import br.edu.ifpb.monteiro.ads.model.Pessoa;

import com.google.gson.JsonObject;

/**
 * Classe Dao de manipulacao dos objetos do tipo Pessoa
 */
public class PessoaDao extends GenericDao<Pessoa, String> {

	/**
	 * Metodo salvar que recebe uma pessoa e a mapeia - o mapeamento é feito
	 * pegando os atributos existentes no objeto passado e criando um documento
	 * com os respectivos campos e valores que os atributos vierem a possuir
	 * 
	 * @param dado
	 *            Uma pessoa que o metodo recebe para persistir (salvar) no
	 *            banco de dados.
	 * @throws DadoInvalidoException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 * @throws IdInvalidoException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 * @throws IdDuplicadoException
	 *             Excecao a ser lancada quando a Pessoa passada tiver o mesmo
	 *             _id de um documento ja cadastrado
	 * @throws TelefoneInvalidoException 
	 * @throws SexoInvalidoException 
	 * @throws NomeInvalidoException 
	 * @throws DadoSemRevException
	 *             Excecao possivelmente proveniente do metodo de validacao
	 *             validarObjeto()
	 */
	@Override
	public void salvar(Pessoa dado)throws IdInvalidoException, IdDuplicadoException, NomeInvalidoException, SexoInvalidoException, TelefoneInvalidoException {

		validarObjeto(dado);

		if (existe(dado.get_id()))
			throw new IdDuplicadoException();

		dbClient.save(dado);

	}

	/**
	 * Metodo que recebe uma pessoa e a atualiza no banco (faz isso pegando os valores do _id e do _rev)
	 */
	@Override
	public void atualizar(Pessoa dado)
			throws DadoInexistenteException, IdDuplicadoException, IdInvalidoException, NomeInvalidoException, SexoInvalidoException, TelefoneInvalidoException {
		
		validarObjeto(dado);
		
		if(buscar(dado.get_id().toString()) != null && !buscar(dado.get_id().toString()).get_id().equals(dado.get_id())){
			throw new IdDuplicadoException();
		}
		dbClient.update(dado);

	}

	/**
	 * Metodo que recebe uma pessoa e a apaga do banco (faz isso pegando os valores do _id e do _rev)
	 */
	@Override
	public void apagar(Pessoa dado) throws DadoInexistenteException{

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
	 * 
	 * @throws DadoInexistenteException
	 */
	@Override
	public List<Pessoa> buscarTodos() throws DadoInexistenteException{

		/**
		 * Recuperando todos os documentos cadastrados no banco. Os documentos
		 * sao trazidos com JSON's.
		 */
		List<JsonObject> todosOsDocumentos = dbClient.view("_all_docs").query(JsonObject.class);

		/**
		 * Iniciando ArrayList que recebera as pessoas obtidas realizando a
		 * busca pelos _id dos JSON's.
		 */
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		/**
		 * Percorrendo todos os documentos (JSON's) recuperados do banco...
		 */
		for (JsonObject json : todosOsDocumentos) {
			/**
			 * Obtendo _id (valor de um campo especifico) do documento JSON. O
			 * metodo json.get("id") retorna o valor bruto deste campo, ja o
			 * metodo getAsString() e responsavel por pegar o valor bruto obtido
			 * e trata-lo como uma String (basicamente convertê-lo)
			 */
			String id = json.get("id").getAsString();
			/**
			 * Utilizando o metodo de busca padrao (que retorna somente um
			 * resultado) para buscar pessoa por pessoa no banco, passando o _id
			 * obtido anteriormente. Ao recuperar a pessoa, adiciona-se a lista
			 * de pessoas que sera retornada.
			 */
			pessoas.add(buscar(id));
		}

		if(pessoas.isEmpty()){
			return pessoas;
		}
		else{
			return pessoas;
		}
	}
}
