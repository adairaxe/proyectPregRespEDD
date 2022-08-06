package ec.edu.espol.proyectpreguntasedd;

import TDA.BinaryTree;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import model.Util;
import static model.Util.chargeAnimals;
import static model.Util.createMapSheets;
import static model.arbolDecisiones.buildTreeDecisionxd;
import model.nodoProyecto;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
//        launch();

        // ARBOL CARGADO CON PREGUNTAS
        Stack<BinaryTree<String>> createStackQuestions = Util.createStackQuestions("preguntas.txt");
        BinaryTree<String> BinaryTreeQuestion = Util.createBinaryTreeQuestion(createStackQuestions);
        
        Map<String, Queue<String>> createMapSheets = Util.createMapSheets("respuestas.txt");
//        for(String animal : createMapSheets.keySet()){
//            System.out.println(animal + " : " + createMapSheets.get(animal));
//        }
        
        LinkedList<String> breadthTraversalQuestion = BinaryTreeQuestion.breadthTraversal();
        
//        
        Util.chargeAnimals(BinaryTreeQuestion,createMapSheets);
        
        LinkedList<String> breadthTraversal = BinaryTreeQuestion.breadthTraversal();
        
//        
//       for(String s : breadthTraversalQuestion){
//            System.out.println(s);
//        }
        for(String s : breadthTraversal){
            System.out.println(s);
        }
        
       
    }

}