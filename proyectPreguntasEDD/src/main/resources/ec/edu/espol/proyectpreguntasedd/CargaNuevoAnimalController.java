/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectpreguntasedd;

import TDA.BinaryTree;
import static ec.edu.espol.proyectpreguntasedd.InicioController.arbolPreguntas;
import static ec.edu.espol.proyectpreguntasedd.InicioController.numPreguntas;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField txf_animal;
    
    static public String RutaPreguntas_nuevoAnimal;
    static public String RutaRespuestas_nuevoAnimal;
    
    private int numQuestionOfFile;
    private LinkedList<String> preguntas_nuevoAnimal = new LinkedList();
    private ArrayList<String> respuestas_nuevoAnimal = new ArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            txf_animal.setDisable(true);
            txf_animal.setVisible(false);
            Adivinador adivinador_CargaNuevoAnimal = new Adivinador();
            ArrayList<String> createListOfQuestion = adivinador_CargaNuevoAnimal.createListOfQuestion(RutaPreguntas_nuevoAnimal);
            preguntas_nuevoAnimal.addAll(createListOfQuestion);
            lb_pregunta.setText(preguntas_nuevoAnimal.removeFirst());
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }    
    
    @FXML
    private void guardar(ActionEvent event) {
        if(!preguntas_nuevoAnimal.isEmpty()){
            
            if(rb_no.isSelected() && !rb_si.isSelected())
                respuestas_nuevoAnimal.add("no");
            else 
                respuestas_nuevoAnimal.add("si");
            
            lb_pregunta.setText(preguntas_nuevoAnimal.removeFirst());
            rb_si.setSelected(false);
            rb_no.setSelected(false);
            bt_guardar.setDisable(true);
            numPreguntas--;
            
        }else if(preguntas_nuevoAnimal.isEmpty() && !lb_pregunta.getText().equals("Ingrese el animal:")){
            
            if(rb_no.isSelected() && !rb_si.isSelected())
                respuestas_nuevoAnimal.add("no");
            else 
                respuestas_nuevoAnimal.add("si");
            lb_pregunta.setText("");
            lb_pregunta.setText("Ingrese el animal:");
            txf_animal.setVisible(true);
            txf_animal.setDisable(false);
            disable();
            System.out.println(respuestas_nuevoAnimal);
       
        }else{
            
            String animal = txf_animal.getText();
            if(animal != null && !animal.equals("")){
                bt_guardar.setDisable(false);
                editarArchivoDeRespuestas(RutaRespuestas_nuevoAnimal, animal);
            }
            disable();
        }
    }
    
    
    private void disable (){
        
      rb_no.setVisible(false);
      rb_no.setDisable(true);
      rb_si.setVisible(false);
      rb_si.setDisable(true);
      bt_guardar.setDisable(true);
      
    }
    
    
    
    @FXML
    private void jugarDeNuevo(ActionEvent event) throws IOException {
        
        FXMLLoader loader = App.loadFXML("cargaArchivos");
        Parent root = loader.load();
        App.scene.setRoot(root);
        
    }
    
    
    
    private void editarArchivoDeRespuestas(String nomArchivoRespuestas, String animal){ 
        try{
            
            FileWriter writer = new FileWriter(nomArchivoRespuestas, true);
            BufferedWriter bufferedWriter= new BufferedWriter(writer);
            bufferedWriter.write(animal);
            bufferedWriter.write(" ");
            for(String respuestas : respuestas_nuevoAnimal){
                bufferedWriter.write(respuestas);
                bufferedWriter.write(" ");
            }
            System.out.println("Guardo bien");
            bufferedWriter.newLine();
            bufferedWriter.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    @FXML
    private void select_si(ActionEvent event) {
        rb_no.setSelected(false); 
        bt_guardar.setDisable(false);
    }
    
    
    
    @FXML
    private void select_no(ActionEvent event) {
        rb_si.setSelected(false); 
        bt_guardar.setDisable(false);
    }

}
