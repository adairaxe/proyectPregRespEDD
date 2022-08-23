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
    
    private int numMaxPreguntas;
    private String RutaPreguntas;
    private String RutaRespuestas;

    public void setNumMaxPreguntas(int numMaxPreguntas) {
        this.numMaxPreguntas = numMaxPreguntas;
    }

    public void setRutaPreguntas(String RutaPreguntas) {
        this.RutaPreguntas = RutaPreguntas;
    }

    public void setRutaRespuestas(String RutaRespuestas) {
        this.RutaRespuestas = RutaRespuestas;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playButton.setVisible(false);
        this.mas.setVisible(false);
        this.menos.setVisible(false);
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
