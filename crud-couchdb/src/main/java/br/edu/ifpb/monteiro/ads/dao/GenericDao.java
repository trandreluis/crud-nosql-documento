package br.edu.ifpb.monteiro.ads.dao;

import java.util.ArrayList;

/**
 * Dao Generico que ira fornecer as operacoes basicas de insercao, edicao, exclusao e busca no banco.
 */

public interface GenericDao<T, ID> {

	void salvar(T dado);
	
	void atualizar(T dado, ID id);
	
	void apagar(ID dado);
	
	T buscar(ID dado);
	
	ArrayList<T> buscarTodos();
	
}
