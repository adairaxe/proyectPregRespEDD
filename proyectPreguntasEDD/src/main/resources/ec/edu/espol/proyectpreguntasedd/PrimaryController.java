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
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Adivinador;
import model.Util;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class PrimaryController implements Initializable {

    @FXML
    private ImageView menos;
    @FXML
    private ImageView mas;
    @FXML
    private Label num_preguntas;
    @FXML
    private Button playButton;
    @FXML
    
    private Label txt_notificacion;
    
    private Adivinador adivinadorEnviar;
    
    private int numMaxPreguntas;
    static public String RutaPreguntas_primary;
    static public String RutaRespuestas_primary;
    private BinaryTree<String> treeQuestion;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Adivinador adivinador = new Adivinador();
        
        try {
 
            ArrayList<String> createListOfQuestion = adivinador.createListOfQuestion(RutaPreguntas_primary);            
            numMaxPreguntas = createListOfQuestion.size();
            Map<String, ArrayList<String>> createMapOfAnswer = adivinador.createMapOfAnswer(RutaRespuestas_primary);            
            adivinador.createListAleatoryOfQuestion(createListOfQuestion, createMapOfAnswer);
            Stack<BinaryTree<String>> createBinaryTreeQuestion = adivinador.createBinaryTreeQuestion();
            adivinador.createBinaryTreeRoot(createBinaryTreeQuestion);
            adivinador.chargeAllAnswer();
            treeQuestion = adivinador.getTreeOfGame();
            
            adivinadorEnviar = adivinador;
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    @FXML
    private void play() throws IOException{
        
        FXMLLoader loader = App.loadFXML("inicio");
        InicioController.arbolPreguntas = treeQuestion;
        InicioController.numPreguntas = Integer.parseInt(num_preguntas.getText()) - 1;
        InicioController.adivinador_Inico = adivinadorEnviar;
        
        InicioController.RutaPreguntas_inicio = RutaPreguntas_primary;
        InicioController.RutaRespuestas_inicio = RutaRespuestas_primary;
        
        Parent root= loader.load();
        App.scene.setRoot(root);

    }
    
    
    
    @FXML
    private void disminuir(MouseEvent event) {
        
        int num = Integer.parseInt(num_preguntas.getText());
        if(!(num == 1)){
            num = num - 1;
            num_preguntas.setText("");
            num_preguntas.setText(String.valueOf(num));
        }else{
            Alert a1 = new Alert(Alert.AlertType.ERROR,"No puedes escoger menos de una preguntas");
            a1.show();
        }
    }
    
    
    
    @FXML
    private void aumentar(MouseEvent event) {
        int num = Integer.parseInt(num_preguntas.getText());
        if(!(num == numMaxPreguntas)){
            num = num + 1;
            num_preguntas.setText("");
            num_preguntas.setText(String.valueOf(num));
        }else{
            Alert a1 = new Alert(Alert.AlertType.ERROR,"No puedes escoger más del número de preguntas que tiene tu archivo");
            a1.show();
        }   
    }

    
    
}
