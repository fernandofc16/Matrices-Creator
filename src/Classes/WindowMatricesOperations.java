package Classes;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WindowMatricesOperations extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 2426974607429762916L;
	
	private JTextField matrixA, matrixB, matrixC;
	private JLabel sumLabel, subLabel, equalLabel, multiplyLabel;
	private JButton buttonOK, buttonCancel;
	private Font fontButton, fontJTextField;
	private String[] matricesNames = new String[3];
	private MatrixManipulation mm;
	private int whichOperation;
	
	public WindowMatricesOperations(JFrame parent, int whichOperation, MatrixManipulation mm) {
		//1 for sum
		//0 for sub
		super(parent, "Sum or Sub");
		setSize(200, 80);
		setAlwaysOnTop(true);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setModal(true);
		this.mm = mm;
		this.whichOperation = whichOperation;
		
		fontButton = new Font("serif", Font.BOLD, 15);
		fontJTextField = new Font("serif", Font.PLAIN, 20);
		
		matrixA = new JTextField();
		matrixA.setFont(fontJTextField);
		matrixA.setHorizontalAlignment(JLabel.CENTER);
		matrixA.addActionListener(this);
		matrixA.addKeyListener(this);
		matrixB = new JTextField();
		matrixB.setFont(fontJTextField);
		matrixB.setHorizontalAlignment(JLabel.CENTER);
		matrixB.addActionListener(this);
		matrixB.addKeyListener(this);
		matrixC = new JTextField();
		matrixC.setFont(fontJTextField);
		matrixC.setHorizontalAlignment(JLabel.CENTER);
		matrixC.addActionListener(this);
		matrixC.addKeyListener(this);
		sumLabel = new JLabel("+");
		sumLabel.setFont(fontJTextField);
		sumLabel.setHorizontalAlignment(JLabel.CENTER);
		subLabel = new JLabel("-");
		subLabel.setFont(fontJTextField);
		subLabel.setHorizontalAlignment(JLabel.CENTER);
		equalLabel = new JLabel("=");
		equalLabel.setFont(fontJTextField);
		equalLabel.setHorizontalAlignment(JLabel.CENTER);
		multiplyLabel = new JLabel("x");
		multiplyLabel.setFont(fontJTextField);
		multiplyLabel.setHorizontalAlignment(JLabel.CENTER);
		
		Container c1 = new Container();
		c1.setLayout(new GridLayout(1,5));
		c1.add(matrixA);
		
		switch(whichOperation) {
			case 0:
				c1.add(subLabel);
				break;
			case 1:
				c1.add(sumLabel);
				break;
			case 2:
				c1.add(multiplyLabel);
				break;
		}
		
		c1.add(matrixB);
		c1.add(equalLabel);
		c1.add(matrixC);
		
		add(c1, BorderLayout.CENTER);
		
		buttonOK = new JButton("OK");
		buttonOK.setFont(fontButton);
		buttonOK.addActionListener(this);
		buttonOK.addKeyListener(this);
		buttonCancel = new JButton("Cancel");
		buttonCancel.setFont(fontButton);
		buttonCancel.addActionListener(this);
		buttonCancel.addKeyListener(this);
		
		Container c2 = new Container();
		c2.setLayout(new GridLayout(1,2));
		c2.add(buttonOK);
		c2.add(buttonCancel);
		
		add(c2, BorderLayout.SOUTH);
	}
	
	public void doThatThing() {
		
		matricesNames[0] = matrixA.getText();
		matricesNames[1] = matrixB.getText();
		matricesNames[2] = matrixC.getText();
		ArrayList<String> matricesNamesCreated = new ArrayList<>();
		for(Matrices matrix : MainWindow.matrizesCriadas) {
			matricesNamesCreated.add(matrix.getNome());
		}
		if(!matricesNamesCreated.contains(matricesNames[2])) {
			mm.setMatricesNames(matricesNames);
			if(mm.getMatricesNames() != null) {
				Matrices mtz = mm.sumOrSubMatrices(whichOperation);
				MainWindow.matrizesCriadas.add(mtz);
				mm.printarMatrizes();
			} 
		} else {
			setVisible(false);
			JOptionPane.showMessageDialog(null, "Matrix result name already in use", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(e.getSource() != buttonCancel) {
				doThatThing();
				dispose();
			} else {
				dispose();
			}
		} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			dispose();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonOK) {
			doThatThing();
			dispose();
		} else if(e.getSource() == buttonCancel) {
			dispose();
		}
	}

}
