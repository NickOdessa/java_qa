package com.qa.java.addressbook.tests;


import com.qa.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    int  before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().creatGroup(new GroupData("test23", null, null));
    int  after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before +1); //кол-во после добавления должно совпадать с кол-вом до увеличенным на 1

  }


}
