/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import TDA.BinaryTree;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class Util {
    
    
    public static Stack<BinaryTree<String>> createStackQuestions(String nameFileQuestion){
        
        try(    FileReader reader = new FileReader(nameFileQuestion);
                BufferedReader buff = new BufferedReader(reader);   )
        {   
           Stack<BinaryTree<String>> stackTreeQuestions = new Stack<>(); 
           String question;
           while((question = buff.readLine()) != null){
               stackTreeQuestions.add(new BinaryTree(question));
           }          
           return stackTreeQuestions;
           
        }   catch (Exception ex) {
            System.out.println(ex.getMessage()); 
            return null;
        }   
    }
    
    
    public static BinaryTree<String> createBinaryTreeQuestion(Stack<BinaryTree<String>> stackTreeQuestions){
        
        while(stackTreeQuestions.size() > 1){
            BinaryTree<String> treeUnder = stackTreeQuestions.pop();
            BinaryTree<String> treeUp = new BinaryTree (stackTreeQuestions.pop().getRootContent());
            treeUp.setLeft(treeUnder.newcopyBinaryTree());
            treeUp.setRight(treeUnder.newcopyBinaryTree());
            /*treeUp.setLeft(new BinaryTree<String> (treeUnder.getRootContent()));
            treeUp.setRight(new BinaryTree<String> (treeUnder.getRootContent()));*/
            stackTreeQuestions.push(treeUp);
        }
        return stackTreeQuestions.pop(); 
    }
    
    
    public static Map<String, Queue<String>> createMapSheets(String nameFileAnswers){
        
        Map<String, Queue<String>> MapAnswers = new TreeMap();
        try(    FileReader reader = new FileReader(nameFileAnswers);
                BufferedReader buff = new BufferedReader(reader);   )
        {   
           String answer;
           while((answer = buff.readLine()) != null){
               
                String[] array = answer.split(" ");
                Queue<String> arrayAnswers = new LinkedList();
                for(int i=1 ; i < array.length ; i++)
                    arrayAnswers.add(array[i]);
                MapAnswers.put(array[0], arrayAnswers);
           }
        }   catch (Exception ex) {
            System.out.println(ex.getMessage());
            
        }
        return MapAnswers;
    }
    
    
    public static void chargeAnswers(BinaryTree<String> treeQuestion, BinaryTree<String> animal, Queue<String> answers){
        
        String answer = answers.poll();
        if(answers.isEmpty() && isAnswerValid(answer)){                
            if(answer.equals("si"))
            treeQuestion.setLeft(animal);
            else
                treeQuestion.setRight(animal);   
        }        
        
        else if (!answers.isEmpty() && isAnswerValid(answer)) {
            if(answer.equals("si"))
                chargeAnswers(treeQuestion.getLeft(), animal, answers);
            else if (answer.equals("no"))
                chargeAnswers(treeQuestion.getRight(), animal, answers);
        }
        
        else 
            System.out.println("Revisar su archivo de respuestas");
    }
    
    
    public static boolean isAnswerValid(String answer){
        
        return answer.equals("si") || answer.equals("no");
    }
    
    
    public static void chargeAnimals(BinaryTree<String> treeQuestion,Map<String, Queue<String>> animals){
        
        animals.forEach((k,v)->chargeAnswers(treeQuestion,new BinaryTree<String>(k),v));
    }
    
    
    public static boolean isValidNumQuestion(int num, Stack<BinaryTree<String>> Questions) {
        
        return num <= Questions.size();
    }
    
    
    public static int askNumQuestionsUser (int numPreguntas)
    {
        System.out.println ("Puedes seleccionar hasta " + numPreguntas + ":");
        System.out.println ("Escribe el número de pregunas que deseas relizar: ");
        int preguntas;
        Scanner entradaEscaner = new Scanner (System.in);   
        preguntas = entradaEscaner.nextInt();
        return preguntas;
    }
    
    
    public static void printSheetAnimal(BinaryTree<String> treeQuestion){
       
        if (treeQuestion== null)
            System.out.println("¡Lo sentimos!, todavía no conocemos a ese animal.");
        else
            System.out.println("Tu animal es: " + treeQuestion.getRootContent());  
        
    }
    
    
    public static void printAnimalsTree (){
    }
    
    
    public static String ingresarRespuesta (String pregunta){
        
        String respuesta;
        System.out.println (pregunta + ": " );
        Scanner entradaEscaner = new Scanner (System.in); 
        
        respuesta = entradaEscaner.nextLine();
        return respuesta;
    }
    
    
    public static void playGame (BinaryTree<String> treeQuestion){
        
        System.out.println ("Bienvenido, ¡vamos a adivinar el animal que piensas! ");
        int numQuestions = askNumQuestionsUser(treeQuestion.randomCountTreeComplete());
        System.out.println ("Has seleccionado " + numQuestions + " preguntas");
        int cont = 1;
        String respuesta;
        BinaryTree <String> treeTemp = new BinaryTree<>();
        treeTemp = treeQuestion;
        while (numQuestions > 0){
            respuesta = ingresarRespuesta(cont + treeTemp.getRootContent());
            cont++;
            numQuestions--;
            if (isAnswerValid(respuesta)){
                
                if (respuesta.equals("si"))
                    treeTemp = treeTemp.getLeft();
                else
                    treeTemp = treeTemp.getRight();
            }else{
                System.out.println ("Ingresa una respuesta válida, minúscula sin espacios, gracias");
            }
        }
        if (numQuestions == 0)
            printSheetAnimal(treeTemp);
        else
            printSheetAnimal(treeTemp);
    }
    public static String[] separarCadena (String cadena){
        return cadena.split(" ");
    }
}
