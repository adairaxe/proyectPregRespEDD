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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.DepthTest;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    
    @FXML
    private ImageView imGenioFinal;
    @FXML
    private ImageView imv_genio1;
    @FXML
    private Button btJugarDeNuevo;
    @FXML
    private VBox vb_principal;
    
    static public Adivinador adivinador_Inico;
    static public String RutaPreguntas_inicio;
    static public String RutaRespuestas_inicio;
    static public int numPreguntas;
    
    static public BinaryTree<String> arbolPreguntas;
    
    private ArrayList<String> respuestas;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btJugarDeNuevo.setVisible(false);
        respuestas = new ArrayList();
        lb_pregunta.setText(arbolPreguntas.getRootContent());
        if(!rbt_no.isSelected() && !rbt_si.isSelected()){
            bt_siguiente.setDisable(true);
        }
        
    }  
    
    
    
    @FXML
    private void selec_no(ActionEvent event) {
        rbt_si.setSelected(false);
        bt_siguiente.setDisable(false);
    }
    
    
    
    @FXML
    private void selec_si(ActionEvent event) {
        rbt_no.setSelected(false); 
        bt_siguiente.setDisable(false);
    }
    
    
    
    @FXML
    private void siguiente_pregunta(ActionEvent event) {
        if(numPreguntas >= 0){
            String text;
            
            if(rbt_no.isSelected() && !rbt_si.isSelected()){  
                text = rbt_no.getText();
                System.out.println(text);
                respuestas.add(text);
                arbolPreguntas = arbolPreguntas.getRight();
            }
            else {
                text = rbt_si.getText();
                respuestas.add(text);
                arbolPreguntas = arbolPreguntas.getLeft();
            }
            
            rbt_si.setSelected(false);
            rbt_no.setSelected(false);
            bt_siguiente.setDisable(true);
            numPreguntas--;
            
            if(numPreguntas >= 0)
                lb_pregunta.setText(arbolPreguntas.getRootContent());
            
            else{
                
                LinkedList<String> listAnimals = new LinkedList();
                if(arbolPreguntas == null){
                    genioError();
                    
                }
                
                else if(!arbolPreguntas.isLeaf()){
                    
                    LinkedList<String> sheets = arbolPreguntas.getTreeSheets();
                    lb_pregunta.setText("");
                    for(String s : sheets){
                        
                        if(!(s.contains("?"))){
                            lb_pregunta.setText(lb_pregunta.getText()+ " " + s + " ");
                        }  
                    }
                    if("".equals(lb_pregunta.getText())){
                            genioError();
                        }
                    
                }else{
                    
                    if(!(arbolPreguntas.getRootContent().contains("?"))){              
                        listAnimals.add(arbolPreguntas.getRootContent());   
                        lb_pregunta.setText(arbolPreguntas.getRootContent()); 
                    }

                    else{          
                        genioError();  
                    }
                }
                disable();
                btJugarDeNuevo.setVisible(true);
            }
        }   
    }
    
    
    
    private void disable (){
        
      rbt_no.setVisible(false);
      rbt_no.setDisable(true);
      rbt_si.setVisible(false);
      rbt_si.setDisable(true);
      bt_siguiente.setDisable(true);
      bt_siguiente.setVisible(false);
      
    }
    
    
    
    private void genioError (){
        
        imv_genio1.setVisible(false);
        lb_pregunta.setText("No conozco tu animal pero...");
        Image img = new Image("img/genio2.gif");
        imGenioFinal.setImage(img);
        Button butonAgregarAnimal = new Button();
        butonAgregarAnimal.setText("Agrega el animal");
        butonAgregarAnimal.setOnMouseClicked(
                (MouseEvent e)->
                    {
                        try {
                            
                            CargaNuevoAnimalController.RutaPreguntas_nuevoAnimal = RutaPreguntas_inicio;
                            CargaNuevoAnimalController.RutaRespuestas_nuevoAnimal = RutaRespuestas_inicio;
                            FXMLLoader loader = App.loadFXML("cargaNuevoAnimal");
                            Parent root = loader.load();
                            App.scene.setRoot(root);
                             
                        } catch (IOException ex) {
                            
                            ex.printStackTrace();
                        }      
                    }
        );
        vb_principal.getChildren().add(butonAgregarAnimal);
    }
    
    
    
    @FXML
    private void jugarDeNuevo(ActionEvent event) throws IOException {
        
        FXMLLoader loader = App.loadFXML("cargaArchivos");
        Parent root = loader.load();
        App.scene.setRoot(root);
        
    }
    
}
