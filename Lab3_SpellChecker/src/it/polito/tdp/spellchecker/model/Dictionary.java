package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
	
	private List<String> dizionario = new LinkedList<String>();
	private List<RichWord> parole = new LinkedList<RichWord>();
	
	public void loadDictionary(String input) {
		try {
			FileReader fr = new FileReader(input);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				dizionario.add(word.toLowerCase().replaceAll("[.,\\\\/#!?$%\\\\^&\\\\*;:{}=\\\\-_`~()\\\\[\\\\]\\\"]", ""));
			}
			br.close();
		}
		catch(IOException e) {
			System.err.println("Errore nella lettura del testo.");
		}
	}

	/**
	 * @return the dizionario
	 */
	public List<String> getDizionario() {
		return dizionario;
	}
	
	public List<RichWord> spellCheckLinear(String[] inputTextList) {
		parole.clear();
		
		for(String p : inputTextList){
			
			boolean trovata = false;
			if(dizionario.contains(p)) {
				trovata = true;
				parole.add(new RichWord(p, trovata));
			}
			else
				parole.add(new RichWord(p, trovata));
		}
		return parole;
	}
	
	public List<RichWord> spellCheckDicothomic(String[] inputTextList) {
		parole.clear();
		
		for(String p : inputTextList){
			
			boolean trovata = false;
			int startIndex = 0;
			int endIndex = (int)dizionario.size()-1;
			
			while(trovata == false && endIndex > startIndex && endIndex-startIndex != 1) {
				
				int half = (int)((endIndex+startIndex)/2);
				String word= dizionario.get(half);
				
				if(p.compareTo(word) == 0) {
					trovata = true;
					break;
				}
				else {
					if(p.compareTo(word)<0) {
						endIndex = half;
					}
					else {
						startIndex = half;
					}
				}
			}
			parole.add(new RichWord(p, trovata));
		}
		return parole;
	}
	
	
}


