package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by user on 11.12.2016.
 */
public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0){ //проверяем есть ли контакт, если нет, то создаем его
      app.contact().create(new ContactData("Nick22", "Petrov1", "Nick12", "test23", "Own Company", "Odessa, Ukraine", "+380487777777", "nick_test@mailinator.com"), true);
    }
  }
  @Test (enabled = false)
  public void testContactDeletion(){
    List<ContactData> before = app.contact().list();
    int index = before.size() -1;
    app.contact().deleteContact(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() -1);
    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
