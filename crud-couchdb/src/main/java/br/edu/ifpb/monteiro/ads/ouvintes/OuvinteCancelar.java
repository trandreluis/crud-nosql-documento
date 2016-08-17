package br.edu.ifpb.monteiro.ads.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import br.edu.ifpb.monteiro.ads.dao.PessoaDao;
import br.edu.ifpb.monteiro.ads.view.PainelTabelas;

public class OuvinteCancelar implements ActionListener{

	private JFrame janela;
	private PessoaDao dao;
	
	public OuvinteCancelar(JFrame janela, PessoaDao dao){
		this.janela = janela;
		this.dao = dao;
	}
	public void actionPerformed(ActionEvent arg0) {
		janela.getContentPane().removeAll();
		new PainelTabelas(janela, dao);
		janela.setVisible(true);
	}
}