/**
 * File: Calculation.java
 * Version: 0.1
 * Revision: $log$
 * Date: 25/04/2014
 * 
 * GBST Java Coding Problem: solution about Calculating Pi by 
 * using multiple threading
 */
package com.calculation.calculatingpi.beans;

/**
 * <p>
 * Interface Calculation define all calculation function
 * </p>
 * 
 * @author TUNGUYEN
 */
public interface Calculation {

   /**
    * The maximum number which user can input
    */
   public static final int MAX_NUMBER = 1000000000;

   /**
    * <ul>
    * CalculatePi using for calculating Pi base on input number.
    * <li>- number is less than zero, then return -1.0.</li>
    * <li>- number is equal zero , then return 1.0</li>
    * <li>- number is more than zero, then return approximation to the constant
    * Pi (π).</li>
    * </ul>
    * 
    * @param inputNumber
    * @return sum
    * 
    */
   public double calculatePi(int inputNumber) throws InterruptedException;
}
