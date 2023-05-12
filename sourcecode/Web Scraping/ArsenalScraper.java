package com.danialraja.kitcomparrison;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/** Example code to illustrate web scraping with JSoup */
public class ArsenalScraper {

    ArsenalScraper(){
        try{
            scrapeKit();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
            void scrapeKit() throws Exception{
        //Name of item that we want to scrape
       // String itemName = "kits";
        
        //Download HTML document from website
        Document doc = Jsoup.connect("https://arsenaldirect.arsenal.com/Football-Shirts-and-Kit/c/kit").get();
        
        //Get all of the products on the page
        Elements prods = doc.select(".item");
        
        //Work through the products
        for(int i=0; i<prods.size(); ++i){
            
            //Get the product description
            Elements description = prods.get(i).select(".item-name");
            
            //Get the product price
            Elements price1 = prods.get(i).select(".details-wrapper");
            Elements finalPrice = price1.select(".item-price");
            
            //Get the weight
            //Elements weight = prods.get(i).select(".fop-catch-weight");
            
            //Output the data that we have downloaded
            System.out.println("DESCRIPTION: " + description.text() + 
                     "; PRICE: " + finalPrice.text());
        }
    }
}

