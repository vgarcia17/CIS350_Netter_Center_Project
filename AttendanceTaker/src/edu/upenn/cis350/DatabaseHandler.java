package edu.upenn.cis350;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "contactsManager";
	//tables
	private static final String TABLE_ACTIVITY = "Activity";
	private static final String TABLE_USER = "User";
	private static final String TABLE_STUDENT = "Student";
 
    // Activity Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_USERID = "userid";
    
    // Activity Table Columns names
    private static final String KEY_STUD_ID = "studentid";
    private static final String KEY_STUD_GRADE = "grade";
    private static final String KEY_STUD_NAME = "name";
    private static final String KEY_STUD_PHONE = "phone";
    private static final String KEY_STUD_ADDRESS = "address";
    private static final String KEY_STUD_SCHOOL = "school";
    private static final String KEY_STUD_SITE = "site";
    private static final String KEY_STUD_PROGRAM = "program";
    private static final String KEY_STUD_CONTACTNAME = "contactName";
    private static final String KEY_STUD_CONTACTTYPE = "contactType";
    
    public DatabaseHandler(Context context){
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db){
    	String CREATE_ACTIVITY_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_ACTIVITY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_USERID + " INTEGER" + ")";
    	db.execSQL(CREATE_ACTIVITY_TABLE);
    	
    	
    	
    	String CREATE_STUDENT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_STUDENT + "("
    			+ KEY_STUD_ID + " INTEGER PRIMARY KEY," + KEY_STUD_GRADE + " INTEGER,"
    			+ KEY_STUD_NAME + " TEXT,"
                + KEY_STUD_PHONE + " TEXT," + KEY_STUD_ADDRESS + " TEXT,"
                + KEY_STUD_SCHOOL + " TEXT," + KEY_STUD_SITE + " TEXT,"
                + KEY_STUD_PROGRAM + " TEXT,"
                + KEY_STUD_CONTACTNAME + " TEXT,"
                + KEY_STUD_CONTACTTYPE + " TEXT"
                +")";
    	db.execSQL(CREATE_STUDENT_TABLE);
    }
    
    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    	//drop older table if existed
    	//db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
    	
    	//create tables again
    	onCreate(db);
    }
    
    //add new activity
    public void addActivity(ActivityObject activity){
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(KEY_NAME, activity.getName());
    	values.put(KEY_USERID, activity.getUserId());
    	
    	//inserts row
    	db.insert(TABLE_ACTIVITY, null, values);
    	db.close();
    }
    
    //add new student
    public void addStudent(StudentObject student){
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues values = new ContentValues();
    	values.put(KEY_STUD_GRADE, student.getGradeLevel());
    	values.put(KEY_STUD_NAME, student.getName());
    	values.put(KEY_STUD_PHONE, student.getPhone());
    	values.put(KEY_STUD_ADDRESS, student.getAddress());
    	values.put(KEY_STUD_SCHOOL, student.getSchool());
    	values.put(KEY_STUD_SITE, student.getSite());
    	values.put(KEY_STUD_PROGRAM, student.getProgram());
    	values.put(KEY_STUD_CONTACTNAME, student.getContactName());
    	values.put(KEY_STUD_CONTACTTYPE, student.getContactType());

    	//inserts row
    	db.insert(TABLE_STUDENT, null, values);
    	db.close();
    }
    
    //gets an activity
    public ActivityObject getActivity(int id){
    	SQLiteDatabase db = this.getReadableDatabase();
    	
    	Cursor cursor = db.query(TABLE_ACTIVITY, new String[] { KEY_ID,
                KEY_NAME, KEY_USERID }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        ActivityObject activity = new ActivityObject(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
       
        return activity;
    }
    
    public List<ActivityObject> getAllActivities() {
        List<ActivityObject> activityList = new ArrayList<ActivityObject>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ACTIVITY;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	ActivityObject activity = new ActivityObject();
                activity.setID(Integer.parseInt(cursor.getString(0)));
                activity.setName(cursor.getString(1));
                activity.setUserId(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                activityList.add(activity);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return activityList;
    }
    
    public List<StudentObject> getAllStudents(){
    	List<StudentObject> studentList = new ArrayList<StudentObject>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	StudentObject student = new StudentObject();
            	student.setStudentid(Integer.parseInt(cursor.getString(0)));
            	student.setGradeLevel(Integer.parseInt(cursor.getString(1)));
            	student.setName(cursor.getString(2));
            	student.setPhone(cursor.getString(3));
            	student.setAddress(cursor.getString(4));
            	student.setSchool(cursor.getString(5));
                student.setSite(cursor.getString(6));
                student.setProgram(cursor.getString(7));
                student.setContactName(cursor.getString(8));
                student.setContactType(cursor.getString(9));
                
                // Adding student to list
                studentList.add(student);   
                
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return studentList;
    	
    }
}
