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
	
	public static ArrayList<Matrices> matricesCreated = new ArrayList<>();
	public static ArrayList<Matrices> matricesShown = new ArrayList<>();
	private ArrayList<String> matricesDeterminantsCalculated = new ArrayList<>();
	JButton buttonCreateMatrix = new JButton("Create New Matrix");
	JButton buttonDeleteMatrix = new JButton("Delete Matrix");
	JButton buttonSumMatrices = new JButton("Sum Matrices");
	JButton buttonSubtractMatrices = new JButton("Subtraction Matrices");
	JButton buttonMultiplicationMatrices = new JButton("Multiply Matrices");
	JButton buttonTransposedMatrix = new JButton("Transposed Matrix");
	JButton buttonMatrixDeterminant = new JButton("Matrix Determinant");
	JButton buttonMatrixOpposite = new JButton("Opposite Matrix");
	public static JTextArea areaMatrices = new JTextArea();
	public static JScrollPane jspAreaMatrices = new JScrollPane(areaMatrices);
	Font fontUsedBigger = new Font("serif", Font.BOLD, 35);
	Font fontUsedSmaller = new Font("serif", Font.BOLD, 15);
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
		
		buttonCreateMatrix.addActionListener(this);
		buttonCreateMatrix.setFont(fontUsedSmaller);
		buttonSumMatrices.addActionListener(this);
		buttonSumMatrices.setFont(fontUsedSmaller);
		buttonDeleteMatrix.addActionListener(this);
		buttonDeleteMatrix.setFont(fontUsedSmaller);
		buttonMultiplicationMatrices.addActionListener(this);
		buttonMultiplicationMatrices.setFont(fontUsedSmaller);
		buttonTransposedMatrix.addActionListener(this);
		buttonTransposedMatrix.setFont(fontUsedSmaller);
		buttonMatrixDeterminant.addActionListener(this);
		buttonMatrixDeterminant.setFont(fontUsedSmaller);
		buttonSubtractMatrices.addActionListener(this);
		buttonSubtractMatrices.setFont(fontUsedSmaller);
		buttonMatrixOpposite.addActionListener(this);
		buttonMatrixOpposite.setFont(fontUsedSmaller);
		
		areaMatrices.setEditable(false);
		areaMatrices.setAutoscrolls(true);
		areaMatrices.setFont(fontUsedBigger);
		
		Container c1 = new Container();
		c1.setLayout(new GridLayout(4,2));
		c1.add(buttonCreateMatrix);
		c1.add(buttonDeleteMatrix);
		c1.add(buttonSumMatrices);
		c1.add(buttonSubtractMatrices);
		c1.add(buttonMultiplicationMatrices);
		c1.add(buttonTransposedMatrix);
		c1.add(buttonMatrixDeterminant);
		c1.add(buttonMatrixOpposite);
		add(BorderLayout.NORTH, c1);
		
		Container c2 = new Container();
		c2.setLayout(new GridLayout());
		c2.add(jspAreaMatrices);
		add(BorderLayout.CENTER, c2);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == buttonCreateMatrix) {
			mm.createMatrices(this);
			mm.printMatrices();
		}
		
		if(arg0.getSource() == buttonSumMatrices) {
			mm.getMatricesForSum(this);
		}
		
		if(arg0.getSource() == buttonSubtractMatrices) {
			mm.getMatricesForSubtraction(this);
		}
		
		if(arg0.getSource() == buttonMultiplicationMatrices) {
			mm.getMatricesForMultiplication(this);
		}
		
		if(arg0.getSource() == buttonDeleteMatrix) {
			mm.deleteMatrix();
			mm.printMatrices();
		}
		
		if(arg0.getSource() == buttonTransposedMatrix) {
			Matrices mtz = mm.matrixTransposed();
			if(mtz != null) {
				matricesCreated.add(mtz);
				mm.printMatrices();
			}
		}
		
		if(arg0.getSource() == buttonMatrixOpposite) {
			Matrices mtz = mm.matrixOpposite();
			if(mtz != null) {
				matricesCreated.add(mtz);
				mm.printMatrices();
			}
		}
		
		if(arg0.getSource() == buttonMatrixDeterminant) {
			String matrixName = JOptionPane.showInputDialog(null, "Matrix name to calculate determinant");
			Matrices matrixDet = null;
			for(Matrices matrix : matricesCreated) {
				if(matrix.getName().equals(matrixName)) {
					matrixDet = matrix;
				}
			}
			if(matrixDet != null) {
				if(matrixDet.getRow() == matrixDet.getColumn()) {
					if(!matrixDet.getHasDeterminant()) {
						int determinant = mm.matricesDeterminant(matrixDet.getMatrix());
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
