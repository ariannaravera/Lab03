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
    	String s=txtTesto.getText().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"] "," ");
    	String p[]=s.split(" ");
    	for(String par:p) {
    		inputTextList.add(par);
    	}
    	List<String> errori=new LinkedList<String>();
		for(RichWord rw:model.spellCheckText(inputTextList))
			if(rw.isTrovata()==false)
				errori.add(rw.getParola());
    	String r="";
    	for(String str:errori)
    		r+=str+"\n";
    	txtErrori.appendText(r);
    	txtResErr.setText("The text contains "+errori.size()+" errors.");
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

