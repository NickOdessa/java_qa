package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 11.12.2016.
 */
public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0){ //проверяем есть ли контакт, если нет, то создаем его
      app.contact().create(new ContactData().withFirstname("Nick22")
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
  public void testContactDeletion(){
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().returnToHomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(deletedContact);
   Assert.assertEquals(before, after);
  }
}
