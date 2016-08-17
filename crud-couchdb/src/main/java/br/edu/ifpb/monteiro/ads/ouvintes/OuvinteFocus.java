package br.edu.ifpb.monteiro.ads.ouvintes;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class OuvinteFocus implements FocusListener{
	
	private Object objeto;
	
	public OuvinteFocus(Object objeto) {
		this.objeto = objeto;
	}

	public void focusGained(FocusEvent e) {
		if(objeto instanceof JTextField){
			JTextField area = (JTextField) objeto;
			area.setBorder(new LineBorder(new Color(30, 144, 255)));
		}
	}

	public void focusLost(FocusEvent e) {
		if(objeto instanceof JTextField){
			JTextField area = (JTextField) objeto;
			area.setBorder(new LineBorder(Color.GRAY));
		}
	}
}
