/**
 * File: CalculationImpl.java
 * Version: 0.1
 * Revision: $log$
 * Date: 25/04/2014
 * 
 * GBST Java Coding Problem: solution about Calculating Pi by 
 * using multiple threading
 */
package com.calculation.beans.impl;

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
   private int iFromIndex = 0;

   /**
    * 
    */
   private int iToIndex = 0;

   /**
    * SharedQueue using for store values after calculations
    */
   private final LinkedBlockingQueue<Double> sharedQueue;

   /**
    * Constructor set instance value for instance variables
    * 
    * @param iFromIndex start position to calculate
    * @param iToIndex end position to calculate
    * @param sharedQueue put items after calculate on each part
    */
   public ProducerImpl(int iFromIndex, int iToIndex, LinkedBlockingQueue<Double> sharedQueue) {
      this.sharedQueue = sharedQueue;
      this.iFromIndex = iFromIndex;
      this.iToIndex = iToIndex;
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
      for (int i = this.iFromIndex; i <= this.iToIndex; i++) {
         if (i % 2 == 0) {
            sum += 1.0 / (2.0 * i + 1);
         } else {
            sum -= 1.0 / (2.0 * i + 1);
         }
      }
      return sum;
   }
}
