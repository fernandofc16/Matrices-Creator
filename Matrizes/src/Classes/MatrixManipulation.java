package Classes;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MatrixManipulation {
	
	private Matrices matriz1, matriz2, mtzDeletar, matrizT, matrixOp;
	private ArrayList<String> matricesCreated = new ArrayList<>();
	private String[] matricesNames;

	public void setMatricesNames(String[] matricesNames) {
		this.matricesNames = matricesNames;
	}
	
	public String[] getMatricesNames() {
		return matricesNames;
	}
	
	public void printarMatrizes() {

		for (Matrices mtz : MainWindow.matrizesCriadas) {
			if(!MainWindow.matrizesPrintadas.contains(mtz)) {
				if(!MainWindow.areaMatrizes.getText().isEmpty()) { MainWindow.areaMatrizes.append("\n"); }
				MainWindow.areaMatrizes.append(mtz.getNome() + ":");

				for(int i = 0; i < mtz.getLinha(); i++) {
					MainWindow.areaMatrizes.append("\n" + "{");
					for(int j = 0; j < mtz.getColuna(); j++) {
						for(int space = 0; space < (mtz.getBiggestNumberValue() - String.valueOf(mtz.getMatriz()[i][j]).length()); space++) {
							MainWindow.areaMatrizes.append("  ");
						}
						MainWindow.areaMatrizes.append(" " + mtz.getMatriz()[i][j]);
						if(j == mtz.getColuna() - 1) {
							MainWindow.areaMatrizes.append(" }"); 
						} else {
							MainWindow.areaMatrizes.append(",");
						}
					}
				}
				MainWindow.matrizesPrintadas.add(mtz);
			}
		}

	}
	
	public Matrices criarMatriz(String nome, int linha, int coluna, int[][] numbMatriz, int biggestNumberValue) {	
		return new Matrices(nome, linha, coluna, numbMatriz, biggestNumberValue);
	}
	
	public void criarMatrizes(JFrame parent) {
		String numeroLinhas = "";
		String numeroColunas = "";
		String nomeDaMatriz = JOptionPane.showInputDialog(null, "Digite o nome da matriz a ser criada: ");
		if(!matricesCreated.contains(nomeDaMatriz)) {
			if(nomeDaMatriz != null) {
				while(!numeroLinhas.matches("[0-9]+")) {
					numeroLinhas = JOptionPane.showInputDialog(null, "Digite o número de linhas da matriz: ");
				}
				if(numeroLinhas != null) {
					while(!numeroColunas.matches("[0-9]+")) {
						numeroColunas = JOptionPane.showInputDialog(null, "Digite o número de colunas da matriz: ");
					}
					if(numeroColunas != null) {
						new WindowMatricesValues(nomeDaMatriz, Integer.parseInt(numeroLinhas), Integer.parseInt(numeroColunas), parent, this);
						matricesCreated.add(nomeDaMatriz);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "This name is already in use", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void deletarMatriz() {
		
		String nomeMatrizDeletar = JOptionPane.showInputDialog(null, "Digite o nome da matriz a ser deletada: ");
		if(nomeMatrizDeletar != null) {
			mtzDeletar = null;
			for (Matrices matrizDel : MainWindow.matrizesCriadas) {
				for(int i = 0; i < MainWindow.matrizesCriadas.size(); i++) {
					if(matrizDel.getNome().equals(String.valueOf(nomeMatrizDeletar))) { 
						mtzDeletar = matrizDel;
					}
				}
			}
			
			if(mtzDeletar != null) {
				MainWindow.matrizesCriadas.remove(mtzDeletar);
				MainWindow.matrizesPrintadas.clear();
				MainWindow.areaMatrizes.setText("");
				printarMatrizes();
			} else {
				JOptionPane.showMessageDialog(null, "Nenhuma matriz encontrada com esse nome", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void getMatricesForSum(JFrame parent) {
		
		matricesNames = null;
		new WindowMatricesOperations(parent, 1, this);
		
	}
	
	public void getMatricesForSubtraction(JFrame parent) {
		
		matricesNames = null;
		new WindowMatricesOperations(parent, 0, this);
		
	}
	
	public void getMatricesForMultiplication(JFrame parent) {
		
		matricesNames = null;
		new WindowMatricesOperations(parent, 2, this);
		
	}
	
	public Matrices sumOrSubMatrices(int whichOperation) {
		//0 for subtraction
		//1 for sum
		//2 for multiplication
		int biggestNumberValue = 0;
		if(matricesNames != null) {
			for (Matrices matrices : MainWindow.matrizesCriadas) {
				
				if(matrices.getNome().equalsIgnoreCase(matricesNames[0])) {
					matriz1 = matrices;
				}
				
				if(matrices.getNome().equalsIgnoreCase(matricesNames[1])) {
					matriz2 = matrices;
				}
				
			}
			
			if(whichOperation == 1 || whichOperation == 0) {
			
				if(matriz1.getLinha() == matriz2.getLinha() && matriz1.getColuna() == matriz2.getColuna()) {
					
					int[][] matriz3 = new int[matriz1.getLinha()][matriz1.getColuna()];			
					for(int i = 0; i < matriz1.getLinha(); i ++) {
						for(int j = 0; j < matriz1.getColuna(); j++) {
							if(whichOperation == 1) {
								matriz3[i][j] = matriz1.getMatriz()[i][j] + matriz2.getMatriz()[i][j];
							} else {
								matriz3[i][j] = matriz1.getMatriz()[i][j] - matriz2.getMatriz()[i][j];
							}
							int count = String.valueOf(matriz3[i][j]).length();
							if(biggestNumberValue < count) { biggestNumberValue = count; }
						}
					}
					
					return new Matrices(matricesNames[2], matriz1.getLinha(), matriz1.getColuna(), matriz3, biggestNumberValue);
					
				} else {
					JOptionPane.showMessageDialog(null, "As matrizes não possuem as mesmas dimensões", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			
			} else {
				
				if(matriz1.getColuna() == matriz2.getLinha()) {
					
					int[][] matriz3 = new int[matriz1.getLinha()][matriz2.getColuna()];
					for(int i = 0; i < matriz1.getLinha(); i++) {
						for(int j = 0; j < matriz2.getColuna(); j++) {
							for(int k = 0; k < matriz2.getLinha(); k++) {
								matriz3[i][j] += (matriz1.getMatriz()[i][k]*matriz2.getMatriz()[k][j]);
								int count = String.valueOf(matriz3[i][j]).length();
								if(biggestNumberValue < count) { biggestNumberValue = count; }
							}
						}
					}
					
					return new Matrices(matricesNames[2], matriz1.getLinha(), matriz2.getColuna(), matriz3, biggestNumberValue);
					
				} else {
					JOptionPane.showMessageDialog(null, "As matrizes não podem ser multiplicadas", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		}
		return null;
		
	}
	
	public Matrices matrixOpposite() {
		
		matrixOp = null;
		String nameMatrixOpposite = JOptionPane.showInputDialog(null, "Type the name of the matrix you want the opposite");
		
		for (Matrices matrices : MainWindow.matrizesCriadas) {
			if(matrices.getNome().equals(nameMatrixOpposite)) {
				matrixOp = matrices;
			}
		}
		
		if(matrixOp != null) {
			int[][] matrixOpFinal = new int[matrixOp.getLinha()][matrixOp.getColuna()];
			for(int i = 0; i < matrixOp.getLinha(); i++) {
				for(int j = 0; j < matrixOp.getColuna(); j++) {
					matrixOpFinal[i][j] = matrixOp.getMatriz()[i][j]*(-1);
				}
			}
			return new Matrices(nameMatrixOpposite, matrixOp.getLinha(), matrixOp.getColuna(), matrixOpFinal, matrixOp.getBiggestNumberValue());
		}
		
		return null;
	}
	
	public Matrices matrizTransposta() {
		
		matrizT = null;
		int biggestNumberValue = 0;
		String nomeMatrizTransp = JOptionPane.showInputDialog(null, "Digite o nome da matriz a ser transpostada");
		
		for (Matrices matrices : MainWindow.matrizesCriadas) {
			if(matrices.getNome().equals(nomeMatrizTransp)) {
				matrizT = matrices;
			}
		}
		
		if(matrizT != null) {
			int[][] matrizTFinal = new int[matrizT.getColuna()][matrizT.getLinha()];
			for(int i = 0; i < matrizT.getLinha(); i++){
				for(int j = 0; j < matrizT.getColuna(); j++) {
					matrizTFinal[j][i] = matrizT.getMatriz()[i][j];
					int count = String.valueOf(matrizT.getMatriz()[i][j]).length();
					if(biggestNumberValue < count) { biggestNumberValue = count; }
				}
			}
			return new Matrices(nomeMatrizTransp+"T", matrizT.getColuna(), matrizT.getLinha(), matrizTFinal, biggestNumberValue);
		} else {
			JOptionPane.showMessageDialog(null, "Nenhuma matriz com esse nome encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
			return null;		
		}
	}
	
	public int matricesDeterminant(int[][] matrix) {
		int sum = 0; 
	    int s;
	    if(matrix.length == 1){  //bottom case of recursion. size 1 matrix determinant is itself.
	      return(matrix[0][0]);
	    }
	    for(int i = 0; i < matrix.length; i++){ //finds determinant using row-by-row expansion
	      int[][] smaller = new int[matrix.length-1][matrix.length-1]; //creates smaller matrix- values not in same row, column
	      for(int j = 1; j < matrix.length; j++){
	        for(int k = 0; k < matrix.length; k++){
	          if(k < i){
	            smaller[j-1][k] = matrix[j][k];
	          }
	          else if(k > i){
	            smaller[j-1][k-1] = matrix[j][k];
	          }
	        }
	      }
	      if(i%2 == 0){ //sign changes based on i
	        s = 1;
	      }
	      else{
	        s = -1;
	      }
	      sum += s*matrix[0][i]*(matricesDeterminant(smaller)); //recursive step: determinant of larger determined by smaller.
	    }
	    return(sum); //returns determinant value. once stack is finished, returns final determinant.	  
	}
	
}
