package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by user on 11.12.2016.
 */
public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()){ //проверяем есть ли контакт, если нет, то создаем его
      app.getContactHelper().createContact(new ContactData("Nick22", "Petrov1", "Nick12", "test23", "Own Company", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData (before.get(before.size() -1).getId(),"Nick22", "Petrov12", "Nick55", null,  "Roga and Kopyta!", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com");
    app.getContactHelper().initContactModification(before.size() -1);
    app.getContactHelper().fillContactForm(contact, false );
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());


    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
