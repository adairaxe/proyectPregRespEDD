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

/**
 *
 * @author USER
 */
public class Util {
    public BinaryTree crearArbolPreguntas(String nameFileQuestion){
        BinaryTree treeQuestions = new BinaryTree();
        try(
                FileReader reader = new FileReader(nameFileQuestion);
                BufferedReader buff = new BufferedReader(reader);
            )
        {   
           String pregunta;
           while((pregunta = buff.readLine()) != null){
               treeQuestions.setRootContent(pregunta);
           }
            
        
        }   catch (Exception ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return null;
    }
}
