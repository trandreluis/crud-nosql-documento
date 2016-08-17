package br.edu.ifpb.monteiro.ads.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.edu.ifpb.monteiro.ads.dao.PessoaDao;
import br.edu.ifpb.monteiro.ads.execoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.ouvintes.Botoes;
import br.edu.ifpb.monteiro.ads.ouvintes.OuvinteEditar;
import br.edu.ifpb.monteiro.ads.ouvintes.OuvinteExcluir;
import br.edu.ifpb.monteiro.ads.ouvintes.OuvintePainelAdd;
import br.edu.ifpb.monteiro.ads.ouvintes.OuvintePainelBuscar;

@SuppressWarnings("serial")
public class PainelTabelas extends JPanel{
	
	private JTable tabela;
	private Container c;
	private TableModelPessoa modelPessoa;
	private PessoaDao dao;

	public PainelTabelas(JFrame janela, PessoaDao dao) {
		this.dao = dao;
		this.setLayout(new BorderLayout());
		janela.setTitle("Cadastro de Pessoas");
		janela.setLocationRelativeTo(null);
		janela.setResizable(true);
		janela.setIconImage(new ImageIcon(getClass().getResource("/images/person.png")).getImage());
		c = janela.getContentPane();

		c.removeAll();
		c.add(new JScrollPane(getTabelaPessoa()), BorderLayout.CENTER);
		OuvintePainelAdd ouvinteAdd = new OuvintePainelAdd(janela,dao);
		OuvinteExcluir ouvinteExcluir = new OuvinteExcluir(tabela, modelPessoa, dao);
		OuvintePainelBuscar ouvinteBuscar = new OuvintePainelBuscar(janela, dao);
		OuvinteEditar ouvinteEditar = new OuvinteEditar(janela, dao, tabela);
		c.add(new Botoes().adicionarButtonsSouth(ouvinteAdd, ouvinteExcluir, ouvinteBuscar, ouvinteEditar), BorderLayout.SOUTH);

		janela.setVisible(true);
	}
	private JTable getTabelaPessoa(){
		if (tabela == null) {
			tabela = new JTable();
			tabela.getTableHeader().setReorderingAllowed(false);
			tabela.setFont(new Font("Times New Roman", 0, 20));
			tabela.setRowHeight(25);
			try {
				tabela.setModel(modelPessoa = new TableModelPessoa(dao.buscarTodos()));
			} catch (DadoInexistenteException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		return tabela;
	}
}