package Classes;

public class Matrices {

	private String name;
	private int row, column;
	private int[][] matrix;
	private int biggestNumberValue, determinant;
	private boolean hasDeterminant;
	
	public Matrices(String nome, int row, int column, int[][] numberMatrix, int biggestNumberValue) {
		this.name = nome;
		this.row = row;
		this.column = column;
		this.matrix = new int[row][column];
		this.biggestNumberValue = biggestNumberValue;
		hasDeterminant = false;
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				this.matrix[i][j] = numberMatrix[i][j];
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	
	public int getBiggestNumberValue() {
		return biggestNumberValue;
	}
	
	public void setBiggestNumberValue(int biggestNumberValue) {
		this.biggestNumberValue = biggestNumberValue;
	}
	
	public int getDeterminant() {
		return determinant;
	}
	
	public void setDeterminant(int determinant) {
		this.determinant = determinant;
		hasDeterminant = true;
	}
	
	public boolean getHasDeterminant() {
		return hasDeterminant;
	}
}
