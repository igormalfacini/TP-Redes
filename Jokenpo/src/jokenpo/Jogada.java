package jokenpo;

public enum Jogada {

    PEDRA("PEDRA","TESOURA"),
    PAPEL("PAPEL", "PEDRA"),
    TESOURA("TESOURA", "PAPEL");

	private String nome;
    private String ganhaDe;

    Jogada(String nome, String ganhaDe) {
    	this.nome = nome;
        this.ganhaDe = ganhaDe;
    }

	public boolean ganhaDe(Jogada jogada) {
		return this.ganhaDe.equals(jogada.nome);
	}
}
