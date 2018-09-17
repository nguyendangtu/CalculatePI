package com.calculation.calculatingpi.beans.test;

import java.lang.reflect.Method;

public abstract class PrivateMethodTestBase {
   @SuppressWarnings({ "rawtypes", "unchecked" })
   public Method getPrivateMethodToTest(Object instance, String methodName, Class... param)
         throws SecurityException, NoSuchMethodException {
      Class clazz = instance.getClass();
      Method method = clazz.getDeclaredMethod(methodName, param);
      method.setAccessible(true);
      return method;
   }

}
