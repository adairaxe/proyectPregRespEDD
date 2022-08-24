/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectpreguntasedd;

import TDA.BinaryTree;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import model.Adivinador;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class InicioController implements Initializable {

    @FXML
    private Label lb_pregunta;
    @FXML
    private RadioButton rbt_no;
    @FXML
    private RadioButton rbt_si;
    @FXML
    private Button bt_siguiente;
    
    static public BinaryTree<String> arbolPreguntas;
    
    private ArrayList<String> respuestas;
    
    static public int numPreguntas;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lb_pregunta.setText(arbolPreguntas.getRootContent());
        
    }    

    @FXML
    private void selec_no(ActionEvent event) {
        rbt_si.setSelected(false);
    }

    @FXML
    private void selec_si(ActionEvent event) {
        rbt_no.setSelected(false);  
    }

    @FXML
    private void siguiente_pregunta(ActionEvent event) {
        String text;
        if(rbt_no.isSelected() && !rbt_si.isSelected()){     
            text = rbt_no.getText();
            //respuestas.add(text);
            arbolPreguntas = arbolPreguntas.getRight();
            lb_pregunta.setText(arbolPreguntas.getRootContent());
        }
        else{
            text = rbt_si.getText();
            //respuestas.add(text);
            arbolPreguntas = arbolPreguntas.getLeft();
            lb_pregunta.setText(arbolPreguntas.getRootContent());
        }
        
        lb_pregunta.setText(arbolPreguntas.getRootContent());
        rbt_si.setSelected(false);
        rbt_no.setSelected(false);
    }
    
}
