package com.qa.java.addressbook.tests;

import com.qa.java.addressbook.model.GroupData;
import com.qa.java.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0){ //проверяем есть ли группа, если нет, то создаем ее
            app.group().create(new GroupData().withName("test23"));
        }
    }

    @Test
    public void testGroupDeletion() {
       Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.group().all();
        assertEquals(after.size(),before.size() - 1);
        assertThat(after, equalTo(before.withOut(deletedGroup)));
    }

}
