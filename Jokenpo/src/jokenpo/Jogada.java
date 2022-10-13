package jokenpo;

//1 = Pedra
//2 = Papel
//3 = Tesoura

public class Jogada {
	Jogador jogador1, jogador2;
	
	public Jogada(){};
	
	public void inserirJogador(String nome){
		if(jogador1 == null)
			this.jogador1 = new Jogador(nome);
		this.jogador2 = new Jogador(nome);
	};
	
	public void fazJogada(int jogada1, int jogada2) {
		if(jogada1 == jogada2) {
			//Draw
			System.out.println("Empate");
		}
		else if(jogada1 == 1) {
			if(jogada2 == 2){
				//jogador2 Win
				jogador2.setVitorias(jogador2.getVitorias() + 1);
			}
			if(jogada2 == 3){
				//jogador1 Win
				jogador1.setVitorias(jogador1.getVitorias() + 1);
			}
		}
		else if(jogada1 == 2) {
			if(jogada2 == 3){
				//jogador2 Win
				jogador2.setVitorias(jogador2.getVitorias() + 1);
			}
			if(jogada2 == 1){
				//jogador1 Win
				jogador1.setVitorias(jogador1.getVitorias() + 1);
			}
		}
		else {
			if(jogada2 == 1){
				//jogador2 Win
				jogador2.setVitorias(jogador2.getVitorias() + 1);
			}
			if(jogada2 == 2){
				//jogador1 Win
				jogador1.setVitorias(jogador1.getVitorias() + 1);
			}
		}
		verificaFim();
	}

	void verificaFim(){
		if(jogador1.getVitorias() >= 3) {
			//Jogador1 Ganha
		}else if(jogador2.getVitorias() >= 3) {
			//Jogador1 Ganha
		}
	}
}
