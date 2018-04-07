package Classes;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MatrixManipulation {
	
	private Matrices matrix1, matrix2, matrixDelete, matrixTransposed, matrixOpposite;
	private ArrayList<String> matricesCreated = new ArrayList<>();
	private String[] matricesNames;

	public void setMatricesNames(String[] matricesNames) {
		this.matricesNames = matricesNames;
	}
	
	public String[] getMatricesNames() {
		return matricesNames;
	}
	
	public void printMatrices() {

		for (Matrices mtz : MainWindow.matricesCreated) {
			if(!MainWindow.matricesShown.contains(mtz)) {
				if(!MainWindow.areaMatrices.getText().isEmpty()) { MainWindow.areaMatrices.append("\n"); }
				MainWindow.areaMatrices.append(mtz.getName() + ":");

				for(int i = 0; i < mtz.getRow(); i++) {
					MainWindow.areaMatrices.append("\n" + "{");
					for(int j = 0; j < mtz.getColumn(); j++) {
						for(int space = 0; space < (mtz.getBiggestNumberValue() - String.valueOf(mtz.getMatrix()[i][j]).length()); space++) {
							MainWindow.areaMatrices.append("  ");
						}
						MainWindow.areaMatrices.append(" " + mtz.getMatrix()[i][j]);
						if(j == mtz.getColumn() - 1) {
							MainWindow.areaMatrices.append(" }");
						} else {
							MainWindow.areaMatrices.append(",");
						}
					}
				}
				MainWindow.matricesShown.add(mtz);
			}
		}

	}
	
	public Matrices createMatrix(String name, int row, int column, int[][] numberMatrix, int biggestNumberValue) {
		return new Matrices(name, row, column, numberMatrix, biggestNumberValue);
	}
	
	public void createMatrices(JFrame parent) {
		String rowNumber = "";
		String columnNumber = "";
		String matrixName = JOptionPane.showInputDialog(null, "Type the name of the matrix to be created: ");
		if(!matricesCreated.contains(matrixName)) {
			if(matrixName != null) {
				while(!rowNumber.matches("[0-9]+")) {
					rowNumber = JOptionPane.showInputDialog(null, "Type tha number of rows: ");
				}
				if(rowNumber != null) {
					while(!columnNumber.matches("[0-9]+")) {
						columnNumber = JOptionPane.showInputDialog(null, "Type the number of columns: ");
					}
					if(columnNumber != null) {
						new WindowMatricesValues(matrixName, Integer.parseInt(rowNumber), Integer.parseInt(columnNumber), parent, this);
						matricesCreated.add(matrixName);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "This name is already in use", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void deleteMatrix() {
		
		String nameMatrixDelete = JOptionPane.showInputDialog(null, "Type the name of the matrix to be deleted: ");
		if(nameMatrixDelete != null) {
			matrixDelete = null;
			for (Matrices matrixDel : MainWindow.matricesCreated) {
				for(int i = 0; i < MainWindow.matricesCreated.size(); i++) {
					if(matrixDel.getName().equals(String.valueOf(nameMatrixDelete))) {
						matrixDelete = matrixDel;
					}
				}
			}
			
			if(matrixDelete != null) {
				MainWindow.matricesCreated.remove(matrixDelete);
				MainWindow.matricesShown.clear();
				MainWindow.areaMatrices.setText("");
				printMatrices();
			} else {
				JOptionPane.showMessageDialog(null, "No matrix was found with this name", "ERROR", JOptionPane.ERROR_MESSAGE);
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
			for (Matrices matrices : MainWindow.matricesCreated) {
				
				if(matrices.getName().equalsIgnoreCase(matricesNames[0])) {
					matrix1 = matrices;
				}
				
				if(matrices.getName().equalsIgnoreCase(matricesNames[1])) {
					matrix2 = matrices;
				}
				
			}
			
			if(whichOperation == 1 || whichOperation == 0) {
			
				if(matrix1.getRow() == matrix2.getRow() && matrix1.getColumn() == matrix2.getColumn()) {
					
					int[][] matrix3 = new int[matrix1.getRow()][matrix1.getColumn()];
					for(int i = 0; i < matrix1.getRow(); i ++) {
						for(int j = 0; j < matrix1.getColumn(); j++) {
							if(whichOperation == 1) {
								matrix3[i][j] = matrix1.getMatrix()[i][j] + matrix2.getMatrix()[i][j];
							} else {
								matrix3[i][j] = matrix1.getMatrix()[i][j] - matrix2.getMatrix()[i][j];
							}
							int count = String.valueOf(matrix3[i][j]).length();
							if(biggestNumberValue < count) { biggestNumberValue = count; }
						}
					}
					
					return new Matrices(matricesNames[2], matrix1.getRow(), matrix1.getColumn(), matrix3, biggestNumberValue);
					
				} else {
					JOptionPane.showMessageDialog(null, "The matrices must have the same dimension", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			
			} else {
				
				if(matrix1.getColumn() == matrix2.getRow()) {
					
					int[][] matrix3 = new int[matrix1.getRow()][matrix2.getColumn()];
					for(int i = 0; i < matrix1.getRow(); i++) {
						for(int j = 0; j < matrix2.getColumn(); j++) {
							for(int k = 0; k < matrix2.getRow(); k++) {
								matrix3[i][j] += (matrix1.getMatrix()[i][k]* matrix2.getMatrix()[k][j]);
								int count = String.valueOf(matrix3[i][j]).length();
								if(biggestNumberValue < count) { biggestNumberValue = count; }
							}
						}
					}
					
					return new Matrices(matricesNames[2], matrix1.getRow(), matrix2.getColumn(), matrix3, biggestNumberValue);
					
				} else {
					JOptionPane.showMessageDialog(null, "The multiplication between the matrices cannot be made", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		}
		return null;
		
	}
	
	public Matrices matrixOpposite() {
		
		matrixOpposite = null;
		String nameMatrixOpposite = JOptionPane.showInputDialog(null, "Type the name of the matrix you want the opposite");
		
		for (Matrices matrices : MainWindow.matricesCreated) {
			if(matrices.getName().equals(nameMatrixOpposite)) {
				matrixOpposite = matrices;
			}
		}
		
		if(matrixOpposite != null) {
			int[][] matrixOpFinal = new int[matrixOpposite.getRow()][matrixOpposite.getColumn()];
			for(int i = 0; i < matrixOpposite.getRow(); i++) {
				for(int j = 0; j < matrixOpposite.getColumn(); j++) {
					matrixOpFinal[i][j] = matrixOpposite.getMatrix()[i][j]*(-1);
				}
			}
			return new Matrices(nameMatrixOpposite, matrixOpposite.getRow(), matrixOpposite.getColumn(), matrixOpFinal, matrixOpposite.getBiggestNumberValue());
		}
		
		return null;
	}
	
	public Matrices matrixTransposed() {
		
		matrixTransposed = null;
		int biggestNumberValue = 0;
		String nameMatrixTransposed = JOptionPane.showInputDialog(null, "Type the name of the matrix to be transposed");
		
		for (Matrices matrices : MainWindow.matricesCreated) {
			if(matrices.getName().equals(nameMatrixTransposed)) {
				matrixTransposed = matrices;
			}
		}
		
		if(matrixTransposed != null) {
			int[][] finalMatrix = new int[matrixTransposed.getColumn()][matrixTransposed.getRow()];
			for(int i = 0; i < matrixTransposed.getRow(); i++){
				for(int j = 0; j < matrixTransposed.getColumn(); j++) {
					finalMatrix[j][i] = matrixTransposed.getMatrix()[i][j];
					int count = String.valueOf(matrixTransposed.getMatrix()[i][j]).length();
					if(biggestNumberValue < count) { biggestNumberValue = count; }
				}
			}
			return new Matrices(nameMatrixTransposed+"T", matrixTransposed.getColumn(), matrixTransposed.getRow(), finalMatrix, biggestNumberValue);
		} else {
			JOptionPane.showMessageDialog(null, "No matrix with this name was found", "ERROR", JOptionPane.ERROR_MESSAGE);
			return null;		
		}
	}
	
	public int matricesDeterminant(int[][] matrix) {
		int sum = 0; 
	    int s;
	    if(matrix.length == 1){  //bottom case of recursion. size 1 matrix determinant is itself.
	      return(matrix[0][0]);
	    }
	    for(int i = 0; i < matrix.length; i++) { //finds determinant using row-by-row expansion
	      int[][] smaller = new int[matrix.length-1][matrix.length-1]; //creates smaller matrix- values not in same row, column
	      for(int j = 1; j < matrix.length; j++) {
	        for(int k = 0; k < matrix.length; k++) {
	          if(k < i) {
	            smaller[j-1][k] = matrix[j][k];
	          } else if(k > i) {
	            smaller[j-1][k-1] = matrix[j][k];
	          }
	        }
	      }
	      if(i%2 == 0){ //sign changes based on i
	        s = 1;
	      } else {
	        s = -1;
	      }
	      sum += s*matrix[0][i]*(matricesDeterminant(smaller)); //recursive step: determinant of larger determined by smaller.
	    }
	    return(sum); //returns determinant value. once stack is finished, returns final determinant.	  
	}
	
}
