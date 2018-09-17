package com.calculation.beans.test;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

import junitx.util.PrivateAccessor;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import com.calculation.beans.Calculation;
import com.calculation.beans.impl.CalculationImpl;

public class CalculationTest extends CalculationTestBase {

   private Calculation testedObject = new CalculationImpl();

   @Before
   public void setUp() throws Exception {
      super.setUp();
      PrivateAccessor.setField(testedObject, "calculationConfiguration", calculationConfiguration);

   }

   @Test
   public void testCalculatePi_positive() throws SecurityException, NoSuchMethodException, IllegalAccessException,
         IllegalArgumentException, InvocationTargetException, InterruptedException {
      DecimalFormat df = new DecimalFormat("#.#####");
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      replayAll();
      double testValue = Double.valueOf(df.format(CORRECT_NUMBER_RESULT));
      double pi = Double.valueOf(df.format(testedObject.calculatePi(CORRECT_NUMBER)));
      assertTrue(testValue == pi);
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculatePi_number_zero_positive() throws InterruptedException {
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      replayAll();
      assertTrue(CORRECT_NUMBER_ZERO_RESULT == testedObject.calculatePi(CORRECT_NUMBER_ZERO));
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculatePi_number_zero_negative() throws InterruptedException {
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      replayAll();
      assertFalse(CORRECT_NUMBER_ZERO_RESULT != testedObject.calculatePi(CORRECT_NUMBER_ZERO));
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculatePi_number_10_positive() throws SecurityException, NoSuchMethodException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
      DecimalFormat df = new DecimalFormat("#");
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      replayAll();
      double testValue = Double.valueOf(df.format(CORRECT_NUMBER_RESULT));
      double pi = Double.valueOf(df.format(testedObject.calculatePi(CORRECT_NUMBER_10)));
      assertTrue(testValue == pi);
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculatePi_number_100_positive() throws SecurityException, NoSuchMethodException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
      DecimalFormat df = new DecimalFormat("#.#");
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      replayAll();
      double testValue = Double.valueOf(df.format(CORRECT_NUMBER_RESULT));
      double pi = Double.valueOf(df.format(testedObject.calculatePi(CORRECT_NUMBER_100)));
      assertTrue(testValue == pi);
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculatePi_number_1000_positive() throws SecurityException, NoSuchMethodException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
      DecimalFormat df = new DecimalFormat("#.##");
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      replayAll();
      double testValue = Double.valueOf(df.format(CORRECT_NUMBER_RESULT));
      double pi = Double.valueOf(df.format(testedObject.calculatePi(CORRECT_NUMBER_1000)));
      assertTrue(testValue == pi);
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculatePi_number_10000_positive() throws SecurityException, NoSuchMethodException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
      DecimalFormat df = new DecimalFormat("#.##");
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      replayAll();
      double testValue = Double.valueOf(df.format(CORRECT_NUMBER_RESULT));
      double pi = Double.valueOf(df.format(testedObject.calculatePi(CORRECT_NUMBER_10000)));
      assertTrue(testValue == pi);
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculatePi_odd_positive() throws InterruptedException {
      DecimalFormat df = new DecimalFormat("#.#########");
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      replayAll();
      double testValue = Double.valueOf(df.format(CORRECT_NUMBER_RESULT));
      double pi = Double.valueOf(df.format(testedObject.calculatePi(CORRECT_NUMBER_ODD)));
      assertTrue(testValue == pi);
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculatePi_odd_negative() throws InterruptedException {
      DecimalFormat df = new DecimalFormat("#.#########");
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      replayAll();
      double testValue = Double.valueOf(df.format(CORRECT_NUMBER_RESULT));
      double pi = Double.valueOf(df.format(testedObject.calculatePi(CORRECT_NUMBER_ODD)));
      assertFalse(testValue != pi);
      PowerMock.verifyAll();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testCalculatePi_incorrect_number_exception() throws InterruptedException {
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      replayAll();
      assertTrue(INCORRECT_NUMBER_RESULT == testedObject.calculatePi(INCORRECT_NUMBER));
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculateAmountOfThread_one_positive() throws SecurityException, NoSuchMethodException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      Method method = this.getPrivateMethodToTest(testedObject, "calculateNumberOfThread", int.class);
      replayAll();
      Integer result = (Integer) method.invoke(testedObject, DEVIDEND);
      assertTrue(THREAD_NUMBER_ONE == result);
      PowerMock.verifyAll();

   }

   @Test
   public void testCalculateAmountOfThread_one_negative() throws SecurityException, NoSuchMethodException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      Method method = this.getPrivateMethodToTest(testedObject, "calculateNumberOfThread", int.class);
      replayAll();
      Integer result = (Integer) method.invoke(testedObject, DEVIDEND);
      assertFalse(THREAD_NUMBER_ONE != result);
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculateAmountOfThread_size_positive() throws SecurityException, NoSuchMethodException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      Method method = this.getPrivateMethodToTest(testedObject, "calculateNumberOfThread", int.class);
      replayAll();
      Integer result = (Integer) method.invoke(testedObject, CORRECT_NUMBER);
      assertTrue(Runtime.getRuntime().availableProcessors() == result);
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculateAmountOfThread_size_negative() throws SecurityException, NoSuchMethodException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      Method method = this.getPrivateMethodToTest(testedObject, "calculateNumberOfThread", int.class);
      replayAll();
      Integer result = (Integer) method.invoke(testedObject, CORRECT_NUMBER);
      assertFalse(Runtime.getRuntime().availableProcessors() != result);
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculateAmountOfThread_min_size_positive() throws SecurityException, NoSuchMethodException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      Method method = this.getPrivateMethodToTest(testedObject, "calculateNumberOfThread", int.class);
      replayAll();
      Integer result = (Integer) method.invoke(testedObject, CORRECT_NUMBER_ZERO);
      assertTrue(THREAD_MIN_SIZE == result);
      PowerMock.verifyAll();
   }

   @Test
   public void testCalculateAmountOfThread_min_size_negative() throws SecurityException, NoSuchMethodException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      expect(calculationConfiguration.getDevidend()).andReturn(DEVIDEND).anyTimes();
      Method method = this.getPrivateMethodToTest(testedObject, "calculateNumberOfThread", int.class);
      replayAll();
      Integer result = (Integer) method.invoke(testedObject, CORRECT_NUMBER_ZERO);
      assertFalse(THREAD_MIN_SIZE != result);
      PowerMock.verifyAll();
   }
}
