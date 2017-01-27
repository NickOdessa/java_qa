package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Nick22")
            .withLastname("Petrov1")
            .withNickname("Nick12")
            .withGroup("test23")
            .withCompany("Own Company")
            .withAddress("Odessa, Ukraine")
            .withMobile("+380487777777")
            .withEmail("nick_test@mailinator.com");
    app.contact().create(contact, true);
    app.goTo().returnToHomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() +1);
    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
