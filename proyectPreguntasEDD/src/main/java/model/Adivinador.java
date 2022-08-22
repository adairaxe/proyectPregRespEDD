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
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author USER
 */
public class Adivinador {
    
    private BinaryTree<String> treeOfGame;
    private List<String> listOfQuestions;
    private Map<String , ArrayList<String>> mapOfAnswers;

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
    
    
    
    public void createListAleatoryOfQuestion(List<String> listOfQuestion , Map<String , ArrayList<String>> mapAnswers){
        int sizeOrigin = listOfQuestion.size();
        
        List<String> newArraylistQuestions = new ArrayList();
        Map<String , ArrayList<String>> mapAnswersRandom = new LinkedHashMap();
        mapAnswersRandom.putAll(mapAnswers);
        
        int numRandom;
        Set<String> keySet = mapAnswers.keySet();
        for(int i = 0 ; i < sizeOrigin ; i++){
            while(!listOfQuestion.isEmpty()){
                numRandom = (int)(Math.random() * 10);
                if(numRandom < listOfQuestion.size()){
                    newArraylistQuestions.add(listOfQuestion.get(numRandom));
                    listOfQuestion.remove(numRandom);
                          
                    Iterator<String> iterator = keySet.iterator();
                    while(iterator.hasNext()){
                        String next = iterator.next();
                        ArrayList<String> ArrayNew = mapAnswersRandom.get(next);
                        ArrayList<String> ArrayOld = mapAnswers.get(next);
                        ArrayNew.add(ArrayOld.remove(numRandom));
                        mapAnswersRandom.replace(next, ArrayNew);
                    }   
                }
            }
        }
        this.setListOfQuestions(newArraylistQuestions);
        this.setMapOfAnswers(mapAnswersRandom);
    }

    
}
