package jokenpo;

public class Jogo {
	private Jogador jogador1, jogador2;
	private int roundAtual = 1;
	
	public Jogo() {}
	
	public Jogo(String nickJogador1, String nickJogador2) {
		jogador1 = new Jogador(nickJogador1);
		jogador2 = new Jogador(nickJogador2);
	};
	
	/**
	 * 
	 * @param jogada1 - jogada realizada pelo jogador 1
	 * @param jogada2 - jogada realizada pelo jogador 2
	 * @return String - nome do jogador vencedor.
	 */
	public String fazJogada(Jogada jogada1, Jogada jogada2) {
		if(jogada1.ganhaDe(jogada2)) {
			roundAtual = getRoundAtual() + 1;
			jogador1.setVitorias(jogador1.getVitorias()+1);
			return jogador1.getNome();
		}

		if(jogada2.ganhaDe(jogada1)) {
			roundAtual = getRoundAtual() + 1;
			jogador1.setVitorias(jogador1.getVitorias()+1);
			return jogador2.getNome();
		}
		
		/**
		 * Empate
		 */
		return null;
		//verificaFim();
	}
	
	void verificaFim(){
		if(jogador1.getVitorias() >= 3) {
			//Jogador1 Ganha
		}else if(jogador2.getVitorias() >= 3) {
			//Jogador1 Ganha
		}
	}

	public int getRoundAtual() {
		return roundAtual;
	}
	
	public Jogador getJogador1() {
		return jogador1;
	}

	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}
}
