/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectpreguntasedd;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private Button cargarButton;
    @FXML
    private Button playButton;
    private String pathRespuestas;
    private String pathPreguntas;
    @FXML
    private Label txt_notificacion;
    
    public int numeroPreguntas;
    
    Util adivinador= new Util();
    @FXML
    private CheckBox sin_archivos;
    @FXML
    private Button cargarDenuevo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playButton.setVisible(false);
        this.numeroPreguntas=0;
        num_preguntas.setText(String.valueOf(numeroPreguntas));
        this.mas.setVisible(false);
        this.menos.setVisible(false);
    }
    
    @FXML
    public void cargarDatos(){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("secondary.fxml"));
            Parent root = loader.load();
            SecondaryController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            this.pathPreguntas=controlador.getFilePreguntas().getPath();
            this.pathRespuestas=controlador.getFileRespuestas().getPath();
            this.playButton.setVisible(true);
            this.mas.setVisible(false);
            this.menos.setVisible(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @FXML
    public void aumentar(){
        System.out.println(pathPreguntas);
        System.out.println(Util.calculateMaxQuestions(pathPreguntas));
        if(this.numeroPreguntas<=Util.calculateMaxQuestions(pathPreguntas)){
            this.numeroPreguntas++;
        }
        num_preguntas.setText(String.valueOf(numeroPreguntas));
    }
    
    @FXML
    public void disminuir(){
        if(this.numeroPreguntas>0){
            this.numeroPreguntas--;
        }
        num_preguntas.setText(String.valueOf(numeroPreguntas));
    }
    @FXML
    public void defaultMode(){
        cargarButton.setVisible(true);
        this.mas.setVisible(false);
        this.menos.setVisible(false);
        numeroPreguntas=0;
        num_preguntas.setText(String.valueOf(numeroPreguntas));
        this.pathPreguntas=null;
        this.pathRespuestas=null;
        this.playButton.setVisible(false);
        if(this.sin_archivos.selectedProperty().get()){
            cargarButton.setVisible(false);
            this.pathPreguntas="preguntas.txt";
            this.pathRespuestas="respuestas.txt";
            this.mas.setVisible(true);
            this.menos.setVisible(true);
            this.playButton.setVisible(true);
        }
        
    }
    
    public void resetDatos(){
        this.sin_archivos.setSelected(false);
        defaultMode();
        
          
    }
    
}
