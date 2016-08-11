package br.edu.ifpb.monteiro.ads.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.ifpb.monteiro.ads.dao.PessoaDao;
import br.edu.ifpb.monteiro.ads.execoes.DadoInexistenteException;
import br.edu.ifpb.monteiro.ads.execoes.IdDuplicadoException;
import br.edu.ifpb.monteiro.ads.execoes.IdInvalidoException;
import br.edu.ifpb.monteiro.ads.execoes.NomeInvalidoException;
import br.edu.ifpb.monteiro.ads.execoes.SexoInvalidoException;
import br.edu.ifpb.monteiro.ads.execoes.TelefoneInvalidoException;
import br.edu.ifpb.monteiro.ads.model.Pessoa;

@SuppressWarnings("serial")
public class PainelAddPessoa extends JPanel{
	
	private JTextField areaCpf, areaNome, areaTelefone;
	private Container c;
	private JFrame janela;
	private ButtonGroup buttonGroup;
	private JRadioButton M, F;
	private JPanel painel;
	private OuvinteFocus ouvinteFocus;
	private PessoaDao dao;
	private String funcao;
	private Pessoa pessoa;
	private JButton btAdd;
	private JLabel titulo;
	
	public PainelAddPessoa(JFrame janela, PessoaDao dao, Pessoa pessoa){
		
		this.janela = janela;
		
		this.pessoa = pessoa;
		
		this.dao = dao;
		
		this.janela.setSize(800,600);
		
		this.janela.setLocationRelativeTo(null);
		
		this.janela.setResizable(false);
		
		this.janela.setLayout(new BorderLayout());
		
		this.painel = new JPanel();
		
		buttonGroup = new ButtonGroup();
		
		janela.setTitle("Adicionar Pessoa");
		
		janela.setIconImage(new ImageIcon(getClass().getResource("/images/add.png")).getImage());
		
		painel.setSize(800,600);
		
		c = janela.getContentPane();
		
		painel.setLayout(null);
		
		
		adicionarJLabels();
		
		adicionarJButtons();
		
		adicionarJRadioButtons();
		
		adicionarJTextFields();
		
		c.add(painel);

		setarCampos();
		
		setVisible(true);
	}
	
	private void adicionarJTextFields() {
		try {
			areaCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null,
					"Preencha de acordo com o formato ###.###.###-##", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		ouvinteFocus = new OuvinteFocus(areaCpf);
		areaCpf.addFocusListener(ouvinteFocus);
		areaCpf.setBounds(365, 120, 320, 25);
		areaCpf.setFont(new Font("Times New Roman", 0, 20));
		areaCpf.setToolTipText("Exemplo: 111.222.333-44");
		painel.add(areaCpf);
		
		areaNome = new JTextField();
		ouvinteFocus = new OuvinteFocus(areaNome);
		areaNome.addFocusListener(ouvinteFocus);
		areaNome.setBounds(375, 160, 310, 25);
		areaNome.setFont(new Font("Times New Roman", 0, 20));
		areaNome.setToolTipText("Exemplo: Astrogildo");
		painel.add(areaNome);
		
		try {
			areaTelefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null,
					"Preencha de acordo com o formato (##) #####-####", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		ouvinteFocus = new OuvinteFocus(areaTelefone);
		areaTelefone.addFocusListener(ouvinteFocus);
		areaTelefone.setBounds(400, 260, 285, 25);
		areaTelefone.setFont(new Font("Times New Roman", 0, 20));
		areaTelefone.setToolTipText("Exemplo: (87) 99999-9999");
		painel.add(areaTelefone);
	}

	private void adicionarJRadioButtons() {
		M = new JRadioButton();
		M.setBounds(400, 205, 20, 20);
		buttonGroup.add(M);
		painel.add(M);
		
		F = new JRadioButton();
		F.setBounds(475, 205, 20, 20);
		buttonGroup.add(F);
		painel.add(F);
	}

	private void adicionarJButtons() {
		btAdd = new JButton("Adicionar");
		OuvinteAddPessoa ouvintePessoa = new OuvinteAddPessoa(janela);
		btAdd.addActionListener(ouvintePessoa);
		btAdd.setIcon(new ImageIcon(getClass().getResource("/images/ok.png")));
		btAdd.setBounds(565, 330, 120, 30);
		painel.add(btAdd);
		JButton btCancel = new JButton("Cancelar");
		OuvinteCancelar ouvinteCancelar = new OuvinteCancelar(janela, dao);
		btCancel.addActionListener(ouvinteCancelar);
		btCancel.setIcon(new ImageIcon(getClass().getResource("/images/cancelar.png")));
		btCancel.setBounds(100, 330, 120, 30);
		painel.add(btCancel);
	}

	private void adicionarJLabels() {
		titulo = new JLabel("Adicionar Pessoa");
		titulo.setFont(new Font("Times New Roman", 0, 40));
		titulo.setBounds(270, 20, 280, 50);
		painel.add(titulo);
		
		JLabel cpf = new JLabel("Informe o CPF da pessoa:*");
		cpf.setFont(new Font("Times New Roman", 0, 24));
		cpf.setBounds(100, 120, 265, 30);
		painel.add(cpf);
		
		JLabel nome = new JLabel("Informe o nome da pessoa:*");
		nome.setFont(new Font("Times New Roman", 0, 24));
		nome.setBounds(100, 160, 275, 30);
		painel.add(nome);
		
		JLabel sexo = new JLabel("Informe o sexo da pessoa:*");
		sexo.setFont(new Font("Times New Roman", 0, 24));
		sexo.setBounds(100, 200, 265, 30);
		painel.add(sexo);
		
		JLabel telefone = new JLabel("Informe o telefone da pessoa:*");
		telefone.setFont(new Font("Times New Roman", 0, 24));
		telefone.setBounds(100, 260, 300, 30);
		painel.add(telefone);
		
		JLabel M = new JLabel("M");
		M.setFont(new Font("Times New Roman", 0, 24));
		M.setBounds(400, 230, 50, 20);
		painel.add(M);
		
		JLabel F = new JLabel("F");
		F.setFont(new Font("Times New Roman", 0, 24));
		F.setBounds(478, 230, 50, 20);
		painel.add(F);
	}
	
	public void setarCampos(){
		if(pessoa != null){
			btAdd.setText("Editar");
			titulo.setText("Editar Pessoa");
			areaCpf.setText(pessoa.get_id());
			areaCpf.setEditable(false);
			areaNome.setText(pessoa.getNome());
			areaTelefone.setText(pessoa.getTelefone());
			btAdd.setIcon(new ImageIcon(getClass().getResource("/images/edit.png")));
			janela.setIconImage(new ImageIcon(getClass().getResource("/images/edit.png")).getImage());
			janela.setTitle("Editar Pessoa");
			funcao = "editada";
			if(pessoa.getSexo() == 'M'){
				M.setSelected(true);
			}
			else{
				F.setSelected(true);
			}
		}
		else{
			funcao = "adicionada";
		}
	}
	
	private class OuvinteAddPessoa implements ActionListener{
		char sexo;
		
		JFrame janela;
		
		public OuvinteAddPessoa(JFrame janela) {
			this.janela = janela;
		}

		public void actionPerformed(ActionEvent e) {
			
			if((areaCpf.getText() == null && areaNome.getText() == null && areaTelefone.getText() == null) || 
					(areaNome.getText().trim().equals("") && areaCpf.getText().trim().equals("") && 
							areaTelefone.getText().trim().equals(""))){
				JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(M.isSelected()){
				sexo = 'M';
			}
			
			if(F.isSelected()){
				sexo = 'F';
			}
			
			try {
				if(pessoa != null){
					try {
						Pessoa pessoa1 = dao.buscar(pessoa.get_id());
						pessoa1.setNome(areaNome.getText().toString());
						pessoa1.setSexo(sexo);
						pessoa1.setTelefone(areaTelefone.getText().toString());
						dao.atualizar(pessoa1);
						JOptionPane.showMessageDialog(null, "Pessoa "+funcao+" com sucesso!");
						new PainelTabelas(janela, dao);
					} catch (DadoInexistenteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					Pessoa pessoa = new Pessoa(areaCpf.getText().toString(), areaNome.getText().toString(), sexo, areaTelefone.getText().toString());
					dao.salvar(pessoa);
					JOptionPane.showMessageDialog(null, "Pessoa "+funcao+" com sucesso!");
					new PainelTabelas(janela, dao);
				}
			} catch (IdInvalidoException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				areaCpf.setText("");
			} catch (IdDuplicadoException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				areaCpf.setText("");
			} catch (NomeInvalidoException e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				areaNome.setText("");
			} catch (SexoInvalidoException e4) {
				JOptionPane.showMessageDialog(null, e4.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			} catch (TelefoneInvalidoException e5) {
				JOptionPane.showMessageDialog(null, e5.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				areaTelefone.setText("");
			}
		}
	}
}