package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test (enabled = false)
  public void testContactCreation() {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("Nick22", "Petrov1", "Nick12", "test23", "Own Company", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com"); //"Nick22", "Petrov1", "Nick12", "test23", "Own Company", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com"
    app.contact().create(contact, true);
    app.goTo().returnToHomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(contact);
    contact.setId(after.stream().max(((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))).get().getId());
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
