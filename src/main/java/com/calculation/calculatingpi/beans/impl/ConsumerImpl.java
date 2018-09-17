/**
 * File: ConsumerImpl.java
 * Version: 0.1
 * Revision: $log$
 * Date: 25/04/2014
 * 
 * GBST Java Coding Problem: solution about Calculating Pi by 
 * using multiple threading
 */
package com.calculation.calculatingpi.beans.impl;

import java.text.DecimalFormat;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p>
 * Provide consumer which get value from sharedQueue, sum all, providing the method getResult
 * return result
 * </p>
 * 
 * @author TUNGUYEN
 */
public class ConsumerImpl implements Runnable {

   /**
    * Consumer get value from sharedQueue.
    */
   private final LinkedBlockingQueue<Double> sharedQueue;

   /**
    * Pi using for sum all value and return the result after calculation
    */
   private Double pi;

   /**
    * The maximum items which getting from sharedQueue
    */
   private final int MAX_SIZE;

   /**
    * Constructor to set instance value for instance variables.
    * 
    * @param sharedQueue put items after calculation on each part
    * @param MAX_SIZE the number of threads which we execute producer
    */
   public ConsumerImpl(LinkedBlockingQueue<Double> sharedQueue, int MAX_SIZE) {

      this.sharedQueue = sharedQueue;

      this.MAX_SIZE = MAX_SIZE;

      this.pi = 0.0;
   }

   /**
    * @return Pi after calculation
    * @throws InterruptedException
    */
   public double getResult() throws InterruptedException {
    //Set format for the result after calculation
      DecimalFormat df = new DecimalFormat("#.############");
      return Double.valueOf(df.format(pi * 4.0));
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Runnable#run()
    */
   public void run() {
      int size = 0;
      while (size < MAX_SIZE) {
         try {
            if (!this.sharedQueue.isEmpty()) {
               pi += this.sharedQueue.take();
               size ++;
            }
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
}
