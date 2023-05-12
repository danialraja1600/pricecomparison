package com.danialraja.kitcomparrison;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Contains main method for application
 */
public class Main {

   public static void main(String[] args) { 
      // runApplicationsXMLConfig();
       runApplicationAnnotationsConfig();
       
   }
   
   /** Uses Spring XML configuration to set up and run application */
   static void runApplicationsXMLConfig(){
        //Instruct Spring to create and wire beans using XML file
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        
        //Get bean
        scraperManager scraperManager = (scraperManager) context.getBean("myCar");
        
        //Call methods on bean
        scraperManager.drive();
   }
   
   
   /** Uses Spring Annotation configuration to set up and run application */
   static void runApplicationAnnotationsConfig(){
       //Instruct Spring to create and wire beans using annotations.
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
   
        //Get bean
        scraperManager scraperManager = (scraperManager) context.getBean("scraperManager");
        
        //Call methods on scraper bean
        scraperManager.drive();
   }

    private static class scraperManager {

        private static void drive() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public scraperManager() {
        }
    }

    private static class AppConfig {

        public AppConfig() {
        }
    }
   
}
