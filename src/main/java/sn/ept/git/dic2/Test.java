/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Saly
 */
public class Test {
    
//    public static double getMoyenne(LinkedList<Double> liste){
//        Double sum = 0.0;
//        for(double i : liste){
//            sum+=i;
//        }
//        return sum/5;
//    }
    
     public static void main(String[] args) {
        /*LinkedList<Double> linkedList = new LinkedList<>();
        linkedList.add(1.0);
        linkedList.add(2.0);
        linkedList.add(3.0);
        linkedList.add(4.0);
        linkedList.add(5.0);*/

      /*  Double sum = calculateSum(linkedList);
        System.out.println("Somme des éléments de la LinkedList : " + sum);
        
         System.out.println(getMoyenne(linkedList));*/
         
         
      File dir  = new File("D:\\DIC2\\Modélisation Stochastique\\data");

      File[] liste = dir.listFiles();
      for(File item : liste){
        if(item.isFile())
        { 
          System.out.format("Nom du fichier: %s%n", item.getName()); 
        } 
        else if(item.isDirectory())
        {
          System.out.format("Nom du répertoir: %s%n", item.getName()); 
        } 
                
    }
     
     }}  

   /* public static Double calculateSum(LinkedList<Double> linkedList) {
        Double sum = 0.0;
         for (Double value : linkedList) {
             sum += value;
         }
        return sum;
    }*/

