package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.GroupData;
import org.testng.annotations.Test;

import java.lang.annotation.Target;

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
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModofication();
    app.getGroupHelper().fillGroupForm(new GroupData("test43", "test3", null));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}