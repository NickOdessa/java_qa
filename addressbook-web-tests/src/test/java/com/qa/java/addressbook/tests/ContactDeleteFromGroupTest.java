package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import com.qa.java.addressbook.model.Contacts;
import com.qa.java.addressbook.model.GroupData;
import com.qa.java.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 02.02.2017.
 */
public class ContactDeleteFromGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) { //проверяем есть ли контакт, если нет, то создаем его
      app.contact().create(new ContactData()
              .withFirstname("Nick22")
              .withLastname("Petrov1")
              .withNickname("Nick12")
              .withCompany("Own Company")
              .withAddress("Odessa, Ukraine")
              .withMobilePhone("+380487777777")
              .withEmail("nick_test@mailinator.com"), true);
    }
    if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test 123"));
         }
  }

  @Test
  public void testContactDeleteFromGroup () {
    app.goTo().returnToHomePage();
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData selectedAContact = before.iterator().next();
    GroupData group = groups.iterator().next();
    Groups beforeGroups = app.db().getIdGroupsToContact(selectedAContact.getId());
    app.contact().selectContactAdded1();
    app.contact().deleteContactFromGroup(selectedAContact);
    app.goTo().returnToHomePage();
    app.contact().selectContactAdded2();
    Groups afterGroups=app.db().getIdGroupsToContact(selectedAContact.getId());
    assertThat(afterGroups, equalTo(beforeGroups.withOut(group)));

  }


}
