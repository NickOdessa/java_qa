package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by user on 11.12.2016.
 */
public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() {
    int  before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()){ //проверяем есть ли контакт, если нет, то создаем его
      app.getContactHelper().createContact(new ContactData("Nick22", "Petrov1", "Nick12", "test23", "Own Company", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Nick22", "Petrov12", "Nick55", null,  "Roga and Kopyta!", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com"), false );
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    int  after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
