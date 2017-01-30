package com.qa.java.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by user on 07.12.2016.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);

  }
  public void returnToHomePage() {

    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
    return;
    }
      click(By.linkText("groups")); }


    public void goToContactCreationPage()
    {
      click(By.linkText("add new"));
    }
  public void changeActiveWindow(){
    switchWindow();
  }
}
