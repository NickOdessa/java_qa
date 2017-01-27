package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import com.qa.java.addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.withOut(deletedContact)));
  }
}
