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
            BinaryTree<String> treeUp = stackTreeQuestions.pop();
            treeUp.setLeft(treeUnder);
            treeUp.setRight(treeUnder);
            stackTreeQuestions.push(treeUp);
        }
        return stackTreeQuestions.pop(); 
    }
    
    public static Map<String, ArrayList<String>> createMapSheets(String nameFileAnswers){
        
        Map<String, ArrayList<String>> MapAnswers = new TreeMap();
        try(    FileReader reader = new FileReader(nameFileAnswers);
                BufferedReader buff = new BufferedReader(reader);   )
        {   
           String answer;
           while((answer = buff.readLine()) != null){
               
                String[] array = answer.split(" ");
                ArrayList<String> arrayAnswers = new ArrayList();
                for(int i=1 ; i < array.length ; i++)
                    arrayAnswers.add(array[i]);
                MapAnswers.put(array[0], arrayAnswers);
           }
        }   catch (Exception ex) {
            System.out.println(ex.getMessage());
            
        }
        return MapAnswers;
    }
    
    public static boolean insertOneSheet(BinaryTree<String> tree, ArrayList<String> arrayAnswers, String animal){
        
        System.out.println(arrayAnswers);
        while (!arrayAnswers.isEmpty()){
        if(arrayAnswers.get(0).equals("si")){
            if(tree.getLeft() == null){
                BinaryTree<String> animalTree = new BinaryTree();
                animalTree.setRootContent(animal);
                tree.setLeft(animalTree);
                return true;
            }else{
                if(arrayAnswers.isEmpty())
                    System.out.println("Un animal ya existe");
                else{
                    arrayAnswers.remove(0);
                    insertOneSheet(tree.getLeft(), arrayAnswers, animal);
                }
                return false;
            }
        }else if(arrayAnswers.get(0).equals("no")){{
            if(tree.getRight() == null){
                BinaryTree<String> animalTree = new BinaryTree();
                animalTree.setRootContent(animal);
                tree.setRight(animalTree);
                return true;
            }else{
                if(arrayAnswers.isEmpty()){
                    System.out.println("Un animal ya existe");
                }else{
                    arrayAnswers.remove(0);
                    insertOneSheet(tree.getRight(), arrayAnswers, animal);
                }          
                return false;
            }
        }
    }
            return true;
        }
}
