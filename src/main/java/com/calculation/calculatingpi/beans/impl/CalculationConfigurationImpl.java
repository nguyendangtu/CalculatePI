/**
 * File: CalculationConfigurationImpl.java
 * Version: 0.1
 * Revision: $log$
 * Date: 25/04/2014
 * 
 * GBST Java Coding Problem: solution about Calculating Pi by 
 * using multiple threading
 */
package com.calculation.calculatingpi.beans.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.calculation.calculatingpi.beans.CalculationConfiguration;

/**
 * <p>
 * Provide calculation configuration
 * </p>
 * 
 * @author TUNGUYEN
 */
@Service(value = "calculationConfiguration")
public class CalculationConfigurationImpl implements CalculationConfiguration {

   @Value("${devidend}")
   public String devidend;

   @Value("${max_number}")
   public String maxNumber;

   public int getDevidend() {
      return Integer.parseInt(devidend);
   }

   public int getMaxNumber() {
      return Integer.parseInt(maxNumber);
   }
}
