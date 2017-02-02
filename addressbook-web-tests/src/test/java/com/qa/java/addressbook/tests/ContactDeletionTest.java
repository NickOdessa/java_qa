package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import com.qa.java.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by user on 11.12.2016.
 */
public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0){ //проверяем есть ли контакт, если нет, то создаем его
      app.contact().create(new ContactData().withFirstname("Nick22")
              .withLastname("Petrov1")
              .withNickname("Nick12")
              //.withGroup("test23")
              .withCompany("Own Company")
              .withAddress("Odessa, Ukraine")
              .withMobilePhone("+380487777777")
              .withEmail("nick_test@mailinator.com")
              .withPhoto(new File("src/test/resources/min.jpg")), true);
    }
  }
  @Test
  public void testContactDeletion(){
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().returnToHomePage();
    assertThat(app.contact().getContactCount(), equalTo(before.size() -1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(deletedContact)));
  }
}
