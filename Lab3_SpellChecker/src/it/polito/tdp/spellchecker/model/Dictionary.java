package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	private List<String> dizionario=new LinkedList<String>();
	private List<RichWord> parole=new LinkedList<RichWord>();
	private float tinizio;
	
	public void loadDictionary(String language) {
		dizionario.clear();
		FileReader fr;
		BufferedReader br = null;
		try {
			if(language.equals("english")) {
				fr = new FileReader( "rsc/English.txt" );
				br = new BufferedReader(fr);
				}
			else if(language.equals("italian")) {
				fr = new FileReader( "rsc/Italian.txt" );
				br = new BufferedReader(fr);
				}
			String word;
			while ((word= br.readLine())!=null ) {
				dizionario.add(word.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", ""));
			}
			br .close();
			} catch (IOException e ){
			System. out .println( "Errore nella lettura del file" );
			}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList ) {
		tinizio=System.nanoTime();
		parole.clear();
		
		for(String s:inputTextList) {
			boolean t=false;
			s.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]","");
			for(String p:dizionario) {
				if(s.equals(p))
					t=true;
				}
			parole.add(new RichWord(s,t));
		}
		
		return parole;
	}
	
	public float tempo() {
		float tfine=System.nanoTime();
		return (float)((tfine-tinizio)/Math.pow(10,9));
	}
	
	
}
