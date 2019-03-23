package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> tendina;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button butCheck;

    @FXML
    private TextArea txtErrori;

    @FXML
    private Label txtResErr;

    @FXML
    private Button butClear;

    @FXML
    private Label txtResTime;

    @FXML
    void doCheck(ActionEvent event) {
    	float tinizio=System.nanoTime();
    	String lingua=tendina.getValue();
    	model.loadDictionary(lingua.toLowerCase());
    	List<String> inputTextList=new LinkedList<String>();
    	String s=txtTesto.getText().replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"] "," ").toLowerCase();
    	String p[]=s.split(" ");
    	for(String par:p) {
    		inputTextList.add(par);
    	}
    	List<String> l1=new LinkedList<String>();
    	List<String> l2=new LinkedList<String>();
    	List<String> a1=new ArrayList<String>();
    	List<String> a2=new ArrayList<String>();
    	
    	/**
    	 * Confrontare le differenze di prestazioni tra le due implementazioni
    	 *  utilizzando sia un ArrayList ed una
    	 *  LinkedList . Riempire la tabella nella pagina seguente con i tempi di
    	 *  esecuzione per ciascun caso.
    	 *  l1=0.8389sec
    	 *  l2=0.8053sec
    	 *  a1=0.9059sec
    	 *  a2=0.7382sec
    	 *  
    	 *  Quale implementazione utilizza il metodo contains di Java List ?
    	 */
    	for(RichWord rw:model.spellCheckTextLinear(inputTextList))
			if(rw.isTrovata()==false) {
				l1.add(rw.getParola());
				a1.add(rw.getParola());
			}
		for(RichWord rw:model.spellCheckTextDichotomic(inputTextList))
			if(rw.isTrovata()==false){
				l2.add(rw.getParola());
				a2.add(rw.getParola());
			}
    	String rl1="";
    	String rl2="";
    	String ra1="";
    	String ra2="";
    	for(String str:l1)
    		rl1+=str+"\n";
    	for(String str:l2)
    		rl2+=str+"\n";
    	for(String str:a1)
    		ra1+=str+"\n";
    	for(String str:a2)
    		ra2+=str+"\n";
    	txtErrori.appendText(rl1);
    	txtResErr.setText("The text contains "+l1.size()+" errors.");
    	float tfine=System.nanoTime();
    	txtResTime.setText("Spell check completed in "+((tfine-tinizio)/Math.pow(10,9))+" seconds.");
    	
    }

    @FXML
    void doClear(ActionEvent event) {
    	txtTesto.clear();
    	txtErrori.clear();
    	txtResErr.setText("");
    	txtResTime.setText("");
    }

    @FXML
    void initialize() {
        assert tendina != null : "fx:id=\"tendina\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert butCheck != null : "fx:id=\"butCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResErr != null : "fx:id=\"txtResErr\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert butClear != null : "fx:id=\"butClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResTime != null : "fx:id=\"txtResTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
    
    public void setModel(Dictionary model) {
    	this.model=model;
    	tendina.getItems().addAll("English","Italian");
    }
    
}

