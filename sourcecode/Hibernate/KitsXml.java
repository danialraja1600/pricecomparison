package com.danialraja.kitcomparrison;

/** Represents kits */
public class KitsXml {
    
    private int id;
    private int kit_type;  
    
    
    /** Empty constructor */
    public KitsXml(){
    }

    
    //Getters and setters
    public int getId() {
        return id;
    }

    public int get() {
        return kit_type;
    }

    public void kit_type(int type) {
        this.kit_type = type;
    }

    
    /*Returns a String description of the class */
    public String toString() {
        String str = "Kits. id: " + id +;
        kit_type:
        " + kitType ";
        return str;
    }
}

