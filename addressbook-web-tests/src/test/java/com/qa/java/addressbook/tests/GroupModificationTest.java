package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.List;

/**
 * Created by user on 11.12.2016.
 */
public class GroupModificationTest extends TestBase{

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){ //проверяем есть ли группа, если нет, то создаем ее
      app.getGroupHelper().creatGroup(new GroupData("test23", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() -1);
    app.getGroupHelper().initGroupModofication();
    GroupData group = new GroupData(before.get(before.size() -1).getId(),"test43", "test3", "test47");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size() -1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
