/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import TDA.BinaryTree;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class Util {
    
   
    public static int preguntasPosibles(String nameFileQuestion){
        try ( 
            FileReader reader = new FileReader(nameFileQuestion);
            BufferedReader buff = new BufferedReader(reader);) {
            String question;
            int contador=0;
            while ((question = buff.readLine()) != null) {
                contador++;
            }
            return contador;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public static Stack<BinaryTree<String>> createStackQuestions(String nameFileQuestion, int countQuestions,LinkedList<Integer> random){
        try(    FileReader reader = new FileReader(nameFileQuestion);
                BufferedReader buff = new BufferedReader(reader);   )
        {   
            Stack<BinaryTree<String>> stackTreeQuestions = new Stack<>();
            String question;
            int num = 0;
            if (random == null) {
                System.out.println("normal");
                while ((question = buff.readLine()) != null && num < countQuestions) {
                    stackTreeQuestions.add(new BinaryTree(question));
                    num++;
                }
            } else {
                System.out.println("random");
                LinkedList<Integer> l = new LinkedList<>(random);
                while ((question = buff.readLine()) != null) {
                    if (l.contains(num)) {
                        stackTreeQuestions.add(new BinaryTree(question));
                    }
                    num++;
                }
            }
            System.out.println(stackTreeQuestions);
            return stackTreeQuestions;

        }   catch (Exception ex) {
            System.out.println(ex.getMessage()); 
            return null;
        }   
    }
  
    public static LinkedList<Integer> randomQuestion(String nameFileQuestion, int numPreguntas) {
        LinkedList<Integer> random = new LinkedList<>();
        int contador = 0;
        int numero = 0;
        int numPreguntasT = preguntasPosibles(nameFileQuestion);
        while (contador < numPreguntas) {
            numero = (int) (Math.random() * numPreguntasT);
            if (!random.contains(numero)) {
                random.add(numero);
                contador++;
            }
        }
        random.sort((s1, s2) -> {
            return s1 - s2;
        });
        return random;

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
    
    
    public static Map<String, Queue<String>> createMapSheets(String nameFileAnswers, int countAnswer,LinkedList<Integer> random ){
        
        Map<String, Queue<String>> MapAnswers = new TreeMap();
        try(    FileReader reader = new FileReader(nameFileAnswers);
                BufferedReader buff = new BufferedReader(reader);   )
        {   
           String answer;
           int count = 0;
            while ((answer = buff.readLine()) != null && count < countAnswer) {
                String[] array = answer.split(" ");
                Queue<String> arrayAnswers = new LinkedList();

                if (random==null) {
                    for (int i = 1; i < countAnswer + 1; i++) {
                        arrayAnswers.add(array[i]);
                    }
                }else{
                    for(Integer i : random){
                        arrayAnswers.add(array[i+1]);
                    }
                    
                }
                System.out.println(arrayAnswers);
                MapAnswers.put(array[0], arrayAnswers);

                count++;
            }
        }   catch (Exception ex) {
            System.out.println(ex.getMessage());
            
        }
        return MapAnswers;
    }
    
    
    public static void chargeAnswers(BinaryTree<String> treeQuestion, BinaryTree<String> animal, Queue<String> answers){
        String answer = answers.poll();
        
        if (answers.isEmpty() && isAnswerValid(answer)) {
            if (answer.equals("si")) {
                treeQuestion.setLeft(animal);
              
            } else {
                treeQuestion.setRight(animal);
           
            }
        } else if (!answers.isEmpty() && isAnswerValid(answer)) {
            if(answer.equals("si")){
               
                chargeAnswers(treeQuestion.getLeft(), animal, answers);
            }
            else if (answer.equals("no")){
               
                chargeAnswers(treeQuestion.getRight(), animal, answers);
            }
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
    
    public static boolean isQuestion(String pregunta , Stack<BinaryTree<String>> s){
         while(!s.isEmpty()){
             if(s.pop().getRootContent().equals(pregunta)){
                 return true;
             }
         }
         return false;
    }
    
    public static LinkedList<BinaryTree<String>> NodeAnswers(BinaryTree<String> BinaryTreeQuestion,Stack<BinaryTree<String>> s){
        LinkedList<BinaryTree<String>> childrenNodes=new LinkedList<>();
        LinkedList<BinaryTree<String>> nodesTree=BinaryTreeQuestion.preOrderTraversalNodesR();
        for(BinaryTree<String> node : nodesTree){
            if(!isQuestion(node.getRootContent(),s)){
                childrenNodes.add(node);
            }
        }
        return childrenNodes;
    }
    
    
    
    
    public static int askNumQuestionsUser (String numPreguntas)
    {

        String preguntas;
        Scanner entradaEscaner = new Scanner(System.in);
        do {
            System.out.println("Puedes seleccionar hasta " + numPreguntas + " preguntas.");
            System.out.println("Escribe el número de pregunas que deseas relizar: ");
            preguntas = entradaEscaner.nextLine();
            boolean isNumeric =  preguntas.matches("[+-]?\\d*(\\.\\d+)?");
            while(!isNumeric){
                System.out.println("No es un numero, ingrese un valor correcto: ");
                preguntas = entradaEscaner.nextLine();
                isNumeric =  preguntas.matches("[+-]?\\d*(\\.\\d+)?");
            }
        } while ( (Integer.parseInt(preguntas)) >Integer.parseInt(numPreguntas) );
        
        return Integer.parseInt(preguntas);

    }
    
    
    public static void printSheetAnimal(BinaryTree<String> treeQuestion){
       
        if (treeQuestion== null ||treeQuestion.getRootContent().equals("null"))
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
    
    
    public static void playGame (BinaryTree<String> treeQuestion, int Npreguntas){
        
        System.out.println ("Bienvenido, ¡vamos a adivinar el animal que piensas! ");
        System.out.println ("Has seleccionado " + Npreguntas + " preguntas");
        int cont = 1;
        String respuesta;
        BinaryTree <String> treeTemp = new BinaryTree<>();
        treeTemp = treeQuestion;
        while (Npreguntas > 0) {
            respuesta = ingresarRespuesta(cont + treeTemp.getRootContent()).toLowerCase();

            if (isAnswerValid(respuesta)) {
                Npreguntas--;
                cont++;
                if (respuesta.equals("si")) {
                    treeTemp = treeTemp.getLeft();
                } else {
                    treeTemp = treeTemp.getRight();
                }
            } else {

                System.out.println("Ingresa una respuesta válida, sin espacios, gracias");

            }
        }
        if (Npreguntas == 0)
            printSheetAnimal(treeTemp);
        else
            printSheetAnimal(treeTemp);
    }
    public static String[] separarCadena (String cadena){
        return cadena.split(" ");
    }
}
