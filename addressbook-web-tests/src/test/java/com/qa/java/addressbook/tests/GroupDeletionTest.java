package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        int  before = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereAGroup()){ //проверяем есть ли группа, если нет, то создаем ее
        app.getGroupHelper().creatGroup(new GroupData("test23", null, null));
        }
        app.getGroupHelper().selectGroup(before -1);
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
        int  after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before -1);
    }

}
