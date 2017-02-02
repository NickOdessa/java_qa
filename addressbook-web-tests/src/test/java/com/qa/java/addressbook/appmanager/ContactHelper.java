package com.qa.java.addressbook.appmanager;

import com.qa.java.addressbook.model.ContactData;
import com.qa.java.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

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
    //attach(By.name("photo"),contactData.getPhoto());
    //type(By.name("nickname"), contactData.getNickname());
   // type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());


    if (creation) {
      if (contactData.getGroup() != null) {
        new Select(findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
    }
      else {
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
    contactCache = null;
    //returnToHomePage();
  }

  public void selectContact(ContactData selectedContact) {
    selectAddContactToGroupById(selectedContact.getId());
    selectGroupToAdd();
    addContactToGroup();
    contactCache = null;
  }


  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false );
    submitContactModification();
    contactCache = null;
    //returnToHomePage();
  }

  public void deleteContactFromGroup(ContactData selectedContact) {
    selectAddContactToGroupById(selectedContact.getId());
    deleteAddedContactFromGroup();
    contactCache = null;
  }

  private void deleteAddedContactFromGroup() {
    click(By.xpath("//input[@type='submit']"));
  }

  public void selectContactAdded1(){
    click(By.xpath("//form[@id='right']/select/option[3]"));
  }

  public void selectContactAdded2(){
    click(By.xpath("//form[@id='right']/select/option[4]"));
  }


  public void viewInfoById (int id){
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id))).click();
   /* WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
    WebElement row = checkbox.findElement(By.xpath("./../..")); //Находим нужную строку
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();*/ //находим нужную ячейку с символом редактирования
  }

  public void selectGroupToAdd(){
    click(By.xpath("//div[@class='right']/select/option[1]"));
  }

  public void selectAddContactToGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void addContactToGroup() {
    click(By.name("add"));
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address1 = wd.findElement(By.xpath(".//textarea[@name='address']")).getText();
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withFirstname(firstname)
            .withLastname(lastname)
            .withAddress(address1)
            .withHomePhone(home)
            .withMobilePhone(mobile)
            .withWorkPhone(work)
            .withEmail(email)
            .withEmail2(email2)
            .withEmail3(email3);
  }

  public void viewDetails() {
    wd.findElement(By.cssSelector("div[id='content']")).getText();
  }
  public WebElement find(By locator) {
    return wd.findElement(locator);
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
    contactCache = null;
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

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache !=null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText(); //Получаем значение из 1 ячейки
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData()
              .withId(id)
              .withFirstname(firstname)
              .withLastname(lastname)
              .withAddress(address)
              .withAllEmails(allEmails)
              .withAllPhones(allPhones));
    }
    return new Contacts(contactCache);
  }

}

