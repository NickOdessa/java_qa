package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.GroupData;
import com.qa.java.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by user on 11.12.2016.
 */
public class GroupModificationTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0){ //проверяем есть ли группа, если нет, то создаем ее
      app.group().create(new GroupData().withName("test23"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("test43")
            .withHeader("test3")
            .withFooter("test47");
    app.group().modify(group);
    Groups after = app.group().all();
    assertEquals(after.size(),before.size());
    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
  }

}
