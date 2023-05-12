package com.danialraja.kitcomparrison;

//Hibernate imports
import com.danialraja.kitcomparrison.ProductsXml;
import java.util.List;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;

public class HibernateXml1 {

    //Use this class to create new Sessions to interact with the database 
    private SessionFactory sessionFactory;

    /**
     * Empty constructor
     */
    HibernateXml1() {
    }

    /**
     * Sets up the session factory. Call this method first.
     */
    public void init() {
        try {
            //Create a builder for the standard service registry
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

            //Load configuration from hibernate configuration file
            standardServiceRegistryBuilder.configure("resources/hibernate.cfg.xml");

            //Create the registry that will be used to build the session factory
            StandardServiceRegistry registry = standardServiceRegistryBuilder.build();

            try {
                //Create the session factory - this is the goal of the init method.
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                /* The registry would be destroyed by the SessionFactory, 
                        but we had trouble building the SessionFactory, so destroy it manually */
                System.err.println("Session Factory build failed.");
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy(registry);
            }

            //Ouput result
            System.out.println("Session factory built.");

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("SessionFactory creation failed." + ex);
        }
    }

    /**
     * Closes Hibernate down and stops its threads from running
     */
    public void shutDown() {
        sessionFactory.close();
    }

    /**
     * Adds a new product to the database
     */
    public void addProducts() {
        //Get a new Session instance from the SessionFactory
        Session session = sessionFactory.getCurrentSession();

        //Create an instance of a Products class 
        ProductsXml products = new ProductsXml();

        //Set values of Products class that we want to add
        products.setKitId(1);
        products.setTeamId(1);
        products.setProduct_description(1);
        products.setProduct_image(1);
        products.setProduct_price(20.5f);
        products.setProduct_url(1);

        //Start transaction
        session.beginTransaction();

        //Add Products to database - will not be stored until we commit the transaction
        session.save(products);

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Cereal added to database with ID: " + products.getId());
    }

    /**
     * Updates the values of an existing cereal in the database
     */
    public void updateCereal() {
        //Get a new Session instance from the session factory
        Session session = sessionFactory.getCurrentSession();

        //Create an instance of a Products class 
        ProductsXml products = new ProductsXml();

        //Set ID of products class - this controls the row in the table that we want to update
        products.setId(16);

        //Set new values of Products class that we want to add
        products.setKitId(1);
        products.setTeamId(1);
        products.setProduct_description(1);
        products.setProduct_image(1);
        products.setProduct_price(40.5f);
        products.setProduct_url(1);

        //Start transaction
        session.beginTransaction();

        //Update database to match class 
        session.update(products);
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Product updated in database. ID: " + products.getId());
    }

    /**
     * Searches for Products whose price is 5.5
     */
    public void searchProducts() {
        //Get a new Session instance from the session factory
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        List<ProductsXml> productsList = session.createQuery("from ProductsXml where price=5.5").getResultList();
        for (ProductsXml products : productsList) {
            System.out.println(products.toString());
        }

        //Close the session and release database connection
        session.close();
    }

    /**
     * Deletes an object without a foreign key from the database This will
     * generate an error if there is a foreign key.
     */
    public void deleteProductsSimple() {
        //Create Cereal class with the ID that we want to delete
        ProductsXml products = new ProductsXml();
        products.setId(5);

        //Get a new Session instance from the session factory
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        //Update database to match class 
        session.delete(products);
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Cereal updated in database. ID: " + products.getId());
    }

    /**
     * Deletes a product in a way that will work with tables with foreign keys
     */
    public void deleteProductSafe() {
        //Get a new Session instance from the session factory
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        //Search for prouct in database that has ID of 6
        Object persistentInstance = session.load(ProductsXml.class, 16);

        //Delete object (and corresponding data) if we have found a match.
        if (persistentInstance != null) {
            session.delete(persistentInstance);
        }

        //Update database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Products updated in database. ID: 6");
    }

}

