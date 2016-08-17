package br.edu.ifpb.monteiro.ads.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import br.edu.ifpb.monteiro.ads.dao.PessoaDao;
import br.edu.ifpb.monteiro.ads.view.PainelBuscar;

public class OuvintePainelBuscar implements ActionListener{

	private JFrame janela;
	private PessoaDao dao;
	
	public OuvintePainelBuscar(JFrame janela, PessoaDao dao) {
		this.janela = janela;
		this.dao = dao;
	}
	
	public void actionPerformed(ActionEvent e) {
		janela.getContentPane().removeAll();
		new PainelBuscar(janela, dao);
		janela.setVisible(true);
	}
}
