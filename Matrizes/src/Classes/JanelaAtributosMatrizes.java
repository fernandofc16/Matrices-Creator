package Classes;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class JanelaAtributosMatrizes extends JDialog implements ActionListener {

	private static final long serialVersionUID = 5920534168658692222L;

	private JTextField[][] arrayJTextField;
	private int[][] arrayValues;
	private JButton jbOK;
	private int linhas, colunas;
	private String name;
	private ManipulaçãoMatriz manMatriz;
	
	public JanelaAtributosMatrizes(String name, int linhas, int colunas, JFrame parent, ManipulaçãoMatriz manMatriz) {
		//configs
		super(parent, "Atributos Matriz");
		setSize(500, 500);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		this.name = name;
		this.linhas = linhas;
		this.colunas = colunas;
		this.manMatriz = manMatriz;
		
		//layouts
		//setLayout(new GridLayout((linhas-1), (colunas-1)));
		setLayout(new BorderLayout());
		Container contJTextFields = new Container();
		contJTextFields.setLayout(new GridLayout(linhas, colunas));
		System.out.println("Linhas: " + linhas + "  Colunas: " + colunas);
		
		arrayValues = new int[linhas][colunas];
		arrayJTextField = new JTextField[linhas][colunas];
		for(int i=0;i<linhas;i++) {
			for(int j=0;j<colunas;j++) {
				JTextField jtf = new JTextField();
				contJTextFields.add(jtf, i, j);
				arrayJTextField[i][j] = jtf;
			}
		}
		
		add(BorderLayout.CENTER, contJTextFields);
		
		jbOK = new JButton("OK");

		jbOK.addActionListener(this);
		add(BorderLayout.SOUTH, jbOK);
				
	}
	
	public int[][] getArrayValues() {
		return arrayValues;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jbOK) {
			System.out.println("Passou jbOK");
			for(int i=0;i<linhas;i++) {
				for(int j=0;j<colunas;j++) {
					arrayValues[i][j] = Integer.parseInt(arrayJTextField[i][j].getText());
				}
			}

			Matrizes matrizCriada = manMatriz.criarMatriz(name, linhas, colunas, arrayValues);
			JanelaPrincipal.matrizesCriadas.add(matrizCriada);
			manMatriz.printarMatrizes();
			dispose();
			
		}
		
	}
	
}
