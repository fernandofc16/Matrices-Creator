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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainWindow extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = -6568993382515402048L;
	
	public static ArrayList<Matrices> matrizesCriadas = new ArrayList<>();
	public static ArrayList<Matrices> matrizesPrintadas = new ArrayList<>();
	private ArrayList<String> matricesDeterminantsCalculated = new ArrayList<>();
	JButton buttonCriarMatriz = new JButton("Create New Matrix");
	JButton buttonDeletarMatriz = new JButton("Delete Matrix");
	JButton buttonSomarMatrizes = new JButton("Sum Matrices");
	JButton buttonSubtractionMatrices = new JButton("Subtraction Matrices");
	JButton buttonMultiplicarMatrizes = new JButton("Multiply Matrices");
	JButton buttonTranspostarMatriz = new JButton("Transposed Matrix");
	JButton buttonMatrixDeterminant = new JButton("Matrix Determinant");
	JButton buttonMatrixOpposite = new JButton("Opposite Matrix");
	public static JTextArea areaMatrizes = new JTextArea();
	public static JScrollPane jspAreaMatriz = new JScrollPane(areaMatrizes);
	Font fonte = new Font("serif", Font.BOLD, 35);
	Font fonte2 = new Font("serif", Font.BOLD, 15);
	MatrixManipulation mm = new MatrixManipulation();
	
	public MainWindow() {
		//configs
		super("Janela Principal");
		setSize(700, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
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
		buttonTranspostarMatriz.addActionListener(this);
		buttonTranspostarMatriz.setFont(fonte2);
		buttonMatrixDeterminant.addActionListener(this);
		buttonMatrixDeterminant.setFont(fonte2);
		buttonSubtractionMatrices.addActionListener(this);
		buttonSubtractionMatrices.setFont(fonte2);
		buttonMatrixOpposite.addActionListener(this);
		buttonMatrixOpposite.setFont(fonte2);
		
		areaMatrizes.setEditable(false);
		areaMatrizes.setAutoscrolls(true);
		areaMatrizes.setFont(fonte);
		
		Container c1 = new Container();
		c1.setLayout(new GridLayout(4,2));
		c1.add(buttonCriarMatriz);
		c1.add(buttonDeletarMatriz);
		c1.add(buttonSomarMatrizes);
		c1.add(buttonSubtractionMatrices);
		c1.add(buttonMultiplicarMatrizes);
		c1.add(buttonTranspostarMatriz);
		c1.add(buttonMatrixDeterminant);
		c1.add(buttonMatrixOpposite);
		add(BorderLayout.NORTH, c1);
		
		Container c2 = new Container();
		c2.setLayout(new GridLayout());
		c2.add(jspAreaMatriz);
		add(BorderLayout.CENTER, c2);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == buttonCriarMatriz) {
			mm.criarMatrizes(this);
			mm.printarMatrizes();
		}
		
		if(arg0.getSource() == buttonSomarMatrizes) {
			mm.getMatricesForSum(this);
		}
		
		if(arg0.getSource() == buttonSubtractionMatrices) {
			mm.getMatricesForSubtraction(this);
		}
		
		if(arg0.getSource() == buttonMultiplicarMatrizes) {
			mm.getMatricesForMultiplication(this);
		}
		
		if(arg0.getSource() == buttonDeletarMatriz) {
			mm.deletarMatriz();
			mm.printarMatrizes();
		}
		
		if(arg0.getSource() == buttonTranspostarMatriz) {
			Matrices mtz = mm.matrizTransposta();
			if(mtz != null) {
				matrizesCriadas.add(mtz);
				mm.printarMatrizes();
			}
		}
		
		if(arg0.getSource() == buttonMatrixOpposite) {
			Matrices mtz = mm.matrixOpposite();
			if(mtz != null) {
				matrizesCriadas.add(mtz);
				mm.printarMatrizes();
			}
		}
		
		if(arg0.getSource() == buttonMatrixDeterminant) {
			String matrixName = JOptionPane.showInputDialog(null, "Matrix name to calculate determinant");
			Matrices matrixDet = null;
			for(Matrices matrix : matrizesCriadas) {
				if(matrix.getNome().equals(matrixName)) {
					matrixDet = matrix;
				}
			}
			if(matrixDet != null) {
				if(matrixDet.getLinha() == matrixDet.getColuna()) {
					if(!matrixDet.getHasDeterminant()) {
						int determinant = mm.matricesDeterminant(matrixDet.getMatriz());
						matricesDeterminantsCalculated.add(matrixName);
						matrixDet.setDeterminant(determinant);
						JOptionPane.showMessageDialog(null, "Determinant of matrix " + matrixName + ": " + determinant);
					} else {
						JOptionPane.showMessageDialog(null, "Determinant of matrix " + matrixName + ": " + matrixDet.getDeterminant());
					}
				} else {
					JOptionPane.showMessageDialog(null, "To calculate the determinant, the matrix must be quadratic", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "This matrix does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
