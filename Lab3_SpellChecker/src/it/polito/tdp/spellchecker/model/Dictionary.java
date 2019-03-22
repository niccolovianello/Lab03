package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
	
	private List<String> dizionarioLinked = new LinkedList<String>();
	private List<RichWord> parole = new LinkedList<RichWord>();
	//private List<String> dizionarioArray = new ArrayList<String>();
	
	public void loadDictionary(String input) {
		try {
			FileReader fr = new FileReader(input);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				dizionarioLinked.add(word.toLowerCase().replaceAll("[.,\\\\/#!?$%\\\\^&\\\\*;:{}=\\\\-_`~()\\\\[\\\\]\\\"]", ""));
				//dizionarioArray.add(word.toLowerCase().replaceAll("[.,\\\\/#!?$%\\\\^&\\\\*;:{}=\\\\-_`~()\\\\[\\\\]\\\"]", ""));
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
		return dizionarioLinked;
		//return dizionarioArray;
	}
	
	public List<RichWord> spellCheckDicothomic(String[] inputTextList) {
		double t = System.nanoTime()/Math.pow(10, 9);
		int endIndex = (int)dizionarioLinked.size();
		int startIndex = 0;
		boolean trovata = false;
		parole.clear();
		
		while(trovata == false && endIndex > startIndex) {
			
			int half = (int)((endIndex-startIndex)/2);
			String word= dizionarioLinked.get(half);
			
			for(String p : inputTextList) {
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
				parole.add(new RichWord(p, trovata));
			}
		}
		return parole;
	}
	
	
}


