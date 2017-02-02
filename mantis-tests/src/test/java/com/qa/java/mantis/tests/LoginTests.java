package com.qa.java.mantis.tests;

import com.qa.java.mantis.appmanager.HttpSession;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by user on 02.02.2017.
 */
public class LoginTests extends TestBase {

  @Test
  public  void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator","123456"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
