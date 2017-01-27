package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0){ //проверяем есть ли группа, если нет, то создаем ее
            app.group().create(new GroupData("test23", null, null));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.group().list();
        int index = before.size() -1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() -1);

    before.remove(index);
            Assert.assertEquals(before, after);
    }

}
