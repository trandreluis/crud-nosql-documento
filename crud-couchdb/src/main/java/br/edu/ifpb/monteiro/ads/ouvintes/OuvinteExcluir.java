package br.edu.ifpb.monteiro.ads.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.edu.ifpb.monteiro.ads.dao.PessoaDao;
import br.edu.ifpb.monteiro.ads.execoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.view.TableModelPessoa;

public class OuvinteExcluir implements ActionListener{
	private JTable tabela; 
	private TableModelPessoa modelTable;
	private PessoaDao dao;
	
	public OuvinteExcluir(JTable tabela, TableModelPessoa modelTable, PessoaDao dao){
		this.tabela = tabela;
		this.dao = dao;
		this.modelTable = modelTable;
	}

	public void actionPerformed(ActionEvent e) {
		if(tabela.getSelectedRowCount() != 0){
			int linhaSelecionada = tabela.getSelectedRow();
				int opcao = 0;
				try {
					if(dao.buscar(tabela.getValueAt(linhaSelecionada, 0).toString()) != null){
						opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esta pessoa?");					
					}
				} catch (DadoInexistenteException e4) {
					JOptionPane.showMessageDialog(null, e4.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				if(opcao == 0){
					try {
						dao.apagar(dao.buscar(tabela.getValueAt(linhaSelecionada, 0).toString()));
						modelTable.remover(linhaSelecionada);
						JOptionPane.showMessageDialog(null, "Pessoa removida com sucesso!");
					} catch (DadoInexistenteException e3) {
						JOptionPane.showMessageDialog(null, e3.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					return;
				}
		}
		else{
			JOptionPane.showMessageDialog(null, "Selecione a linha do objeto que deseja excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
