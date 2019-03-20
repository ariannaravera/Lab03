package it.polito.tdp.spellchecker.model;

public class RichWord {
	private String parola;
	private boolean trovata;
	
	public RichWord(String parola, boolean trovata) {
		this.parola = parola;
		this.trovata = trovata;
	}
	public String getParola() {
		return parola;
	}
	public boolean isTrovata() {
		return trovata;
	}

	
}
