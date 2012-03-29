package edu.upenn.cis350;

public class ActivityObject {

	    private int id;
	    private String name;
	    private int userid;

	    public ActivityObject(){
	 
	    }
	    // constructor
	    public ActivityObject(int id, String name, int userid){
	        this.id = id;
	        this.name = name;
	        this.userid = userid;
	    }
	    
	    public ActivityObject(String name, int userid){
	    	this.name = name;
	        this.userid = userid;
	    }

	    // getting ID
	    public int getID(){
	        return this.id;
	    }
	 
	    // setting id
	    public void setID(int id){
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
	    public int getUserId(){
	        return this.userid;
	    }
	 
	    // setting userId
	    public void setUserId(int userid){
	        this.userid = userid;
	    }
	
}
