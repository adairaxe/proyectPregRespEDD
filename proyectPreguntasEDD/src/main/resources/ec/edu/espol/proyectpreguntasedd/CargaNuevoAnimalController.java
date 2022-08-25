/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectpreguntasedd;

import TDA.BinaryTree;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            Adivinador adivinador_CargaNuevoAnimal = new Adivinador();
            adivinador_CargaNuevoAnimal.createListOfQuestion(RutaPreguntas_nuevoAnimal);
            adivinador_CargaNuevoAnimal.createMapOfAnswer(RutaRespuestas_nuevoAnimal);
            Stack<BinaryTree<String>> createBinaryTreeQuestion = adivinador_CargaNuevoAnimal.createBinaryTreeQuestion();
            adivinador_CargaNuevoAnimal.createBinaryTreeRoot(createBinaryTreeQuestion);
            adivinador_CargaNuevoAnimal.chargeAllAnswer();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        

    }    
    
    @FXML
    private void guardar(ActionEvent event) {
    }

    @FXML
    private void jugarDeNuevo(ActionEvent event) {
    }

}
