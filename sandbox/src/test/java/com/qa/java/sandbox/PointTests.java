package com.qa.java.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by user on 01.12.2016.
 */
public class PointTests {

  @Test
  public void testDistance() {
    Point a = new Point(4,30);
    Point b = new Point(16,2);

    Assert.assertEquals(a.getDistance(b),30.463092423455635);


  }
  @Test
  public void testDistance2() {
    Point a = new Point(1,38);
    Point b = new Point(12,5);

    Assert.assertEquals(a.getDistance(b),34.785054261852174);

  }

}
