package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 11.12.2016.
 */
public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0){ //проверяем есть ли контакт, если нет, то создаем его
      app.contact().create(new ContactData("Nick22", "Petrov1", "Nick12", "test23", "Own Company", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com"), true);
    }
  }

  @Test (enabled = false)
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() -1;
    ContactData contact = new ContactData (before.get(index).getId(),"Nick22", "Petrov12", "Nick55", null,  "Roga and Kopyta!", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());


    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }

}
