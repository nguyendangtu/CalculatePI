package com.calculation.beans.test;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.ArrayUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.calculation.beans.CalculationConfiguration;
import com.calculation.beans.impl.CalculationConfigurationImpl;

@RunWith(PowerMockRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/TestApplicationContext.xml" })
public abstract class CalculationTestBase extends PrivateMethodTestBase {
   
   protected static final int CORRECT_NUMBER = 1000000000;
   protected static final double CORRECT_NUMBER_RESULT = 3.141592652589;
   protected static final int CORRECT_NUMBER_ODD = 999999999;
   protected static final double CORRECT_NUMBER_RESULT_ODD = 3.141592652589;
   protected static final int INCORRECT_NUMBER = -1;
   protected static final double INCORRECT_NUMBER_RESULT = -1.0;
   protected static final int CORRECT_NUMBER_ZERO = 0;
   protected static final double CORRECT_NUMBER_ZERO_RESULT = 4.0;
   protected static final int CORRECT_NUMBER_10 = 10;
   protected static final int CORRECT_NUMBER_100 = 100;
   protected static final int CORRECT_NUMBER_1000 = 1000;
   protected static final int CORRECT_NUMBER_10000 = 10000;
   protected static final int CORRECT_NUMBER_100000 = 100000;
   protected static final int CORRECT_NUMBER_100000000 = 100000000;
   protected static final int FROM_INDEX = 0;
   protected static final int TO_INDEX = 5;
   protected static final int THREAD_NUMBER_ONE = 1;
   protected static final int THREAD_MIN_SIZE = 1;
   protected static final int DEVIDEND = 200000;

   protected LinkedBlockingQueue<Double> sharedQueue;

   @Autowired
   protected CalculationConfiguration calculationConfiguration;

   private Object[] mockObjects;

   @Before
   public void setUp() throws Exception {
      sharedQueue = new LinkedBlockingQueue<Double>();
      calculationConfiguration = PowerMock.createMock(CalculationConfigurationImpl.class);
   }

   @After
   public void tearDown() {
      PowerMock.resetAll(ArrayUtils.addAll(new Object[] { calculationConfiguration }, mockObjects));
      sharedQueue.clear();
   }

   protected void replayAll(Object... args) {
      this.mockObjects = args;
      PowerMock.replayAll(ArrayUtils.addAll(new Object[] { calculationConfiguration }, args));
   }

}
