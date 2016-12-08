package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() {
    app.initContactCreation();
    app.fillContactForm(new ContactData("Nick22", "Petrov1", "Nick12", "Roga and Kopyta", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com"));
    app.submitContactCreation();
    app.returnToHomePage();
  }
}
