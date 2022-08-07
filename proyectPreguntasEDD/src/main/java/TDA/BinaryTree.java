package TDA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.
        PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BinaryTree<E> {

    private BinaryTreeNode<E> root;

    public BinaryTree(E rootContent) {
        this.root = new BinaryTreeNode<>(rootContent);
    }

    public E getRootContent() {
        return this.root.getContent();
    }

    public BinaryTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void setRootContent(E content) {
        this.root = new BinaryTreeNode<>(content);
    }

    private BinaryTreeNode<E> getRoot() {
        return root;
    }

    private void setRoot(BinaryTreeNode<E> root) {
        this.root = root;
    }

    public void setLeft(BinaryTree tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTree tree) {
        this.root.setRight(tree);
    }

    public BinaryTree<E> getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree<E> getRight() {
        return this.root.getRight();
    }

    public String toString() {
        return "" + root;
    }

    public LinkedList<E> preOrderTraversalRecursive() {
        LinkedList<E> traversal = new LinkedList<>();
        if (!this.isEmpty()) {
            traversal.add(this.getRootContent());
        }
        if (this.getLeft() != null) {
            traversal.addAll(this.getLeft().preOrderTraversalRecursive());
        }
        if (this.getRight() != null) {
            traversal.addAll(this.getRight().preOrderTraversalRecursive());
        }
        return traversal;
    }
    
    public LinkedList<E> inOrderTraversalRecursive() {
        LinkedList<E> traversal = new LinkedList<>();
        if (this.getLeft() != null) {
            traversal.addAll(this.getLeft().inOrderTraversalRecursive());
        }
        if (!this.isEmpty()) {
            traversal.add(this.getRootContent());
        }     
        if (this.getRight() != null) {
            traversal.addAll(this.getRight().inOrderTraversalRecursive());
        }
        return traversal;
    }

    public LinkedList<E> postOrderTraversalRecursive() {
        LinkedList<E> traversal = new LinkedList<>();
        if (this.getLeft() != null) {
            traversal.addAll(this.getLeft().postOrderTraversalRecursive());
        } 
        if (this.getRight() != null) {
            traversal.addAll(this.getRight().postOrderTraversalRecursive());
        }
        if (!this.isEmpty()) {
            traversal.add(this.getRootContent());
        }  
        return traversal;
    }
    
    
    public LinkedList<E> preOrderTraversalIterative() {
        LinkedList<E> traversal = new LinkedList<>();
        Stack<BinaryTree<E>> s = new Stack<>();
        s.push(this);
        
        while (!s.isEmpty()) {    
            BinaryTree<E> tree = s.pop();
            if (!tree.isEmpty()) {
                traversal.add(tree.getRootContent());
            }
            if (tree.getRight()!= null && !tree.getRight().isEmpty()) {
                s.push(tree.getRight());
            }
            if (tree.getLeft() != null && !tree.getLeft().isEmpty()) {
                s.push(tree.getLeft());
            }
        }
        return traversal;
    }
    
//    public LinkedList<E> postOrderTraversalIterative() {
//        LinkedList<E> traversal = new LinkedList<>();
//        Stack<BinaryTree<E>> s = new Stack<>();
//        s.push(this);
//        BinaryTree<E> tree = s.pop();
//        while (!tree.isEmpty()) {    
//            if (tree.getRight()!= null && !tree.getRight().isEmpty()) {
//                s.push(tree.getRight());
//            }
//            if (tree.getLeft() != null && !tree.getLeft().isEmpty()) {
//                s.push(tree.getLeft());
//            }
//            tree = s.pop();
//        }
//        return traversal;
//    }
    
    public LinkedList<E> breadthTraversal() {
        LinkedList<E> traversal = new LinkedList<>();
        Queue<BinaryTree<E>> q = new LinkedList<>();
        q.offer(this);
        
        while (!q.isEmpty()) {
            BinaryTree<E> tree = q.poll();
            if (!tree.isEmpty()) 
                traversal.add(tree.getRootContent());
            
            if (tree.getLeft() != null && !tree.getLeft().isEmpty()) 
                q.offer(tree.getLeft());
            
            if (tree.getRight()!= null && !tree.getRight().isEmpty()) 
                q.offer(tree.getRight());
            
        }
        return traversal;
    }
    
    public boolean buscarElemento(E dato){
        if (this.isEmpty()){
            System.out.println(this.getRootContent());
            return false; 
            
        }else{
            if(this.getRootContent() == dato)
                return true;
            
            if(this.getLeft() != null)
                return this.getLeft().buscarElemento(dato); 
            
            if(this.getRight() != null) 
                return this.getRight().buscarElemento(dato);
            
            return false;
        } 
    }
    
    public E deleteTree(){
        E elementoEliminado = this.getRootContent();
        this.setRootContent(null);
        this.setLeft(null);
        this.setRight(null);
        return elementoEliminado;
    }
    
    public BinaryTree findPredecessorIzquierdo(){
        if(this.getLeft() == null)
            return this;
        else
            return this.getLeft().findPredecessorIzquierdo();   
    }
    
    public BinaryTree findPredecessorDerecho(){
        if(this.getRight() == null)
            return this;
        else
            return this.getRight().findPredecessorIzquierdo();   
    }
    
    public LinkedList<E> esBalanceado(){
        LinkedList<E> contDesbalanceados = new LinkedList<>();
        Stack<BinaryTree<E>> pila = new Stack<>();
        pila.push(this);
        while (!pila.isEmpty()) {
            BinaryTree<E> tree = pila.pop();
            if (tree.isEmpty()) {
                return null;
            }
            if (tree.getLeft() != null && tree.getRight() != null) {               
                pila.push(tree.getRight());
                pila.push(tree.getLeft());
            }     
            if (tree.getRight() != null && tree.getLeft() == null || tree.getRight() == null && tree.getLeft() != null) {
                contDesbalanceados.add(tree.getRootContent());
            }
        }
        if(!contDesbalanceados.isEmpty())
            System.out.println("Arbol desbalanceado");
        else
            System.out.println("Arbol balanceado");
        return contDesbalanceados;
    }
    
    public static BinaryTree arbolDeExpresiones(String expresion){
        String[] split = expresion.split(" ");
        Stack<BinaryTree> pilaArboles = new Stack<>();
        for(int i = 0 ; i < split.length ; i++){
            String c = split[i];
            if(!c.equals("+") && !c.equals("-") && !c.equals("/") && !c.equals("*")){
                BinaryTree<String> arbolNum = new BinaryTree(c);
                pilaArboles.push(arbolNum);
            }else{
                BinaryTree<String> arbolSignos = new BinaryTree(c);
                arbolSignos.setRight(pilaArboles.pop());
                arbolSignos.setLeft(pilaArboles.pop());
                pilaArboles.push(arbolSignos);
            }     
        }
        return pilaArboles.pop();
    }
    
    public static int sumArreglos(int[] arreglo){
        int total = 0;
        for(int i = 0 ; i < arreglo.length ; i++)
            total = total + arreglo[i];
        return total;
    }
    
//    public static BinaryTree arrayTree(int[] arreglo){
//        if(arreglo.length == 1)
//            return new BinaryTree(arreglo[0]);   
//        else{
//            if(sumArreglos(arreglo)/arreglo.length == 1)
//                return arrayTree(arreglo, new BinaryTree(1));
//            if(sumArreglos(arreglo)/arreglo.length == 0)
//                return arrayTree(arreglo, new BinaryTree(0));
//            else 
//                return arrayTree(arreglo, new BinaryTree(-1));
//        }
//    }
//    
//    
//    private static BinaryTree arrayTree(int[] arreglo, BinaryTree arbol){
//
//        
//        return null;    
//    }
    

    public static int resultArbolExpresion(BinaryTree<String> arbolExpresion){
        if(arbolExpresion.getLeft() == null && arbolExpresion.getRight() == null){
            return Integer.parseInt(arbolExpresion.getRootContent());
        }else{
            int numIzquierdo = resultArbolExpresion(arbolExpresion.getLeft());
            int numDerecho = resultArbolExpresion(arbolExpresion.getRight());
            
            if(arbolExpresion.getRootContent().equals("+"))
                return numIzquierdo + numDerecho;
            if(arbolExpresion.getRootContent().equals("-"))
                return numIzquierdo - numDerecho;     
            if(arbolExpresion.getRootContent().equals("*"))
                return numIzquierdo * numDerecho;
            if(arbolExpresion.getRootContent().equals("/"))
                return numIzquierdo / numDerecho;
        }
        return 0;
    }
    
    public static int getMin(BinaryTree<Integer> arbol){ 
        Stack<BinaryTree<Integer>> pila = new Stack<>();
        Integer element = arbol.getRootContent();
        pila.push(arbol);
        
        while(!pila.isEmpty()){
            BinaryTree<Integer> arbol1 = pila.pop();
            
            if(arbol1.isEmpty())
                return 0;
            
            else{
                if (arbol1.getRight() != null)                
                    pila.push(arbol1.getRight());
                if(arbol1.getLeft() != null)
                    pila.push(arbol1.getLeft());
                if(arbol1.getRootContent() < element)
                    element = arbol1.getRootContent();
            }
        }
        return element;
    }
    
    public int countLevels() {
        int numlevels = 1;
        Queue<BinaryTree<E>> q = new LinkedList<>();
        q.offer(this);
        
        while (!q.isEmpty()) {
            BinaryTree<E> tree = q.poll();
            if (tree.getLeft() != null && tree.getRight()!= null){
                q.offer(tree.getLeft());
                q.offer(tree.getRight());
                numlevels = numlevels + 1;
            }
            if (tree.getLeft()!= null && !tree.getLeft().isEmpty()){
                q.offer(tree.getLeft());
                numlevels = numlevels + 1;        
            }else if(tree.getRight()!= null && !tree.getRight().isEmpty()){
                q.offer(tree.getRight());
                numlevels = numlevels + 1;
            }
            
        }
        return numlevels;
    }
    public int randomCountTreeComplete (){
        //cómo es un árbol completo con las preguntas puedo conocer con el número de hojas
        // este método sólo sirve en árboles completos
        int numLevels;
        // 1 + 2 + 4 + 8 +16 + 32 hojas 
        // 1 + 2 + 3 + 4 + 5 niveles
        numLevels = log (this.numSheets()+1,2).intValue();
        return numLevels;
    }
    private static Double log(int num, int base) {
      return (Math.log10(num) / Math.log10(base));
   }
    public int numSheets (){
        int size;
        LinkedList<E> traversal = new LinkedList<>();
        if (!this.isEmpty()) {
            traversal.add(this.getRootContent());
        }
        if (this.getLeft() != null) {
            traversal.addAll(this.getLeft().preOrderTraversalRecursive());
        }
        if (this.getRight() != null) {
            traversal.addAll(this.getRight().preOrderTraversalRecursive());
        }
       
        return traversal.size();
    }
    
    public boolean isLeaf() {
        return (this.getLeft() == null && this.getRight() == null);
    }
    public  BinaryTree<E> newcopyBinaryTree (){
        BinaryTree<E> newTree = new BinaryTree<> ();
    
      //LinkedList<E> traversal = new LinkedList<>();
        if (!this.isEmpty()) {
            //traversal.add(this.getRootContent());
            newTree.setRootContent(this.getRootContent());
        }
        if (this.getLeft() != null) {
            newTree.setLeft(this.getLeft().newcopyBinaryTree());
        }
        if (this.getRight() != null) {
            newTree.setRight(this.getRight().newcopyBinaryTree());
        }
       return newTree;
    
    }
    public LinkedList<E> GetTreeSheets() {
            
        LinkedList<E> listSheet = new LinkedList<>();
        
        if (this.getLeft() != null) {
            this.getLeft().GetTreeSheets();
        } 
        if (this.getRight() != null) {
            this.getRight().GetTreeSheets();
        }
        if (!this.isEmpty() && this.getRight() == null && this.getLeft() == null ) {
            listSheet.add(this.getRootContent());
        }
        //listSheet.toString();
        return listSheet;
    }
    public void printSheets (){
        LinkedList<E> sheets = new LinkedList<>();
        sheets = this.GetTreeSheets();
        for (E e : sheets){
            System.out.println (e);
        }
        sheets.toString();
    }
    
}
