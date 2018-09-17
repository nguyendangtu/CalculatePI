package com.calculation.calculatingpi.main;

import java.io.Console;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.calculation.calculatingpi.beans.Calculation;

public class Main {

   public static void main(String[] args) throws InterruptedException {

      Console c = System.console();
      if (c == null) {
         System.err.println("No console.");
         System.exit(1);
      }
      int input = -1;
      while (true) {
         String inputValue = c.readLine("Enter your number to calculate Pi: ");
         try {
            input = Integer.parseInt(inputValue);
            if (input >= 0 && input <= Calculation.MAX_NUMBER) {
               ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/ApplicationContext.xml");
               Calculation calculation = (Calculation) context.getBean("calculation");
               System.out.println("Calculation Pi: " + calculation.calculatePi(input));
            } else {
               System.out.println("ERROR: input number incorrect, " + "please input another number in range [0, "
                     + Calculation.MAX_NUMBER + "]");
            }
         } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR: input number incorrect, the input must be a number");
         } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR: input number incorrect, the input must be a number");
         }
      }
   }
}