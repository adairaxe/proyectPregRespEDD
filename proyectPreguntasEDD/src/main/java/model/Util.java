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
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author USER
 */
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
            BinaryTree<String> treeUp = stackTreeQuestions.pop();
            treeUp.setLeft(treeUnder);
            treeUp.setRight(treeUnder);
            stackTreeQuestions.push(treeUp);
        }
        
        return stackTreeQuestions.pop(); 
    }
    
    
    public static String[] putSheets(BinaryTree<String> tree, String nameFileAnswers){
        
        try(    FileReader reader = new FileReader(nameFileAnswers);
                BufferedReader buff = new BufferedReader(reader);   )
        {   
           String answer;
           while((answer = buff.readLine()) != null){
               String[] split = answer.split(" ");
           }
           
        }   catch (Exception ex) {
            System.out.println(ex.getMessage());
            
        }
        return null;
    }
    
    
    public static Queue<String> saveAnswers(String[] array){
        
        Queue<String> queueAnswers = new LinkedList();
        for(int i=1 ; i < array.length ; i++)
            queueAnswers.add(array[i]);
        return queueAnswers;
    }
    
    
    public static void validateAndPut(BinaryTree<String> tree, Queue<String> queueAnswers, String animal){
        
        BinaryTree<String> tree2 = tree;
        while(!queueAnswers.isEmpty() && tree2 != null){
            String answer = queueAnswers.poll();
            if(answer.equals("si")){
                if(tree2.getLeft() == null){
                    tree2 = tree2.getLeft();
                }
            }else{
                if(tree2.getRight() == null)
                    tree2 = tree2.getRight();
            }
        }
        tree2.setRootContent(animal);
        
    }
}
