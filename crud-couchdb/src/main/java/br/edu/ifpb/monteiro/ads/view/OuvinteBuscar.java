package br.edu.ifpb.monteiro.ads.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.ifpb.monteiro.ads.dao.PessoaDao;
import br.edu.ifpb.monteiro.ads.execoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.model.Pessoa;

public class OuvinteBuscar implements ActionListener{
	
	private JFrame janela;
	private JTable tabela;
	private JTextField textFieldNormal;
	private PessoaDao dao;
	
	public OuvinteBuscar(JFrame janela, JTable tabela, JTextField textFieldNormal, PessoaDao dao) {
		this.janela = janela;
		this.tabela = tabela;
		this.textFieldNormal = textFieldNormal;
		this.dao = dao;
	}

	public void actionPerformed(ActionEvent e) {
		String valor = null;
		if(textFieldNormal.getText() != null && !textFieldNormal.getText().equals("")){
			valor = textFieldNormal.getText();
		}
			
		try {
			ArrayList<Pessoa> pessoa = new ArrayList<Pessoa>();
			System.out.println(dao.buscar(valor).get_id());
			pessoa.add(dao.buscar(valor));
			tabela.setModel(new TableModelPessoa(pessoa));
			janela.setVisible(true);
		} catch (DadoInexistenteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
