package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    int  before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Nick22", "Petrov1", "Nick12", "test23", "Own Company", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com"), true);
    int  after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before +1);
  }
}
