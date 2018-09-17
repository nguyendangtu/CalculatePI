/**
 * File: CalculationConfiguration.java
 * Version: 0.1
 * Revision: $log$
 * Date: 25/04/2014
 * 
 * GBST Java Coding Problem: solution about Calculating Pi by 
 * using multiple threading
 */
package com.calculation.beans;

/**
 * <p>
 * Interface CalculationConfiguration define all all get configuration function
 * </p>
 * 
 * @author TUNGUYEN
 */
public interface CalculationConfiguration {

   /**
    * @return the dividend value from configuration
    */
   public int getDevidend();
   
   /**
    * @return the max number of input value number
    */
   public int getMaxNumber();
}
