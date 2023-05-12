
package com.danialraja.kitcomparrison;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/** Example code to illustrate web scraping with JSoup */
public class NikeScraper {
    
    
    /** Constructor */
    NikeScraper(){
        try{
            scrapeKit();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    /** Scrapes kits data from the Nike website */
    void scrapeKit() throws Exception{
        //Name of item that we want to scrape
       // String itemName = "kits";
        
        //Download HTML document from website
        Document doc = Jsoup.connect("https://www.nike.com/gb/w?q=england%20football%20shirt&vst=england%20football%20shirt").get();
        
        //Get all of the products on the page
        Elements prods = doc.select(".product-card__body");
        
        //Work through the products
        for(int i=0; i<prods.size(); ++i){
            
            //Get the product description
            Elements description = prods.get(i).select(".product-card__title");
            
            //Get the product price
            Elements price1 = prods.get(i).select(".product-card__price-wrapper");
            Elements finalPrice = price1.select(".product-card__price");
            
            //Get the weight
            //Elements weight = prods.get(i).select(".fop-catch-weight");
            
            //Output the data that we have downloaded
            System.out.println("DESCRIPTION: " + description.text() + 
                     "; PRICE: " + finalPrice.text());
        }
    }
} 