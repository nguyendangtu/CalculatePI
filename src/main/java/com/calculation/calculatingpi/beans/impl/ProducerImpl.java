/**
 * File: CalculationImpl.java
 * Version: 0.1
 * Revision: $log$
 * Date: 25/04/2014
 * 
 * GBST Java Coding Problem: solution about Calculating Pi by 
 * using multiple threading
 */
package com.calculation.calculatingpi.beans.impl;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * Provide producer which calculate and set value to sharedQueue
 * </p>
 * 
 * @author TUNGUYEN
 */
public class ProducerImpl implements Runnable {

   /**
    * Log4j: logger using for logging necessary information.
    */
   private static final Log logger = LogFactory.getLog(CalculationImpl.class);

   /**
    * 
    */
   private int fromIndex = 0;

   /**
    * 
    */
   private int toIndex = 0;

   /**
    * SharedQueue using for store values after calculations
    */
   private final LinkedBlockingQueue<Double> sharedQueue;

   /**
    * Constructor set instance value for instance variables
    * 
    * @param fromIndex start position to calculate
    * @param toIndex end position to calculate
    * @param sharedQueue put items after calculate on each part
    */
   public ProducerImpl(int fromIndex, int toIndex, LinkedBlockingQueue<Double> sharedQueue) {
      this.sharedQueue = sharedQueue;
      this.fromIndex = fromIndex;
      this.toIndex = toIndex;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Runnable#run()
    */
   public void run() {
      try {
         this.sharedQueue.put(this.calculatePi());
      } catch (InterruptedException e) {
         e.printStackTrace();
         logger.error(e.toString());
      }
   }

   /**
    * @return the result after calculation
    */
   public double calculatePi() {
      double sum = 0.0;
      for (int i = this.fromIndex; i <= this.toIndex; i++) {
         if (i % 2 == 0) {
            sum += 1.0 / (2.0 * i + 1);
         } else {
            sum -= 1.0 / (2.0 * i + 1);
         }
      }
      return sum;
   }
}
