package br.edu.ifpb.monteiro.ads.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.edu.ifpb.monteiro.ads.dao.PessoaDao;

/**
 * Classe da janela principal do sistema, que ainda sera feita
 */

@SuppressWarnings("serial")
public class View extends JFrame {
	
	private PessoaDao dao = new PessoaDao();

	public View() {
		super("Cadastrar de Pessoas");
		this.setIconImage(new ImageIcon(getClass().getResource("/images/person.png")).getImage());
		this.setSize(800,600);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		new PainelTabelas(this, dao);
		setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		View v = new View();
	}
	
}
