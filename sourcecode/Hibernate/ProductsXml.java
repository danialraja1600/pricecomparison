package com.danialraja.kitcomparrison;

/** Represents kits */
public class ProductsXml {
    
    private int id;
    private int kit_id;
    private int team_id;
    private String product_name; 
    private String product_url;
    private String product_description;
    private String product_image;
    private float product_price;
    
    /** Empty constructor */
    public ProductsXml(){
    }

    
    //Getters and setters
    public int getId() {
        return id;
    }

    public int getKit_Id() {
        return kit_id;
    }

    public int getTeamId() {
        return team_id;
    }
    
    public String setProduct_description(String desc) {
        this.product_description = desc;
        return product_description;
    }
    public void setProduct_price(float price) {
        this.product_price = price;
    }
    public String setProduct_url(String url){
        this.product_url = url;
        return product_url;
    }
    
    public String setProduct_image (String image){
        this.product_image = image;
        return product_image;
    }
    
    public String setProduct_name (String name){
        this.product_name = name;
        return product_name;
    }
    
    public float getPrice() {
        return product_price;
    }
   
    
    /** Returns a String description of the class */
//    public String toString(){
//        String str = "Cereal. id: " + id + "; productTypeId: " + productTypeId + "; brandId: " +
//                 brandId + "; urlId: " + urlId + "; price: " + price + "; weight: " + weight;
//        return str;
//    } 

    

    public String toString() {
        String str = "Products. id: " + id + "; Kit_Id: " + kit_Id + ";
        team_Id:
        " + team_Id + ";
        product_url:
        " + product_url + ";
        product_description:
        " + product_description ";
        product_image:
        " + product_image ";
        product_price:
        " + product price ";
    }
    }
