- This project is an simple example by using multiple thread when we want to calculate Pi
- We using producer/consumer patter to implement this function.
    - Create thread producers to calculation value from i to j, and put value to SharedQueue
    - Create thread consumers to get value from sharedQueue, sum all, put value to resultQueue
    - Create Calculation class which have a method calculatePi, in this method, we create a
      thread pool to handle all threads. Start producer to calculate for each part, and start 
      consumer to sum all value from producers. 
- To start this example. Would you please following steps:
    1. Download Eclipse and add Maven plugin 3.04
    2. Import project by using Maven
    3. Execute MainClass to see the result
    4. Go to package com.calculation.beans.test, then run method by method in class CalculationTest.java
     to see the the result.
        - We can change configuration into the application.properties file.