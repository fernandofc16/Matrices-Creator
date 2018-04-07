package Classes;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class WindowMatricesValues extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 5920534168658692222L;

	private JTextField[][] arrayJTextField;
	private int[][] arrayValues;
	private JButton jbOK, jbCancel;
	private int linhas, colunas;
	private String name;
	private MatrixManipulation manMatriz;
	private Font fontButton, fontJTextField;
	
	public WindowMatricesValues(String name, int linhas, int colunas, JFrame parent, MatrixManipulation manMatriz) {
		
		super(parent, "Atributos Matriz");
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int widthScreen = gd.getDisplayMode().getWidth();
		int heightScreen = gd.getDisplayMode().getHeight();
		
		int widthPropose = 100*colunas;
		int heightPropose = 100*linhas;
		
		if(widthPropose > widthScreen) { widthPropose = widthScreen; }
		if(heightPropose > heightScreen) { heightPropose = heightScreen; }
		
		setSize(widthPropose, heightPropose);
		setAlwaysOnTop(true);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		fontButton = new Font("serif", Font.BOLD, 10);
		fontJTextField = new Font("serif", Font.PLAIN, 30);
		
		this.name = name;
		this.linhas = linhas;
		this.colunas = colunas;
		this.manMatriz = manMatriz;
		
		setLayout(new BorderLayout());
		Container contJTextFields = new Container();
		contJTextFields.setLayout(new GridLayout(linhas, colunas));
		
		arrayValues = new int[linhas][colunas];
		arrayJTextField = new JTextField[linhas][colunas];
		for(int i=0;i<linhas;i++) {
			for(int j=0;j<colunas;j++) {
				JTextField jtf = new JTextField();
				jtf.setFont(fontJTextField);
				jtf.setHorizontalAlignment(JTextField.CENTER);
				jtf.addKeyListener(this);
				contJTextFields.add(jtf);
				arrayJTextField[i][j] = jtf;
			}
		}
		
		add(BorderLayout.CENTER, contJTextFields);
		
		Container southCont = new Container();
		southCont.setLayout(new GridLayout(1,2));
		jbOK = new JButton("OK");
		jbCancel = new JButton("Cancel");
		jbOK.addActionListener(this);
		jbCancel.addActionListener(this);
		jbOK.setFont(fontButton);
		jbCancel.setFont(fontButton);
		southCont.add(jbOK, 0, 0);
		southCont.add(jbCancel,  0, 1);
		
		add(BorderLayout.SOUTH, southCont);
				
	}
	
	private void makeMatrixThings() {
		int biggestNumberValue = 0;
		for(int i=0;i<linhas;i++) {
			for(int j=0;j<colunas;j++) {
				if(arrayJTextField[i][j].getText().trim().equals("")) {
					arrayValues[i][j] = 0;
				} else {
					arrayValues[i][j] = Integer.parseInt(arrayJTextField[i][j].getText());
				}
				int count = String.valueOf(arrayValues[i][j]).length();
				if(biggestNumberValue < count) { biggestNumberValue = count; }
			}
		}
		Matrices matrizCriada = manMatriz.criarMatriz(name, linhas, colunas, arrayValues, biggestNumberValue);
		MainWindow.matrizesCriadas.add(matrizCriada);
		manMatriz.printarMatrizes();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jbOK) {
			makeMatrixThings();
			dispose();
			
		} else if(e.getSource() == jbCancel) {
			dispose();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			makeMatrixThings();
			dispose();
		} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			dispose();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
