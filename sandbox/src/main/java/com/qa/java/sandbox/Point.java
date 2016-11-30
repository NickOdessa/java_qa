package com.qa.java.sandbox;

/**
 * Created by user on 24.11.2016.
 */
public class  Point {

  double x;
  double y;

public Point (double x, double y) {
  this.x = x;
  this.y = y;}

  double getDistance(Point b) {

    return Math.sqrt((x - b.x)* (x - b.x) + (y - b.y)*(y - b.y));
  }
}

