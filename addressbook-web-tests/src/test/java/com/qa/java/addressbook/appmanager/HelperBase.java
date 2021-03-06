package com.qa.java.addressbook.appmanager;

import org.openqa.selenium.*;

import java.io.File;
import java.util.List;

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

  protected WebElement findElement(By locator){
    WebElement element = wd.findElement(locator);
    return element;
  }

  protected List<WebElement> findElements(By locator){
    List<WebElement> element = wd.findElements(locator);
    return element;
  }

  protected void type(By locator, String text) {
    if (text !=null) {
      String existingText = findElement(locator).getAttribute("value"); //извлекаем из поля содержимое
      if (! text.equals(existingText))
    {
        findElement(locator).clear();
        findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file) {
    if (file !=null) {
        findElement(locator).sendKeys(file.getAbsolutePath()); //get.AbsolutePath() преобразовывает абсолютный путь в относительный
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
  public void alertAccept() {
    wd.switchTo().alert().accept();
    findElement(By.tagName("body"));
  }


  protected boolean isElementPresent(By locator) {
    try {
      findElement(locator);
      return true;
    } catch (NoSuchElementException ex){
    return false;
    }
  }
}

