package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	private String parola;
	private boolean isCorrect;
	
	public RichWord(String parola, boolean isCorrect) {
		this.parola = parola;
		isCorrect = false;
	}

	/**
	 * @return the parola
	 */
	public String getParola() {
		return parola;
	}

	/**
	 * @return the isCorrect
	 */
	public boolean isCorrect() {
		return isCorrect;
	}

}
