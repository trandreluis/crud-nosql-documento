package br.edu.ifpb.monteiro.ads.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpb.monteiro.ads.model.Pessoa;

@SuppressWarnings("serial")
public class TableModelPessoa extends AbstractTableModel{

	private List<Pessoa> linhas;
	private String[] colunas = new String[]{"CPF", "Nome", "Sexo", "Telefone"};
	private final int cpf = 0, nome = 1, sexo = 2, telefone = 3;
	
	public TableModelPessoa(List<Pessoa> linhas){
		this.linhas = linhas;
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
	public Class<?> getColumnClass(int indiceColuna) {
		switch(indiceColuna){
		case cpf:
			return String.class;
		case nome:
			return String.class;
			
		case sexo:
			return char.class;
			
		case telefone:
			return String.class;
			
		default:
			throw new IndexOutOfBoundsException("Índice da coluna fora dos limites");
		}
	}
	
	public Object getValueAt(int linha, int coluna) {
		Pessoa pessoa = linhas.get(linha);
		
		switch (coluna) {
		case cpf:
			return pessoa.get_id();
		case nome:
			return pessoa.getNome();
		case sexo:
			return pessoa.getSexo();
		case telefone:
			return pessoa.getTelefone();
		default:
			throw new IndexOutOfBoundsException("Índice da coluna fora dos limites");
		}
	}
	
	public Pessoa getObjeto(int indiceLinha){
		return linhas.get(indiceLinha);
	}
	
	public void addObjeto(Pessoa pessoa){
		linhas.add(pessoa);
		
		int ultimoElemento = getRowCount() - 1;
		
		fireTableRowsInserted(ultimoElemento, ultimoElemento);
	}
	
	public void remover(int indiceLinha){
		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
}