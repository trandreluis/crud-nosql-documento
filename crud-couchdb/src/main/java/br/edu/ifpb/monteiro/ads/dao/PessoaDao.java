package br.edu.ifpb.monteiro.ads.dao;

import java.util.ArrayList;

import br.edu.ifpb.monteiro.ads.execoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.execoes.DadoInvalidoException;
import br.edu.ifpb.monteiro.ads.execoes.DadoSemIdException;
import br.edu.ifpb.monteiro.ads.execoes.IdDuplicadoException;
import br.edu.ifpb.monteiro.ads.model.Pessoa;

public class PessoaDao extends GenericDao<Pessoa, String> {

	/**
	 * Metodo salvar que recebe uma pessoa e a mapeia O mapeamento é feito
	 * pegando os atributos existentes no objeto passado e criando um documento
	 * com os respectivos campos e valores que os atributos vierem a possuir
	 * 
	 * @param dado
	 *            Uma pessoa que o metodo recebe para persistir (salvar) no
	 *            banco de dados.
	 * @throws DadoInvalidoException
	 * @throws DadoSemIdException
	 * @throws IdDuplicadoException
	 * @throws DadoSemRevException
	 */
	@Override
	public void salvar(Pessoa dado) throws DadoInvalidoException, DadoSemIdException, IdDuplicadoException {

		validarObjeto(dado);

		if (existe(dado.get_id()))
			throw new IdDuplicadoException();

		dbClient.save(dado);

	}

	public void atualizar(Pessoa dado)
			throws DadoInexistenteException, IdDuplicadoException, DadoSemIdException, DadoInvalidoException {

		validarObjeto(dado);
		dbClient.update(dado);

	}

	@Override
	public void apagar(Pessoa dado) throws DadoInvalidoException, DadoSemIdException, DadoInexistenteException {

		validarObjeto(dado);

		if (!existe(dado.get_id()))
			throw new DadoInexistenteException();

		dbClient.remove(dado);

	}

	@Override
	public Pessoa buscar(String id) throws DadoInexistenteException {

		if (!existe(id))
			throw new DadoInexistenteException();

		Pessoa dado = dbClient.find(Pessoa.class, id);

		return dado;

	}

	@Override
	public ArrayList<Pessoa> buscarTodos() {

		return null;
	}

}
