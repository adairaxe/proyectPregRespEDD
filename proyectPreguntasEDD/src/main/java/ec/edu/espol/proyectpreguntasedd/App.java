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
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import model.Adivinador;
import model.Util;
import static model.Util.askNumQuestionsUser;
import static model.Util.chargeAnimals;
import static model.Util.createMapSheets;
import static model.Util.playGame;


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

    public static void main(String[] args) throws IOException {
//        launch();

//        int preguntasPosibles = Util.calculateMaxQuestions("preguntas.txt"); 
//        
//        int numQuestions = askNumQuestionsUser((preguntasPosibles));
//        
//        se crea un stack con las n preguntas
//        LinkedList<Integer> pr = Util.randomQuestion("preguntas.txt", numQuestions);
//        
//        Stack<BinaryTree<String>> createStackQuestions = Util.createStackQuestions("preguntas.txt", numQuestions, pr);
//        System.out.println(createStackQuestions);
//
//        se crea el arbol de preguntas
//        BinaryTree<String> BinaryTreeQuestion = Util.createBinaryTreeQuestion(createStackQuestions);
//        LinkedList<String> breadthTraversal = BinaryTreeQuestion.breadthTraversal();
//        for(String s : breadthTraversal){
//            System.out.println(s);
//        }
//
//        se crea un mapa con las respuestas
//        
//        Map<String, Queue<String>> createMapSheets = Util.createMapSheets("respuestas.txt", numQuestions, pr);
//        System.out.println(createMapSheets);
//        LinkedList<String> breadthTraversalQuestion = BinaryTreeQuestion.breadthTraversal();
//
//        //se carga las respuestas a las preguntas 
//        Util.chargeAnimals(BinaryTreeQuestion, createMapSheets);
//        
//        playGame (BinaryTreeQuestion,numQuestions);

        Adivinador adivinador = new Adivinador();
        List<String> createListOfQuestion = adivinador.createListOfQuestion("preguntas.txt");
        Map<String, ArrayList<String>> createMapOfAnswer = adivinador.createMapOfAnswer("respuestas.txt");
        adivinador.createListAleatoryOfQuestion(createListOfQuestion, createMapOfAnswer);
        
        Stack<BinaryTree<String>> createBinaryTreeQuestion = adivinador.createBinaryTreeQuestion();
        adivinador.createBinaryTreeRoot(createBinaryTreeQuestion);
        
        adivinador.chargeAllAnswer();
        
        LinkedList<String> breadthTraversal = adivinador.getTreeOfGame().breadthTraversal();
        for(String s : breadthTraversal){
            System.out.println(s);
        }
        
        adivinador.askNumQuestionsUser();
        
        

        
    }

}