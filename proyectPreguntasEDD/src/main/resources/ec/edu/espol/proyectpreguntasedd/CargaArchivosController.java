/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectpreguntasedd;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CargaArchivosController implements Initializable {

    @FXML
    private Button btCancelar;
    @FXML
    private Button btGuargar;
    @FXML
    private Label lb_nomFilePreguntas;
    @FXML
    private Button bt_cargarPreguntas;
    @FXML
    private Label lb_nomFileRespuestas;
    @FXML
    private Button bt_cargarRespuestas;
    
    static private String RutaPreguntas;
    
    static private String RutaRespuestas;
    @FXML
    private CheckBox ceck_archivosDefecto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public String getRutaPreguntas() {
        return RutaPreguntas;
    }

    public String getRutaRespuestas() {
        return RutaRespuestas;
    }

    
    
    
    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage=(Stage) btCancelar.getScene().getWindow();
        stage.close();
    }
    
    
    @FXML
    private void guardarArchivos(ActionEvent event) {
        if(ceck_archivosDefecto.isSelected()){
            RutaPreguntas = "preguntas.txt";
            RutaRespuestas = "respuestas.txt";
        }
        try {
            System.out.println(RutaPreguntas);
            System.out.println(RutaRespuestas);
            System.out.println("LLEGO 1");
            
//            Stage stg = (Stage)lb_nomFilePreguntas.getScene().getWindow();
//            stg.close();

            PrimaryController.RutaPreguntas = RutaPreguntas;
            PrimaryController.RutaRespuestas = RutaRespuestas;
            
            FXMLLoader loader = App.loadFXML("primary");
            Parent root= loader.load();
            
            System.out.println("LLEGO 2");
            App.scene.setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    @FXML
    private void cargarPreguntas(ActionEvent event) {
        FileChooser fileChooser = new FileChooser(); 
        Window stage = null;
        File filePreguntas = fileChooser.showOpenDialog(stage);
        RutaPreguntas = filePreguntas.getAbsolutePath();
        lb_nomFilePreguntas.setText(filePreguntas.getName());
    }
    
    
    @FXML
    private void cargarRespuestas(ActionEvent event) {
        FileChooser fileChooser = new FileChooser(); 
        Window stage = null;
        File fileRespuestas = fileChooser.showOpenDialog(stage);
        RutaRespuestas = fileRespuestas.getAbsolutePath();
        lb_nomFileRespuestas.setText(fileRespuestas.getName());
    }

    @FXML
    private void selecionarPorDefecto(ActionEvent event) {
        
    }
    
}
