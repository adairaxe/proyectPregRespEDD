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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import static model.Util.isAnswerValid;

/**
 *
 * @author USER
 */
public class Adivinador {
    
    private BinaryTree<String> treeOfGame;
    private List<String> listOfQuestions;
    private Map<String , ArrayList<String>> mapOfAnswers;

    public Adivinador() {
    }   
    
    public BinaryTree<String> getTreeOfGame() {
        return treeOfGame;
    }  
    
    private void setTreeOfGame(BinaryTree<String> treeOfGame) {
        this.treeOfGame = treeOfGame;
    }

    public List<String> getListOfQuestions() {
        return listOfQuestions;
    }

    private void setListOfQuestions(List<String> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }

    public Map<String, ArrayList<String>> getMapOfAnswers() {
        return mapOfAnswers;
    }

    private void setMapOfAnswers(Map<String, ArrayList<String>> mapOfAnswers) {
        this.mapOfAnswers = mapOfAnswers;
    }
     
    
    public ArrayList<String> createListOfQuestion(String fileOfQuestions) throws IOException{
        try(    FileReader reader = new FileReader(fileOfQuestions);
                BufferedReader buff = new BufferedReader(reader);   )
        {
            ArrayList<String> linkedListQuestion = new ArrayList<String>();
            String question;
            while ((question = buff.readLine()) != null){
                linkedListQuestion.add(question);
            }
            
            return linkedListQuestion;
            
        } catch (FileNotFoundException ex) {
            return null;
        }
    }
    
    
    
    public Map<String , ArrayList<String>> createMapOfAnswer(String fileOfAnswers) throws IOException{
        try(    FileReader reader = new FileReader(fileOfAnswers);
                BufferedReader buff = new BufferedReader(reader);   )
        {
            Map<String, ArrayList<String>> mapAnswers = new LinkedHashMap();
            String answer;
            
            while ((answer = buff.readLine()) != null){
                String[] splitAnswers = answer.split(" ");
                ArrayList<String> arrayListAnswers = new ArrayList();
                
                for (int i = 1; i < splitAnswers.length; i++)
                        arrayListAnswers.add(splitAnswers[i]);
                
                mapAnswers.put(splitAnswers[0], arrayListAnswers);
            }
            return mapAnswers;
            
        } catch (FileNotFoundException ex) {
            return null;
        }
        
    }
    
    
    private void swapAnswersInMap(Map<String , ArrayList<String>> newMappAnswers , Map<String , ArrayList<String>> oldMapAnswers, int index){
        Set<String> keySet = oldMapAnswers.keySet();
        Iterator<String> iterator = keySet.iterator();
            while(iterator.hasNext()){
                String next = iterator.next();
                ArrayList<String> ArrayNew = newMappAnswers.get(next);
                ArrayList<String> ArrayOld = oldMapAnswers.get(next);
                ArrayNew.add(ArrayOld.remove(index));
                newMappAnswers.replace(next, ArrayNew);
            } 
        
    }
    
    
    
    public void createListAleatoryOfQuestion(List<String> listOfQuestion , Map<String , ArrayList<String>> mapAnswers){
        List<String> newArraylistQuestions = new ArrayList();  
        Map<String , ArrayList<String>> mapAnswersRandom = new LinkedHashMap();
        mapAnswersRandom.putAll(mapAnswers);
        int numRandom;
        int sizeOrigin = listOfQuestion.size();
        for(int i = 0 ; i < sizeOrigin ; i++){
            while(!listOfQuestion.isEmpty()){
                numRandom = (int)(Math.random() * 10);
                if(numRandom < listOfQuestion.size()){
                    newArraylistQuestions.add(listOfQuestion.get(numRandom));
                    listOfQuestion.remove(numRandom);
                    
                    this.swapAnswersInMap(mapAnswersRandom, mapAnswers, numRandom);  
                }
            }
        }
        this.setListOfQuestions(newArraylistQuestions);
        this.setMapOfAnswers(mapAnswersRandom);
    }
    
    
    
    public Stack<BinaryTree<String>> createBinaryTreeQuestion(){
        Stack stack = new Stack();
        LinkedList<String> copyListOfQuestion = new LinkedList();
        copyListOfQuestion.addAll(listOfQuestions);
        while(!copyListOfQuestion.isEmpty()){
            BinaryTree<String> binaryTree = new BinaryTree(copyListOfQuestion.removeFirst());
            stack.push(binaryTree);
        }
        return stack;
    }
    
    
    
    public void createBinaryTreeRoot(Stack<BinaryTree<String>> stackTreeQuestions){        
        while(stackTreeQuestions.size() > 1){
            BinaryTree<String> treeUnder = stackTreeQuestions.pop();
            BinaryTree<String> treeUp = new BinaryTree(stackTreeQuestions.pop().getRootContent());
            treeUp.setLeft(treeUnder.newcopyBinaryTree());
            treeUp.setRight(treeUnder.newcopyBinaryTree());
            stackTreeQuestions.push(treeUp);
        }
        treeOfGame = stackTreeQuestions.pop(); 
    }
    
    
    
    private BinaryTree<String> chargeAnswer(BinaryTree<String> treeQuestion, String animal, ArrayList<String> answersOfMap){
        String remove = answersOfMap.remove(0);
        if(answersOfMap.isEmpty()){
            if(remove.equals("si")){
                if(treeQuestion.getLeft() != null )
                    animal = treeQuestion.getLeft() + " " + animal;
                treeQuestion.setLeft2(animal);
            } 
            else{
                if(treeQuestion.getRight() != null )
                    animal = treeQuestion.getRight() + " " + animal;
                treeQuestion.setRight2(animal);
            }   
            return treeQuestion;  
            
        }
        else{
            if(remove.equals("si"))
                chargeAnswer(treeQuestion.getLeft(), animal, answersOfMap);
            else
                chargeAnswer(treeQuestion.getRight(), animal, answersOfMap);
        }
        return null;
    }
    
    
    
    public void chargeAllAnswer(){
        Set<String> keySet = mapOfAnswers.keySet();
        Iterator<String> iterator = keySet.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            this.chargeAnswer(treeOfGame, next, mapOfAnswers.get(next));
        }
    }
    
    
    
    public int askNumQuestionsUser (){
        String preguntas;
        Scanner entradaEscaner = new Scanner(System.in);
        do {
            System.out.println("Puedes seleccionar hasta " + listOfQuestions.size() + " preguntas.");
            System.out.println("Escribe el número de pregunas que deseas relizar: ");
            preguntas = entradaEscaner.nextLine();
            boolean isNumeric =  preguntas.matches("[+-]?\\d*(\\.\\d+)?");
            while(!isNumeric){
                System.out.println("No es un numero, ingrese un valor correcto: ");
                preguntas = entradaEscaner.nextLine();
                isNumeric =  preguntas.matches("[+-]?\\d*(\\.\\d+)?");
            }
        } while ( (Integer.parseInt(preguntas)) > (listOfQuestions.size()) );
        
        return Integer.parseInt(preguntas); 
    }
    
    
    
    public int askNumQuestionsUser2(Scanner sc){
        int numPregUser = sc.nextInt();
        return numPregUser;
    }
    
    
    
    private BinaryTree<String> travelToN(BinaryTree<String> treeQuestion, String answerUser){
        if(answerUser.equals("si"))
            treeQuestion = treeQuestion.getLeft();
        else
            treeQuestion = treeQuestion.getRight();
        return treeQuestion;
    }
    
    
    
    private static String ingresarRespuesta (){  
        String respuesta;
        Scanner entradaEscaner = new Scanner (System.in); 
        respuesta = entradaEscaner.nextLine();
        return respuesta;
    }
    
    
    public LinkedList<String> getAnimals(){
        LinkedList<String> listAnimals = new LinkedList();
        int numQuestionUser = this.askNumQuestionsUser();
        
        while(numQuestionUser > 0){
            System.out.println(treeOfGame.getRootContent());
            String ingresarRespuesta = Adivinador.ingresarRespuesta();
            treeOfGame = this.travelToN(treeOfGame, ingresarRespuesta);
            numQuestionUser = numQuestionUser - 1;
        }
        
        if(!treeOfGame.isLeaf()){
            System.out.println(treeOfGame.breadthTraversal());
            LinkedList<String> sheets = treeOfGame.getTreeSheets();
            
            for(String s : sheets){
                if(!(s.contains("?")))
                    listAnimals.add(s);
            }
                
        }else{
            if(!(treeOfGame.getRootContent().contains("?")))
                listAnimals.add(treeOfGame.getRootContent());
            else{
                System.out.println("No conocemos a ese animal");
                return null;
            }
                
        }
        return listAnimals;
    }
}
