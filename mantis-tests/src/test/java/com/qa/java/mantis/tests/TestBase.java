package com.qa.java.mantis.tests;

import com.qa.java.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by user on 06.12.2016.
 */
public class TestBase {


  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME)); // если в свойствах не указан броузер, то используется браузер по умолчанию из BrowserType

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  public ApplicationManager getApp() {
    return app;
  }


    }



