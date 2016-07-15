package Classes;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JanelaPrincipal extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = -6568993382515402048L;
	
	public static ArrayList<Matrizes> matrizesCriadas = new ArrayList<>();
	public static ArrayList<Matrizes> matrizesPrintadas = new ArrayList<>();
	JButton buttonCriarMatriz = new JButton("Criar Nova Matriz");
	JButton buttonDeletarMatriz = new JButton("Deletar Matriz");
	JButton buttonSomarMatrizes = new JButton("Somar Matrizes");
	JButton buttonMultiplicarMatrizes = new JButton("Multiplicar Matrizes");
	public static JTextArea areaMatrizes = new JTextArea();
	public static JScrollPane jspAreaMatriz = new JScrollPane(areaMatrizes);
	Font fonte = new Font("serif", Font.BOLD, 35);
	Font fonte2 = new Font("serif", Font.BOLD, 15);
	ManipulaçãoMatriz mm = new ManipulaçãoMatriz();
	
	public JanelaPrincipal() {
		//configs
		super("Janela Principal");
		setSize(500, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		//layouts
		setLayout(new BorderLayout());
		
		buttonCriarMatriz.addActionListener(this);
		buttonCriarMatriz.setFont(fonte2);
		buttonSomarMatrizes.addActionListener(this);
		buttonSomarMatrizes.setFont(fonte2);
		buttonDeletarMatriz.addActionListener(this);
		buttonDeletarMatriz.setFont(fonte2);
		buttonMultiplicarMatrizes.addActionListener(this);
		buttonMultiplicarMatrizes.setFont(fonte2);
		
		areaMatrizes.setEditable(false);
		areaMatrizes.setAutoscrolls(true);
		areaMatrizes.setFont(fonte);
		
		Container c1 = new Container();
		c1.setLayout(new GridLayout(2,3));
		c1.add(buttonCriarMatriz);
		c1.add(buttonSomarMatrizes);
		c1.add(buttonDeletarMatriz);
		c1.add(buttonMultiplicarMatrizes);
		add(BorderLayout.NORTH, c1);
		
		Container c2 = new Container();
		c2.setLayout(new GridLayout());
		c2.add(jspAreaMatriz);
		add(BorderLayout.CENTER, c2);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == buttonCriarMatriz) {
			mm.criarMatrizes();
			mm.printarMatrizes();
		}
		
		if(arg0.getSource() == buttonSomarMatrizes) {
			Matrizes mtz = mm.somarMatrizes();
			matrizesCriadas.add(mtz);
			mm.printarMatrizes();
		}
		
		if(arg0.getSource() == buttonDeletarMatriz) {
			mm.deletarMatriz();
			mm.printarMatrizes();
		}
		
		if(arg0.getSource() == buttonMultiplicarMatrizes) {
			Matrizes mtz = mm.multiplicarMatrizes();
			matrizesCriadas.add(mtz);
			mm.printarMatrizes();
		}
	}
	
}
