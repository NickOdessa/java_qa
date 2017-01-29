package com.qa.java.addressbook.tests;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.testng.annotations.BeforeMethod;
import com.qa.java.addressbook.model.ContactData;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 29.01.2017.
 */
public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0){ //проверяем есть ли контакт, если нет, то создаем его
      app.contact().create(new ContactData().withFirstname("Nick22")
              .withLastname("Petrov1")
              .withNickname("Nick12")
              .withCompany("Own Company")
              .withAddress("Odessa, Ukraine")
              .withHomePhone("+380487777776")
              .withMobilePhone("+380487777777")
              .withWorkPhone("+380487777778")
              .withEmail("nick_test@mailinator.com"), true);
    }
  }
  @Test
  public void testContactPhones() {
    app.goTo().returnToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private  String mergePhones (ContactData contact) {
   return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
      .stream().filter((s) -> ! s.equals(""))
           .map(ContactPhoneTests::cleaned)
           .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
