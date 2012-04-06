package edu.upenn.cis350;

import java.util.Date;

public class ActivityObject {

	    private String id;
	    private String name;
	    private Date date;

	    public ActivityObject(){
	 
	    }
	    // constructor
	    public ActivityObject(String id, String name, Date date){
	        this.id = id;
	        this.name = name;
	        this.date = date;
	    }
	    
	    public ActivityObject(String name){
	       
	        this.name = name;
	    }

	    // getting ID
	    public String getID(){
	        return this.id;
	    }
	 
	    // setting id
	    public void setID(String id){
	        this.id = id;
	    }
	 
	    // getting name
	    public String getName(){
	        return this.name;
	    }
	 
	    // setting name
	    public void setName(String name){
	        this.name = name;
	    }
	 
	    // getting userId
	    public Date getDate(){
	        return this.date;
	    }
	 
	    // setting userId
	    public void setDate(Date d){
	        this.date = d;
	    }
	
}
