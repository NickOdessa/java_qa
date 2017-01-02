package com.qa.java.sandbox;

import org.apache.tools.ant.taskdefs.optional.depend.constantpool.IntegerCPInfo;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by user on 02.01.2017.
 */
public class PrimeTest {

  @Test
  public void testPrime(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrime(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }

  @Test(enabled = false)
  public void testPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testPrimeFast(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }
}
