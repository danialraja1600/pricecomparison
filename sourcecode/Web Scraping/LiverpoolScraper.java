package com.danialraja.kitcomparrison;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/** Example code to illustrate web scraping with JSoup */
public class LiverpoolScraper {

    LiverpoolScraper(){
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
        Document doc = Jsoup.connect("https://www.uksoccershop.com/sphinx?keyword=Liverpool+Home+Shirts").get();
        
        //Get all of the products on the page
        Elements prods = doc.select(".product_block odd_block");
        
        //Work through the products
        for(int i=0; i<prods.size(); ++i){
            
            //Get the product description
            Elements description = prods.get(i).select(".productname");
            
            //Get the product price
            Elements price1 = prods.get(i).select(".right_product_column");
            Elements finalPrice = price1.select(".productprice common_product_price_special");
            
            
            //Output the data that we have downloaded
            System.out.println("DESCRIPTION: " + description.text() + 
                     "; PRICE: " + finalPrice.text());
        }
    }

}



