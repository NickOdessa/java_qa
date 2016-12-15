package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by user on 11.12.2016.
 */
public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Nick22", "Petrov12", "Nick55", "Roga and Kopyta!", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
