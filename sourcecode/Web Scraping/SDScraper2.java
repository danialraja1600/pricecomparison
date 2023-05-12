package com.danialraja.kitcomparrison;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/** Example code to illustrate web scraping with JSoup */
public class SDScraper2 {
    
    
    /** Constructor */
    SDScraper2(){
        try{
            scrapeKit();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    /** Scrapes kit data from website */
    void scrapeKit() throws Exception{
        //Name of item that we want to scrape
       // String itemName = "kits";
        
        //Download HTML document from website
        Document doc = Jsoup.connect("https://www.sportsdirect.com/SearchResults?DescriptionFilter=arsenall%20shirt ").get();
        
        //Get all of the products on the page
        Elements prods = doc.select(".s-productthumbbox");
        
        //Work through the products
        for(int i=0; i<prods.size(); ++i){
            
            System.out.println("https://www.sportsdirect.com"+prods.get(i).select(".productimage").select("a").attr("href"));
            
            //Get the product description
            Elements description = prods.get(i).select(".s-producttext-top-wrapper");
            
//            System.out.println((prods.get(i).select("img")).attr("src"));
           
            
            //Get the product price
            Elements price1 = prods.get(i).select(".s-producttext-price");
//            ;
////           
//          
//            
//            
//            
//            Output the data that we have downloaded
            System.out.println("DESCRIPTION: " + description.text() + 
                     "; PRICE: "+ (price1.select(".curprice")).text() + " ; URL :"+ "https://www.sportsdirect.com"+prods.get(i).select(".productimage").select("a").attr("href") );
        }
    }
}