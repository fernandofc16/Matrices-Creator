package Classes;

import javax.swing.JOptionPane;

public class ManipulaçãoMatriz {
	
	static Matrizes matriz1, matriz2, mtzDeletar;

	public void printarMatrizes() {

		for (Matrizes mtz : JanelaPrincipal.matrizesCriadas) {
			if(!JanelaPrincipal.matrizesPrintadas.contains(mtz)) {
				if(!JanelaPrincipal.areaMatrizes.getText().isEmpty()) { JanelaPrincipal.areaMatrizes.append("\n"); }
				JanelaPrincipal.areaMatrizes.append(mtz.getNome() + ":");

				for(int i = 0; i < mtz.getLinha(); i++) {
					JanelaPrincipal.areaMatrizes.append("\n" + "{");
					for(int j = 0; j < mtz.getColuna(); j++) {
						JanelaPrincipal.areaMatrizes.append(" " + mtz.getMatriz()[i][j] + "," );
						if(j == mtz.getColuna() - 1) {JanelaPrincipal.areaMatrizes.append("}"); }
					}
				}
				JanelaPrincipal.matrizesPrintadas.add(mtz);
			}
		}

	}
	
	public Matrizes criarMatriz(String nome, int linha, int coluna, int[][] numbMatriz) {
		
		return new Matrizes(nome, linha, coluna, numbMatriz);
	}
	
	public void criarMatrizes() {
		
		String nomeDaMatriz = JOptionPane.showInputDialog(null, "Digite o nome da matriz a ser criada: ");
		String numeroLinhas = JOptionPane.showInputDialog(null, "Digite o número de linhas da matriz: ");
		String numeroColunas = JOptionPane.showInputDialog(null, "Digite o número de colunas da matriz: ");
		int[][] valores = new int[Integer.parseInt(numeroLinhas)][Integer.parseInt(numeroColunas)];
		
		for(int i = 0; i < Integer.parseInt(numeroLinhas); i++) {
			for(int j = 0; j < Integer.parseInt(numeroColunas); j++) {
				valores[i][j] = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor do número " + i + "," + j + " da sua matriz:"));
			}
		}
		
		Matrizes matrizCriada = new ManipulaçãoMatriz().criarMatriz(nomeDaMatriz, Integer.parseInt(numeroLinhas), Integer.parseInt(numeroColunas), valores);
		JanelaPrincipal.matrizesCriadas.add(matrizCriada); 
		
	}
	
	public void deletarMatriz() {
		
		String nomeMatrizDeletar = JOptionPane.showInputDialog(null, "Digite o nome da matriz a ser deletada: ");
		
		for (Matrizes matrizDel : JanelaPrincipal.matrizesCriadas) {
			for(int i = 0; i < JanelaPrincipal.matrizesCriadas.size(); i++) {
				if(matrizDel.getNome().equals(String.valueOf(nomeMatrizDeletar))) { 
					mtzDeletar = matrizDel;
				}
			}
		}
		
			JanelaPrincipal.matrizesCriadas.remove(mtzDeletar);
			JanelaPrincipal.matrizesPrintadas.clear();
			JanelaPrincipal.areaMatrizes.setText("");
			printarMatrizes();
		
	}
	
	public Matrizes somarMatrizes() {
		
		String nomeMatriz1 = JOptionPane.showInputDialog(null, "Digite o nome da primeira matriz:");
		String nomeMatriz2 = JOptionPane.showInputDialog(null, "Digite o nome da segunda matriz:");
		String nomeMatrizNova = JOptionPane.showInputDialog(null, "Digite o nome da nova matriz a ser criada com a soma");
		
		for (Matrizes matrizes : JanelaPrincipal.matrizesCriadas) {
			
			if(matrizes.getNome().equalsIgnoreCase(nomeMatriz1)) {
				matriz1 = matrizes;
			}
			
			if(matrizes.getNome().equalsIgnoreCase(nomeMatriz2)) {
				matriz2 = matrizes;
			}
			
		}
		
		if(matriz1.getLinha() == matriz2.getLinha() && matriz1.getColuna() == matriz2.getColuna()) {
			
			int[][] matriz3 = new int[matriz1.getLinha()][matriz1.getColuna()];
			
			for(int i = 0; i < matriz1.getLinha(); i ++) {
				for(int j = 0; j < matriz1.getColuna(); j++) {
					matriz3[i][j] = matriz1.getMatriz()[i][j] + matriz2.getMatriz()[i][j];
				}
			}
			
			return new Matrizes(nomeMatrizNova, matriz1.getLinha(), matriz1.getColuna(), matriz3);
			
		} else {
			JOptionPane.showMessageDialog(null, "As matrizes não possuem as mesmas dimensões", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	public Matrizes multiplicarMatrizes() {
		
		String nomeMatriz1 = JOptionPane.showInputDialog(null, "Digite o nome da primeira matriz:");
		String nomeMatriz2 = JOptionPane.showInputDialog(null, "Digite o nome da segunda matriz:");
		String nomeMatrizNova = JOptionPane.showInputDialog(null, "Digite o nome da nova matriz a ser criada com a multiplicação");
		
		for (Matrizes matrizes : JanelaPrincipal.matrizesCriadas) {
			
			if(matrizes.getNome().equalsIgnoreCase(nomeMatriz1)) {
				matriz1 = matrizes;
			}
			
			if(matrizes.getNome().equalsIgnoreCase(nomeMatriz2)) {
				matriz2 = matrizes;
			}
			
		}
		
		if(matriz1.getColuna() == matriz2.getLinha()) {
			
			int[][] matriz3 = new int[matriz1.getLinha()][matriz2.getColuna()];
			
			for(int i = 0; i < matriz2.getLinha(); i ++) {
				for(int j = 0; j < matriz1.getColuna(); j++) {
					for(int k = 0; k < matriz2.getLinha(); k++) {
						
						matriz3[i][j] += (matriz1.getMatriz()[i][k]*matriz2.getMatriz()[k][j]);
						
					}
				}
			}
			
			return new Matrizes(nomeMatrizNova, matriz2.getLinha(), matriz1.getColuna(), matriz3);
			
		} else {
			JOptionPane.showMessageDialog(null, "As matrizes não podem ser multiplicadas", "ERROR", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
}
