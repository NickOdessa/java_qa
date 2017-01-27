package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Created by user on 11.12.2016.
 */
public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0){ //проверяем есть ли контакт, если нет, то создаем его
      app.contact().create(new ContactData()
              .withFirstname("Nick22")
              .withLastname("Petrov1")
              .withNickname("Nick12")
              .withGroup("test23")
              .withCompany("Own Company")
              .withAddress("Odessa, Ukraine")
              .withMobile("+380487777777")
              .withEmail("nick_test@mailinator.com"), true);
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("Nick22")
            .withLastname("Petrov1")
            .withNickname("Nick12")
            .withGroup("test23")
            .withCompany("Own Company")
            .withAddress("Odessa, Ukraine")
            .withMobile("+380487777777")
            .withEmail("nick_test@mailinator.com");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    before.add(contact);
    Assert.assertEquals(before,after);
  }

}
