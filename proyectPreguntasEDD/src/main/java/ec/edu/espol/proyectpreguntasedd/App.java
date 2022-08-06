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
        for(String animal : createMapSheets.keySet()){
            System.out.println(animal + " : " + createMapSheets.get(animal));
        }
        
        LinkedList<String> breadthTraversalLeon = BinaryTreeQuestion.breadthTraversal();
        Queue<String> answerOso = createMapSheets.get("oso");
        System.out.println(Util.chargeAnswers(BinaryTreeQuestion, new BinaryTree("oso"), answerOso));
        BinaryTreeQuestion.getLeft().getLeft().setRight(new BinaryTree("animal"));
        LinkedList<String> breadthTraversal = BinaryTreeQuestion.breadthTraversal();
        
        Queue<String> answerLeon = createMapSheets.get("avestruz");
        System.out.println(Util.chargeAnswers(BinaryTreeQuestion, new BinaryTree("avestruz"), answerLeon));
        LinkedList<String> preorder = BinaryTreeQuestion.breadthTraversal();
        
        for(String s : breadthTraversal){
            System.out.println(s);
        }
       for(String s : breadthTraversalLeon){
            System.out.println(s);
        }
        for(String s : preorder){
            System.out.println(s);
        }
        
       
    }

}