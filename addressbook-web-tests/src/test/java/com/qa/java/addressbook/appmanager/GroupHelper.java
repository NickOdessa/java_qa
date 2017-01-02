package com.qa.java.addressbook.appmanager;

import com.qa.java.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by user on 07.12.2016.
 */
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {

    super(wd);
  }

  public void returnToGroupPage() {

    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {

    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroup() {
    click(By.name("delete"));
  }

  public void selectGroup() {

    click(By.name("selected[]"));
  }

  public void initGroupModofication() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void creatGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
   return findElements(By.name("selected[]")).size();
  }
}
