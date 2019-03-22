package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
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
    private TextArea txtInput;

    @FXML
    private Button btnSpell;

    @FXML
    private TextArea txtResult;

    @FXML
    private ChoiceBox<String> tendina;

    @FXML
    private Label errTxt;

    @FXML
    private Button btnClear;

    @FXML
    private Label txtTempo;

    @FXML
    void doClearText(ActionEvent event) {
    	txtResult.clear();
    	txtInput.clear();
    	errTxt.setText("");
    	txtTempo.setText("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	double t = System.nanoTime()/Math.pow(10, 9);
    	String lingua = tendina.getValue();
    	//int cont = 0;
    	model.loadDictionary("rsc/"+lingua+".txt");
    	String input = txtInput.getText().replaceAll("[.,\\\\/#!?$%\\\\^&\\\\*;:{}=\\\\-_`~()\\\\[\\\\]\\\"]", "").toLowerCase();
    	String testo[] = input.split(" ");
    	/*for(int i=0; i<testo.length; i++) {
    		if(!model.getDizionario().contains(testo[i])) {
    			txtResult.appendText(testo[i]+"\n");
    			cont++;
    		}
    	}*/
    	
    	int correct = model.spellCheckDicothomic(testo).size();
    	int err = testo.length - correct;
    	
    	/*if(cont == 1)
    		errTxt.setText("C'è "+cont+" errore.");
    	else
    		errTxt.setText("Ci sono "+cont+" errori.");
    	txtTempo.setText(System.nanoTime()/Math.pow(10, 9)-t+" secondi");*/
    	
    	if(err == 1)
    		errTxt.setText("C'è 1 errore.");
    	else
    		errTxt.setText("Ci sono "+err+" errori.");
    	txtTempo.setText(System.nanoTime()/Math.pow(10, 9)-t+" secondi");
    	
    	
    	
    }

    @FXML
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert tendina != null : "fx:id=\"tendina\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert errTxt != null : "fx:id=\"errTxt\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'SpellChecker.fxml'.";
    }
    
    public void setModel(Dictionary model) {
    	this.model = model;
    	tendina.getItems().addAll("English", "Italian");
    }
}

