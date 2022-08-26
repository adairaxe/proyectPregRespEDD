/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectpreguntasedd;

import TDA.BinaryTree;
import static ec.edu.espol.proyectpreguntasedd.InicioController.arbolPreguntas;
import static ec.edu.espol.proyectpreguntasedd.InicioController.numPreguntas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
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
public class CargaNuevoAnimalController implements Initializable {


    @FXML
    private Label lb_pregunta;
    @FXML
    private RadioButton rb_si;
    @FXML
    private RadioButton rb_no;
    @FXML
    private Button bt_guardar;
    @FXML
    private Button btJugarDeNuevo;
    
    static public String RutaPreguntas_nuevoAnimal;
    static public String RutaRespuestas_nuevoAnimal;
    
    private BinaryTree<String> treeCargarAnimal;
    private int numQuestionOfFile;
    private ArrayList<String> respuestas_nuevoAnimal = new ArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            Adivinador adivinador_CargaNuevoAnimal = new Adivinador();
            ArrayList<String> createListOfQuestion = adivinador_CargaNuevoAnimal.createListOfQuestion(RutaPreguntas_nuevoAnimal);
            numQuestionOfFile = createListOfQuestion.size();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        

    }    
    
    @FXML
    private void guardar(ActionEvent event) {
        
    }
    
    
    private void disable (){
        
      rb_no.setVisible(false);
      rb_no.setDisable(true);
      rb_si.setVisible(false);
      rb_si.setDisable(true);
      bt_guardar.setVisible(false);
      
    }
    
    
    
    @FXML
    private void jugarDeNuevo(ActionEvent event) {
    }

}
