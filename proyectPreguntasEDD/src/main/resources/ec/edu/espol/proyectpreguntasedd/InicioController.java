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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    static public BinaryTree<String> arbolPreguntas;
    
    private ArrayList<String> respuestas;
    
    static public int numPreguntas;
    @FXML
    private ImageView imGenioFinal;
    @FXML
    private ImageView imv_genio1;
    @FXML
    private Button btJugarDeNuevo;
    
    
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
//                lb_pregunta.setText(arbolPreguntas.getRootContent());
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
                    
                    imv_genio1.setVisible(false);
                    lb_pregunta.setText("   No conozco tu animal    ");
                    Image img = new Image("img/genio2.gif");
                    imGenioFinal.setImage(img);
                }
                
                else if(!arbolPreguntas.isLeaf()){
                    
                    LinkedList<String> sheets = arbolPreguntas.getTreeSheets();
                    lb_pregunta.setText("");
                    for(String s : sheets){
                        
                        if(!(s.contains("?"))){
                            lb_pregunta.setText(lb_pregunta.getText()+ " " + s + " ");
                        }
                    }

                }else{
                    
                    if(!(arbolPreguntas.getRootContent().contains("?"))){
                        
                        listAnimals.add(arbolPreguntas.getRootContent());   
                        lb_pregunta.setText(arbolPreguntas.getRootContent());
                    }

                    else{
                        
                        imv_genio1.setVisible(false);
                        lb_pregunta.setText("   No conozco tu animal    ");
                        Image img = new Image("img/genio2.gif");
                        imGenioFinal.setImage(img);
                    }
                }
                btJugarDeNuevo.setVisible(true);
            }
        }   
    }
    

    @FXML
    private void jugarDeNuevo(ActionEvent event) throws IOException {
        FXMLLoader loader = App.loadFXML("cargaArchivos");
        Parent root= loader.load();
        App.scene.setRoot(root);
    }
    
}
