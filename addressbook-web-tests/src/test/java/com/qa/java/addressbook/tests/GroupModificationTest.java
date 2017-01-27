package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("test43")
            .withHeader("test3")
            .withFooter("test47");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(),before.size());

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before,after);
  }

}
