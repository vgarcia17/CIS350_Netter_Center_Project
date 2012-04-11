package edu.upenn.cis350;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/***
 * Handles interaction with online Parse datastore
 * In general, methods convert to ActivityObject or StudentObject
 *
 */

//TODO fix addToActivityRoster and getStudentByActivity
public class ParseHandler {

	/**
	 * given a name, adds an activity to Parse datastore
	 * @param name
	 */
	public static void addActivity(String name){
        ParseObject activity = new ParseObject("Activity");
        activity.put("Name", name);
        activity.saveInBackground();
	}
	
	/**
	 * retrieves Activity with given objectId from datastore
	 * @param id
	 */
	public static ActivityObject getActivityById(String id){
		ActivityObject activity = new ActivityObject();
		ParseQuery query = new ParseQuery("Activity");
		query.whereEqualTo("objectId", id);
		
		try{
			ParseObject p = query.getFirst();
			activity = new ActivityObject(p.getObjectId(), p.getString("Name"), p.getUpdatedAt());
		} catch(ParseException e){
			e.printStackTrace();
		}
		
		return activity;
	}
	
	public static ActivityObject getActivityByName(String name){
		ActivityObject activity = new ActivityObject();
		ParseQuery query = new ParseQuery("Activity");
		query.whereEqualTo("name", name);
		
		try {
			ParseObject a = query.getFirst();	
			activity = new ActivityObject(a.getObjectId(),a.getString("Name"),a.getUpdatedAt());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return activity;
	}
	
	public static List<ActivityObject> getAllActivities(){
		ParseQuery query = new ParseQuery("Activity");
		List<ParseObject> queryList = new ArrayList<ParseObject>();
		List<ActivityObject> activityList = new ArrayList<ActivityObject>();
		try{
			queryList = query.find();
			for(ParseObject p : queryList){
				activityList.add(new ActivityObject(p.getObjectId(), p.getString("Name"), p.getUpdatedAt()));
			}
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		return activityList;
	}
	
	/**
	 * enrolls a student in an activity by putting them on the roster
	 * @param s - the student
	 * @param a - the activity
	 */
	public static void addToActivityRoster(StudentObject s, ActivityObject a){
		String sid = s.getStudentid();
		String aid = a.getID();
		ParseObject rosterAdd = new ParseObject("Roster");
		rosterAdd.put("studentId", sid);
		rosterAdd.put("activityId", aid);
		rosterAdd.saveInBackground();
	}
	
	public static void addStudent(StudentObject s){
		ParseObject student = new ParseObject("Student");
		student.put("Name", s.getName());
        student.saveInBackground();
	}
	
	public static StudentObject getStudentById(String id){
		StudentObject student = new StudentObject();
		ParseQuery query = new ParseQuery("Student");
		query.whereEqualTo("objectId", id);
		
		try{
			ParseObject p = query.getFirst();
			student = new StudentObject(p.getObjectId(), p.getString("Name"));
		} catch(ParseException e){
			e.printStackTrace();
		}
		
		return student;
	}
	
	public static StudentObject getStudentByName(String name){
		StudentObject student = new StudentObject();
		ParseQuery query = new ParseQuery("Student");
		query.whereEqualTo("Name", name);
		
		try {
			ParseObject a = query.getFirst();	
			student = new StudentObject(a.getNumber("ID").toString(), a.getNumber("Parse_1112GradeLevel").intValue(),
					a.getString("Name"),a.getString("phoneNumber"),a.getString("Address"), a.getString("School"), a.getString("Site"),"Program?",
					a.getString("emergencyContanctName"), a.getString("emergencyContanctRelationship"));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
	public static List<StudentObject> getAllStudents(){
		ParseQuery query = new ParseQuery("Student");
		List<ParseObject> queryList = new ArrayList<ParseObject>();
		List<StudentObject> studentList = new ArrayList<StudentObject>();
		try{
			queryList = query.find();
			Log.v("hello", queryList.size()+"");
			for(ParseObject student : queryList){
				studentList.add(new StudentObject(student.getNumber("ID").toString(), student.getNumber("Parse_1112GradeLevel").intValue(),
						student.getString("Name"),student.getString("phoneNumber"),student.getString("Address"), student.getString("School"), student.getString("Site"),"Program?",
						student.getString("emergencyContactName"), student.getString("emergencyContactRelationship")));
			}
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		return studentList;
	}
	
	/**
	 * retrieves all the students enrolled in a given activity
	 * @param a - the activity
	 * @return
	 */
	public static List<StudentObject> getStudentsByActivity(ActivityObject a){
		ParseQuery query = new ParseQuery("Roster");
		query.whereEqualTo("activityId", a.getID());
		List<ParseObject> queryList = new ArrayList<ParseObject>();
		List<StudentObject> studentList = new ArrayList<StudentObject>();
		
		try{
			queryList = query.find();
			for(ParseObject p : queryList){
				ParseObject student = (ParseObject) p.get("studentId");
				studentList.add(new StudentObject(student.getNumber("ID").toString(), student.getNumber("Parse_1112GradeLevel").intValue(),
						student.getString("Name"),student.getString("phoneNumber"),student.getString("Address"), student.getString("School"), student.getString("Site"),"Program?",
						student.getString("emergencyContactName"), student.getString("emergencyContactRelationship")));
			}
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		return studentList;
	}
	
}
