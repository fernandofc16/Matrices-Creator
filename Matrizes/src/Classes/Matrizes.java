package Classes;

public class Matrizes {

	private String nome;
	private int linha, coluna;
	private int[][] matriz;
	
	public Matrizes(String nome, int linha, int coluna, int[][] numbMatriz) {
		this.nome = nome;
		this.linha = linha;
		this.coluna = coluna;
		this.matriz = new int[linha][coluna];
		
		for(int i = 0; i < linha; i++) {
			for(int j = 0; j < coluna; j++) {
				this.matriz[i][j] = numbMatriz[i][j];
			}
		}
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(int[][] matriz) {
		this.matriz = matriz;
	}
	
	
	
}
