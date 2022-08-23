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
    
    private String RutaPreguntas;
    private String RutaRespuestas;
    @FXML
    private CheckBox ceck_archivosDefecto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            Stage stg = (Stage)bt_cargarRespuestas.getScene().getWindow();
            stg.close();
            System.out.println("LLEGO 1");
            FXMLLoader fxmlLoader2 = new FXMLLoader(App.class.getResource("primary.fxml"));
            Scene sc = new Scene(fxmlLoader2.load());
            PrimaryController primaryControl = fxmlLoader2.<PrimaryController>getController();
            
            //NO ESTÁ SETEANDO EN LA VENTANA PRIMARY!!!!!
            primaryControl.setRutaPreguntas(RutaPreguntas);
            primaryControl.setRutaRespuestas(RutaRespuestas);
            
            System.out.println(RutaPreguntas);
            System.out.println(RutaRespuestas);
            
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    @FXML
    private void cargarPreguntas(ActionEvent event) {
        FileChooser fileChooser = new FileChooser(); 
        Window stage = null;
        File filePreguntas = fileChooser.showOpenDialog(stage);
        RutaPreguntas = filePreguntas.getPath();
        lb_nomFilePreguntas.setText(filePreguntas.getName());
    }
    
    
    @FXML
    private void cargarRespuestas(ActionEvent event) {
        FileChooser fileChooser = new FileChooser(); 
        Window stage = null;
        File fileRespuestas = fileChooser.showOpenDialog(stage);
        RutaPreguntas = fileRespuestas.getPath();
        lb_nomFileRespuestas.setText(fileRespuestas.getName());
    }

    @FXML
    private void selecionarPorDefecto(ActionEvent event) {
        
    }
    
}
