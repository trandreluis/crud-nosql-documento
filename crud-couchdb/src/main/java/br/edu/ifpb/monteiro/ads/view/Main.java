package br.edu.ifpb.monteiro.ads.view;

import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.google.gson.JsonObject;

import br.edu.ifpb.monteiro.ads.dao.PessoaDao;
import br.edu.ifpb.monteiro.ads.execoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.model.Pessoa;

/**
 * Classe principal da aplicacao, agora servindo apenas como teste
 */

public class Main {

	public static void main(String[] args) {

		/**
		 * Criando Dao para manipulacao de objetos do tipo Pessoa
		 */
		PessoaDao dao = new PessoaDao();

		/**
		 * Criacao da pessoa inicial a ser cadastrada
		 */
		Pessoa pessoa = new Pessoa();

		/**
		 * Definicao das propriedades dessa Pessoa (exceto o _rev, que nao deve
		 * ser definido pela aplicacao, mas sim pelo CouchDB)
		 */
		pessoa.set_id("abcd100.098.342-22");
		pessoa.setNome("Andre");
		pessoa.setSexo('M');
		pessoa.setTelefone("(87) 9 9167 - 8707");

		/**
		 * Tentativa de salvar a Pessoa criada anteriormente
		 */
		try {
			System.out.println("\nSALVANDO...");
			dao.salvar(pessoa);
			System.out.println(pessoa);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		/**
		 * Criando uma Pessoa (ainda sem os valres definidos) para receber o
		 * retorno da busca no banco
		 */
		Pessoa pessoaRecuperada = new Pessoa();

		/**
		 * Tentativa de recuperar a Pessoa (e atribuir a pessoa recuperada), no
		 * retorno da busca o campo _rev ja se encontra preenchido, por tanto, a
		 * partir daqui ja podemos trabalhar com ele (ele e necessario para que
		 * possamos atualizar ou excluir)
		 */
		try {
			System.out.println("\nRECUPERANDO...");
			pessoaRecuperada = dao.buscar("100.098.342-22");
			System.out.println(pessoaRecuperada.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		/**
		 * Alterando a propriedade telefone da pessoa que foi recuperada
		 */
		pessoaRecuperada.setTelefone("Fui roubado");

		/**
		 * Tentativa de atualizar o ducumento ja cadastrado, nesta atualizacao
		 * realizamos a alteracao do numero do telefone
		 */
		try {
			System.out.println("\nALTERANDO...");
			dao.atualizar(pessoaRecuperada);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		/**
		 * Recuperando o documento novamente para verificar se o mesmo foi
		 * alterado (sempre passando a chave (_id) - ainda ha de se implementar
		 * o metodo de busca pelo valor)
		 */
		try {
			System.out.println("\nRECUPERANDO NOVAMENTE...");
			pessoaRecuperada = dao.buscar("100.098.342-22");
			System.out.println(pessoaRecuperada.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
		/**
		 * Recuperando todas as pessoas cadastradasno banco
		 */
		try {
			System.out.println("RECUPERANDO TODAS AS PESSOAS...");
			List<Pessoa> pessoas = dao.buscarTodos();

			for(Pessoa p : pessoas) {
				System.out.println(p.toString());
			}
		} catch (DadoInexistenteException e) {
			System.out.println("NAO EXISTEM PESSOAS CADASTRADAS!");
		}
	
		
		/**
		 * Agora apagando o documento referente a Pessoa persistida no banco
		 * criada no inicio da classe
		 */
//		try {
//			System.out.println("APAGANDO...");
//			dao.apagar(pessoaRecuperada);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

	}

}
