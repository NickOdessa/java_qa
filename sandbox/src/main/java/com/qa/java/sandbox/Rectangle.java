package com.qa.java.sandbox;

/**
 * Created by user on 23.11.2016.
 */
public class Rectangle {

  public double a;
  public double b;

  public Rectangle(double a, double b){
    this.a=a;
    this.b=b;
  }

  public  double area(){
    return this.a * this.b;
  }
}