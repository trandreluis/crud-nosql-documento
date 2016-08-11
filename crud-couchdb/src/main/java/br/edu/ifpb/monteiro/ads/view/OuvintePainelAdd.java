package br.edu.ifpb.monteiro.ads.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import br.edu.ifpb.monteiro.ads.dao.PessoaDao;

public class OuvintePainelAdd implements ActionListener{

	private JFrame janela;
	private PessoaDao dao;
	
	public OuvintePainelAdd(JFrame janela, PessoaDao dao){
		this.janela = janela;
		this.dao = dao;
	}
	public void actionPerformed(ActionEvent arg0) {
		janela.getContentPane().removeAll();
		new PainelAddPessoa(janela, dao, null);
		janela.setVisible(true);
	}
}