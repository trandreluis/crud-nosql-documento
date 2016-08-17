package br.edu.ifpb.monteiro.ads.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.edu.ifpb.monteiro.ads.dao.PessoaDao;
import br.edu.ifpb.monteiro.ads.model.Pessoa;
import br.edu.ifpb.monteiro.ads.view.PainelAddPessoa;

public class OuvinteEditar implements ActionListener{

	private JFrame janela;
	private PessoaDao dao;
	private JTable tabela;
	
	public OuvinteEditar(JFrame janela, PessoaDao dao, JTable tabela){
		this.janela = janela;
		this.dao = dao;
		this.tabela = tabela;
	}
	public void actionPerformed(ActionEvent arg0) {
		Pessoa pessoa = new Pessoa();
		if(tabela.getSelectedRowCount() != 0){
			int linhaSelecionada = tabela.getSelectedRow();
			pessoa = new Pessoa();
			pessoa.set_id(tabela.getValueAt(linhaSelecionada, 0).toString());
			pessoa.setNome(tabela.getValueAt(linhaSelecionada, 1).toString());
			pessoa.setSexo(tabela.getValueAt(linhaSelecionada, 2).toString().toCharArray()[0]); 
			pessoa.setTelefone(tabela.getValueAt(linhaSelecionada, 3).toString());	
		}
		else{
			JOptionPane.showMessageDialog(null, "Selecione a linha do objeto que deseja editar!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		janela.getContentPane().removeAll();
		new PainelAddPessoa(janela, dao, pessoa);
		janela.setVisible(true);
	}
}
