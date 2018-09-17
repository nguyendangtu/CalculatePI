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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calculation.beans.Calculation;
import com.calculation.beans.CalculationConfiguration;

/**
 * <p>
 * CalculationImpl using for calculating Pi with multiple threading. base on the
 * the input value. we have already 0...n, We mince from 0...n to many parts
 * 0...j-1, j...k-1, k...l-1,..., v...n-1, n...n. we calculating on each part,
 * after that sum all.
 * </p>
 * 
 * @author TUNGUYEN
 */
@Service(value = "calculation")
public class CalculationImpl implements Calculation {

   /**
    * Wiring calculation configuration to get configuration from the property
    * file.
    */
   @Autowired
   protected CalculationConfiguration calculationConfiguration;

   /**
    * <ul>
    * CalculatePi using for calculating Pi base on input number.
    * <li>- number is less than zero, then return -1.0.</li>
    * <li>- number is equal zero , then return 1.0</li>
    * <li>- number is more than zero, then return approximation to the constant
    * Pi.</li>
    * </ul>
    * 
    * @param inputNumber
    *           the input number value
    * @return Pi the Pi value after calculation
    * @throws InterruptedException
    *            exception when we get value from the queue
    * 
    */
   public double calculatePi(int inputNumber) throws InterruptedException {

      // Validation input value number
      if (inputNumber < 0) {
         throw new IllegalArgumentException("ERROR: input number incorrect, "
               + "please input another number in range [0, " + MAX_NUMBER + "]");
      } else if (inputNumber == 0) {
         return 4.0;
      }

      double pi = 0.0;
      // Calculate the number of threads
      int numberOfThread = calculateNumberOfThread(inputNumber);

      // sharedQueue using for put value after calculating of each thread.
      LinkedBlockingQueue<Double> sharedQueue = new LinkedBlockingQueue<Double>();

      // run threads to calculate Pi
      pi = this.runThreadsToCalculatePi(sharedQueue, inputNumber, numberOfThread);

      // clear all items in sharedQueue and resultQueue
      sharedQueue.clear();

      return pi;
   }

   /**
    * <p>
    * Calculating Pi by using producer/consumer pattern. Create multiple thread
    * to calculate on each part, and put the to sharedQueue. Create an consumer
    * thread to get value from producer and sum all items. Using thread pool to
    * handle problems in threads. After calculations complete, the result is
    * stored into resultQueue.
    * </p>
    * 
    * @param sharedQueue put value after calculation on each piece
    * @param inputNumber the input number value
    * @param numberOfThread the number of thread
    * @throws InterruptedException exception occur get value from the queue
    * 
    */
   private double runThreadsToCalculatePi(LinkedBlockingQueue<Double> sharedQueue, int inputNumber, int numberOfThread)
         throws InterruptedException {

      int size = inputNumber / numberOfThread;

      int maxSize = numberOfThread;

      // validation input number, if inputNumber % numberofThread != 0 then we
      // create one more thread
      // to handle the last part.
      if (inputNumber % numberOfThread != 0) {
         maxSize = maxSize + 1;
      }

      // Creates a thread pool that reuses a fixed max_size of threads operating
      // off a
      // shared unbounded queue
      ExecutorService executor = Executors.newFixedThreadPool(maxSize);

      for (int i = 0; i < numberOfThread; i++) {

         int iFromIndex = i * size;
         int iToIndex = (i + 1) * size - 1;
         // execute almost threads from 0 to i*size - 1, ...,
         // (numberofThread-1)*size...numberofThread*side-1
         executor.execute(new ProducerImpl(iFromIndex, iToIndex, sharedQueue));
      }

      int tmp = size * numberOfThread;

      // execute lasted thread from size*numberofThread to inputNumber
      executor.execute(new ProducerImpl(tmp, inputNumber, sharedQueue));

      // Declare consumer to sum all items into sharedQueue
      ConsumerImpl consumer = new ConsumerImpl(sharedQueue, maxSize);

      // Execute consumer thread to sum all items into sharedQueue.
      executor.execute(consumer);

      // shutdown Executor service
      executor.shutdown();
      try {
         executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      return consumer.getResult();
   }

   /**
    * Calculate the number of threads base on input number value and hardware
    * capability.
    * 
    * @param inputNumber
    * @return size
    * 
    * @author TUNGUYEN
    */
   private int calculateNumberOfThread(int inputNumber) {

      int maxNumberOfThread = Runtime.getRuntime().availableProcessors();

      int numberOfThread = inputNumber / calculationConfiguration.getDevidend();

      if (inputNumber <= calculationConfiguration.getDevidend()) {

         return 1;

      } else if (numberOfThread >= maxNumberOfThread) {

         numberOfThread = maxNumberOfThread;
      }

      return numberOfThread;
   }
}
