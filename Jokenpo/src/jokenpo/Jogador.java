package jokenpo;

public class Jogador {
	private String nome;
	private int vitorias;
	
	public Jogador(String nome) {
		vitorias = 0;
		nome = this.nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getVitorias() {
		return vitorias;
	}
	
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}
}
