package br.edu.ifpb.monteiro.ads.view;

import br.edu.ifpb.monteiro.ads.dao.ConexaoCouchDB;
import br.edu.ifpb.monteiro.ads.execoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.execoes.DadoInvalidoException;
import br.edu.ifpb.monteiro.ads.execoes.DadoSemIdException;
import br.edu.ifpb.monteiro.ads.execoes.IdDuplicadoException;
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
		try {
			couchConnection.salvar(dado);
		} catch (DadoInvalidoException exception1) {
			System.out.println(exception1.getMessage());
		} catch (DadoSemIdException exception2) {
			System.out.println(exception2.getMessage());
		} catch (IdDuplicadoException exception3) {
			System.out.println(exception3.getMessage());
		}

		String id = "1";

		try {
			/**
			 * Recuperando Dado buscado e o armazenando.
			 */
			Dado dadoRecuperado = couchConnection.buscarPeloID(id);
			/**
			 * Exibindo Dado recuperado
			 */
			System.out.println(dadoRecuperado);
		} catch (DadoInexistenteException e) {
			System.out.println("Nao existe dado no banco com o ID: "+id);
		}
		
	}
	
}
