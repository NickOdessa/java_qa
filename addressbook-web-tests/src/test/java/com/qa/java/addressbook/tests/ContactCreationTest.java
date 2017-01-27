package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import com.qa.java.addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
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
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }
}
