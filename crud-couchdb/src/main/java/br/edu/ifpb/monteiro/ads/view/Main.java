package br.edu.ifpb.monteiro.ads.view;

import br.edu.ifpb.monteiro.ads.dao.ConexaoCouchDB;
import br.edu.ifpb.monteiro.ads.model.Dado;

/**
 * Classe principal da aplicacao
 */

public class Main {

	public static void main(String[] args) {
		
		/**
		 * Criando conexao (ao instanciar essa classe seu construtor criara a conexao)
		 */
		ConexaoCouchDB couchConnection = new ConexaoCouchDB();
		
		/**
		 * Criando um dado de exemplo
		 */
		Dado dado = new Dado();

		/**
		 * Definindo o _id deste dado.
		 * Este _id deve ser unico para ser salvo no banco.
		 */
		dado.set_Id("1");
		
		/**
		 * Definindo demais valos deste dado
		 */
		dado.setNome("Andre");
		dado.setTamanho(19);
		
		/**
		 * Salvando os dados utilizando a conexao criada.
		 * 
		 * Aqui o metodo salvar recebe um objeto qualquer.
		 * Apos receber o objeto, o proprio CouchDB faz o mapeamento
		 * (pegando seus atributos e os respectivos valores de cada um)
		 * Apos mapear, o CouchDB persiste (salva) o objeto no banco
		 */
		couchConnection.salvar(dado);
		
		/**
		 * Recuperando Dado buscado e o armazenando.
		 */
		Dado dadoRecuperado = couchConnection.buscarPeloID("1");
		
		/**
		 * Exibindo Dado recuperado
		 */
		System.out.println(dadoRecuperado.toString());
	
	}
	
}
