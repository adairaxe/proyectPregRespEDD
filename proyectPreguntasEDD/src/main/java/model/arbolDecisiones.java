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
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author User
 */
public class arbolDecisiones {
    
    
    public static void buildTreeDecisionxd(String nameFileQuestion){
        FileReader reader = null;
        BufferedReader buff=null;
        PriorityQueue<BinaryTree<nodoProyecto>> p = new PriorityQueue<> ( 
                (s1,s2)->{
                    return s2.getRootContent().getNivel() -s1.getRootContent().getNivel();
            
        });

        try {
            reader = new FileReader(nameFileQuestion);
            buff = new BufferedReader(reader);
            String question;
            int nivel = 1;
            while ((question = buff.readLine()) != null) {
                System.out.println(question );
                
                // p.add(new BinaryTree(new nodoProyecto(question,nivel)));
                nivel++;
            }
        } catch (IOException e) {
        } finally {

            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e2) {
            }


           
           Stack<BinaryTree<nodoProyecto>> s=new Stack<>();
           
           while(p.isEmpty()){
               s.add(p.poll());               
           }
           /*
           while(s.size() > 1){
            BinaryTree<nodoProyecto> nodoIzquierdo = s.pop();
            BinaryTree<nodoProyecto> nodoDerecho= nodoIzquierdo;
            BinaryTree<nodoProyecto> nodoPadre=s.pop();
            nodoPadre.setLeft(nodoIzquierdo);
            nodoPadre.setLeft(nodoDerecho);
         
        
            }
            */
            for(BinaryTree<nodoProyecto> sa : s){
                System.out.println(sa.getRootContent().getMetadata());
            }
            
           //return p.poll();
           
        }
    }
}
