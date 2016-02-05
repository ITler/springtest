package frontend;

import static org.junit.Assert.*;

import java.net.URL;
import java.net.URLClassLoader;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Inject;

import models.PersonRepository;
import play.Application;
import play.ApplicationLoader.Context;
import play.Environment;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.test.Helpers;
import play.test.WithApplication;

public class ApplicationTest extends WithApplication {

    @Inject private PersonRepository repo;

    @Inject Application application;
    
    @BeforeClass
    public static void classpath() {
        ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();

        //Get the URLs
        URL[] urls = ((URLClassLoader)sysClassLoader).getURLs();

        for(int i=0; i< urls.length; i++)
        {
            System.out.println(urls[i].getFile());
        }
    }

    @Before
    public void setup() {
        GuiceApplicationBuilder builder = new GuiceApplicationLoader()
          .builder(new Context(Environment.simple()))
//          .configure((Map) Helpers.inMemoryDatabase())
//          .in(Mode.TEST)
          ;
        Guice.createInjector(builder.applicationModule()).injectMembers(this);
        
    }

    @After
    public void teardown() {
      Helpers.stop(application);
    }

    @Test
    public void testIndex() throws InterruptedException {
//        running(application, () -> {
//            // running not needed because of extends WithApplication
//        });
        assertNotNull("string");
    }
}