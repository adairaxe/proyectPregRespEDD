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
    
    
    private static void chargeAnswers(BinaryTree<String> treeQuestion, BinaryTree<String> animal, Queue<String> answers){
        String answer = answers.poll();
        if(answers.isEmpty()){
            if(answer.equals("si"))
                treeQuestion.setLeft(animal);
            else
                treeQuestion.setRight(animal);
        }
        else{
            if(answer.equals("si"))
                chargeAnswers(treeQuestion.getLeft(), animal, answers);
            else
                chargeAnswers(treeQuestion.getRight(), animal, answers);
        }
    }
    
    
    public static boolean isValidNumQuestion(int num, Stack<BinaryTree<String>> Questions) {
        return num <= Questions.size();
    }
    
    
    public static int askNumQuestionsUser (int numPreguntas)
    {
        System.out.println ("Puedes seleccionar hasta " + numPreguntas + ":");
        System.out.println ("Escribe el número de pregunas que deseas relizar: ");
        int preguntas;
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner

        preguntas = entradaEscaner.nextInt();
        return preguntas;
    }
    
    
    public static boolean chargeAnimals(BinaryTree<String> treeQuestion,Map<String, Queue<String>> animals){
        animals.forEach((k,v)->chargeAnswers(treeQuestion, new BinaryTree<String>(k), v));
        return true;
    }
    
}
