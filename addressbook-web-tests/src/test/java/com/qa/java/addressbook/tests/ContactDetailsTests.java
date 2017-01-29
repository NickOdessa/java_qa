package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 29.01.2017.
 */
public class ContactDetailsTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0){ //проверяем есть ли контакт, если нет, то создаем его
      app.contact().create(new ContactData()
              .withFirstname("Nick22")
              .withLastname("Petrov1")
              .withNickname("Nick12")
              .withCompany("Own Company")
              .withAddress("Odessa, Ukraine")
              .withHomePhone("+380487777776")
              .withMobilePhone("+380487777777")
              .withWorkPhone("+380487777778")
              .withEmail("nick_test@mailinator1.com")
              .withEmail2("nick_test@mailinator2.com")
              .withEmail3("nick_test@mailinator3.com"), true);
    }
  }

  @Test
  public void contactDetails () {
    app.goTo().returnToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String mergedContactInfoFromEditForm = mergeContactData(contactInfoFromEditForm);
    app.contact().viewInfoById(contact.getId());
    String alltext = cleaned(app.contact().find(By.id("content")).getText());
    assertThat(alltext, equalTo(mergedContactInfoFromEditForm));
  }

  private String mergeContactData(ContactData contact){
    return Arrays.asList(
            contact.getFirstname(),
            contact.getLastname(),
            contact.getAddress(),
            contact.getHomePhone(),
            contact.getMobilePhone(),
            contact.getWorkPhone(),
            contact.getEmail(),
            contact.getEmail2(),
            contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactDetailsTests::cleaned)
            .collect(Collectors.joining(""));
  }

  public static String cleaned(String cleaned) {
    return cleaned.replaceAll("\\s", "").replaceAll("[-()HMW:]","").replaceAll("\n", "");
  }

}
