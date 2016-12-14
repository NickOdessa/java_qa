package com.qa.java.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by user on 08.12.2016.
 */
public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {

    wd.findElement(locator).click();
  }

  protected void findElement(By locator){

    wd.findElement(locator);
  }

  protected void type(By locator, String text) {
    WebElement element = wd.findElement(locator);
    if (text !=null) {
     element.clear();
      element.sendKeys(text);
    }
  }

  public void switchWindow(){
    wd.switchTo().alert().accept();
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}

