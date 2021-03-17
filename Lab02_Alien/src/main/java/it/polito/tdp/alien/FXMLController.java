/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtInput"
    private TextField txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="buttonTraslate"
    private Button buttonTraslate; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

	private DataTraslate model;

    @FXML
    void translate(ActionEvent event) {
    	String testoCompleto =  this.txtInput.getText();
    	testoCompleto = testoCompleto.toLowerCase();
    	String arrayTesto [] = testoCompleto.split(" ");
    	if(testoCompleto.matches("[a-zA-Z\s?]+")==false) {
    		this.txtResult.setText("si possono inserire solo caratteri alfabetici\n");
    		return ;
    	}
    	//String arrayTesto [] = testoCompleto.split(" ");
    	if(arrayTesto.length>2) {
    		this.txtResult.setText("si possono inserire al più due parole\nuna la WordAlien e una la WordHeart\n");
    		this.txtInput.clear();
    		return ;
    	}
    	if(arrayTesto.length == 2) { //popolamenot BD
    		boolean flag = true;
    		try {
				flag = this.model.addTraslate(arrayTesto[0], arrayTesto[1]);
			} catch (ExeceptionDuplicateTraslate e) {
				// TODO Auto-generated catch block
				try {
					this.txtResult.setText("Esiste una già una trduzione associata a tela WordAlien ed è:\n"+this.model.ricerca(arrayTesto[0])+"\n");
				} catch (ExceptionTraduzineNonTrovata e1) {
					// TODO Auto-generated catch block
					this.txtResult.setText("parola non trovata\n");
				}
				this.txtInput.clear();
				return;
			}
    		if(flag) {
    			this.txtResult.setText("taduzione aggiunta corretamente\n");
    		}
    	}
    	if(arrayTesto.length == 1) {
    		try {
				this.txtResult.setText(""+this.model.ricerca(arrayTesto[0])+"\n");
			} catch (ExceptionTraduzineNonTrovata e1) {
				// TODO Auto-generated catch block
				this.txtResult.setText("parola non trovata\n");
			}
    		this.txtInput.clear();
    		return ;
    	}
    	this.txtInput.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonTraslate != null : "fx:id=\"buttonTraslate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
    }

	public void setModel(DataTraslate model) {
		this.model = model;
		
	}
}
