/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectpreguntasedd;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 * FXML Controller class
 *
 * @author User
 */
public class SecondaryController implements Initializable {


    @FXML
    private TextField txt_Preguntas;
    @FXML
    private TextField txt_Respuestas;
    @FXML
    private Button btn_preguntas;
    @FXML
    private Button btn_respuestas;
    
    private File fileRespuestas;
    private File filePreguntas;
    @FXML
    private Label lb_preguntas;
    @FXML
    private Label error_R;
    @FXML
    private Label error_P;
    @FXML
    private Button btn_cargar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.error_P.setVisible(false);
        this.error_R.setVisible(false);
        this.txt_Preguntas.setEditable(false);
        this.txt_Respuestas.setEditable(false);
        
        
    }
    
    @FXML
    public void archivoPreguntas(){
        FileChooser fileChooser = new FileChooser(); 
        Window stage = null;
        this.filePreguntas = fileChooser.showOpenDialog(stage);
        this.txt_Preguntas.setText(filePreguntas.getName());
    }
    
    
    
    @FXML
    public void archivoRespuestas(){
        FileChooser fileChooser = new FileChooser(); 
        Window stage = null;
        this.fileRespuestas = fileChooser.showOpenDialog(stage);
        this.txt_Respuestas.setText(fileRespuestas.getName());
    }
    
    
    
    @FXML
    public void cargarDatos(){
        this.error_R.setVisible(false);
        this.error_P.setVisible(false); 
         
        if(this.filePreguntas==null)
            this.error_P.setVisible(true);
          
        if(this.fileRespuestas==null)
            this.error_R.setVisible(true);
       
        if(this.filePreguntas!=null && this.fileRespuestas!=null){
            cerrar();
        }
    }
    
    
    
    @FXML
    public void cerrar(){
        Stage stage=(Stage) this.btn_preguntas.getScene().getWindow();
        stage.close();
    }

    public TextField getTxt_Preguntas() {
        return txt_Preguntas;
    }

    public void setTxt_Preguntas(TextField txt_Preguntas) {
        this.txt_Preguntas = txt_Preguntas;
    }

    public TextField getTxt_Respuestas() {
        return txt_Respuestas;
    }

    public void setTxt_Respuestas(TextField txt_Respuestas) {
        this.txt_Respuestas = txt_Respuestas;
    }

    public Button getBtn_preguntas() {
        return btn_preguntas;
    }

    public void setBtn_preguntas(Button btn_preguntas) {
        this.btn_preguntas = btn_preguntas;
    }

    public Button getBtn_respuestas() {
        return btn_respuestas;
    }

    public void setBtn_respuestas(Button btn_respuestas) {
        this.btn_respuestas = btn_respuestas;
    }

    public File getFileRespuestas() {
        return fileRespuestas;
    }

    public void setFileRespuestas(File fileRespuestas) {
        this.fileRespuestas = fileRespuestas;
    }

    public File getFilePreguntas() {
        return filePreguntas;
    }

    public void setFilePreguntas(File filePreguntas) {
        this.filePreguntas = filePreguntas;
    }
   
}
