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
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test23"));
       }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("test43")
            .withHeader("test3")
            .withFooter("test47");
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
  }

}
