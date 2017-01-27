package com.qa.java.addressbook.appmanager;

import com.qa.java.addressbook.model.ContactData;
import com.qa.java.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 08.12.2016.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//input[@value='Enter']"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void selectContact(int index) {
    findElements(By.name("selected[]")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[@name='update'][1]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }


  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    //returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false );
    submitContactModification();
    //returnToHomePage();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id))).click();
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
    // app.goTo().changeActiveWindow();
    // app.goTo().returnToHomePage();
  }

  public void delete(ContactData deletedContact) {
    selectDeleteContactById(deletedContact.getId());
    deleteContact();
  }

  public void selectDeleteContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(
            By.xpath("//input[@name='selected[]'][1]"));
  }

  public int getContactCount() {
    return findElements(By.xpath("//input[@name='selected[]']")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }

}

