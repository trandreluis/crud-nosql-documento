package br.edu.ifpb.monteiro.ads.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.ifpb.monteiro.ads.dao.PessoaDao;
import br.edu.ifpb.monteiro.ads.execoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.ouvintes.OuvinteBuscar;
import br.edu.ifpb.monteiro.ads.ouvintes.OuvinteCancelar;
import br.edu.ifpb.monteiro.ads.ouvintes.OuvinteFocus;

@SuppressWarnings("serial")
public class PainelBuscar extends JPanel{
	
	private JTable tabela;
	private JTextField textFieldNormal;
	private JLabel valor;
	private OuvinteFocus ouvinteFocus;
	private JPanel painelBotoes, painel5;
	private Container c;
	private JFrame janela;
	private PessoaDao dao;

	public PainelBuscar(JFrame janela, PessoaDao dao) {
		this.janela = janela;
		
		this.dao = dao;
		
		janela.setSize(800, 600);
		
		janela.setLocationRelativeTo(null);
		
		janela.setResizable(false);
		
		janela.setLayout(new BorderLayout());
		
		janela.setIconImage(new ImageIcon(getClass().getResource("/images/buscar.png")).getImage());
		
		c = janela.getContentPane();
		
		setLayout(new GridLayout(1, 0, 5, 5));
		
		adicionarJTables();
		
		adicionarJPanels();
		
		adicionarJLabels();
		
		adicionarJTextFields();
		
		adicionarJButtons();
		
		c.add(this);
		
		setVisible(true);
	}
	
	private void adicionarJPanels(){
		painel5 = new JPanel();
		painel5.setLayout(new GridLayout(1, 3, 10, 10));
		c.add(painel5, BorderLayout.NORTH);
		
		GridLayout botoes = new GridLayout(1, 1);
		painelBotoes = new JPanel(botoes);
		c.add(painelBotoes, BorderLayout.SOUTH);
	}
	
	private void adicionarJTables(){
		tabela = new JTable();
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setFont(new Font("Times New Roman", 0, 20));
		tabela.setRowHeight(25);
		add(new JScrollPane(tabela));
		janela.setTitle("Buscar Pessoa");
		try {
			tabela.setModel(new TableModelPessoa(dao.buscarTodos()));
		} catch (DadoInexistenteException e) {
			e.printStackTrace();
		}
	}
	
	private void adicionarJLabels(){
		valor = new JLabel(" Informe o CPF da pessoa:");
		valor.setFont(new Font("Times New Roman", 10, 24));
		painel5.add(valor);
	}
	
	private void adicionarJTextFields(){
		try {
			textFieldNormal= new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		textFieldNormal.setBounds(55, 210, 280, 20);
		textFieldNormal.setFont(new Font("Times New Roman", 10, 20));
		ouvinteFocus = new OuvinteFocus(textFieldNormal);
		textFieldNormal.addFocusListener(ouvinteFocus);
		painel5.add(textFieldNormal, BorderLayout.NORTH);
		painel5.repaint();
	}
	
	private void adicionarJButtons(){
		JButton cancelar = new JButton("Cancelar");
		cancelar.setIcon(new ImageIcon(getClass().getResource("/images/cancelar.png")));
		OuvinteCancelar ouvinteCancelar = new OuvinteCancelar(janela, dao);
		cancelar.addActionListener(ouvinteCancelar);
		painelBotoes.add(cancelar);
		JButton buscar = new JButton("Buscar");
		buscar.setIcon(new ImageIcon(getClass().getResource("/images/buscar.png")));
		OuvinteBuscar ouvinteBuscar = new OuvinteBuscar(janela, tabela, textFieldNormal, dao);
		buscar.addActionListener(ouvinteBuscar);
		painel5.add(buscar);
	}
}
