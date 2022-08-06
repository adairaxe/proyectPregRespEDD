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
    
    
    public static boolean chargeAnswers(BinaryTree<String> treeQuestion, BinaryTree<String> animal, Queue<String> answers){
        System.out.println(answers.size());
        String answer = answers.poll();
        //BinaryTree<String> 
        if(treeQuestion.isLeaf()){
            System.out.println("ES hoja");
            System.out.println(answer);
            if(answer.equals("si")){
                System.out.println("Izquierdo");
                treeQuestion.setLeft(animal);
            }
            else if (answer.equals("no")){
                System.out.println("derecho");
                treeQuestion.setRight(animal);
            }
            return true;
        }
        else if (!treeQuestion.isLeaf()) {
            System.out.println("No es hoja");
            System.out.println(answer);
            if(answer.equals("si"))
                return chargeAnswers(treeQuestion.getLeft(), animal, answers);
            else if (answer.equals("no"))
                return chargeAnswers(treeQuestion.getRight(), animal, answers);
            //return false;
        }
        return false;
    }
    
    
    public static boolean isValidNumQuestion(int num, Stack<BinaryTree<String>> Questions) {
        return num <= Questions.size();

    }
}
