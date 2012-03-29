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
    
    public DatabaseHandler(Context context){
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db){
    	String CREATE_ACTIVITY_TABLE = "CREATE TABLE " + TABLE_ACTIVITY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_USERID + " INTEGER" + ")";
    	db.execSQL(CREATE_ACTIVITY_TABLE);
    }
    
    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    	//drop older table if existed
    	db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
    	
    	//create tables again
    	onCreate(db);
    }
    
    //add new activity
    public void addActivity(Activity activity){
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(KEY_NAME, activity.getName());
    	values.put(KEY_USERID, activity.getUserId());
    	
    	//inserts row
    	db.insert(TABLE_ACTIVITY, null, values);
    	db.close();
    }
    
    //gets an activity
    public Activity getContact(int id){
    	SQLiteDatabase db = this.getReadableDatabase();
    	
    	Cursor cursor = db.query(TABLE_ACTIVITY, new String[] { KEY_ID,
                KEY_NAME, KEY_USERID }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Activity activity = new Activity(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
       
        return activity;
    }
    
    public List<Activity> getAllActivities() {
        List<Activity> activityList = new ArrayList<Activity>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ACTIVITY;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Activity activity = new Activity();
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
}
