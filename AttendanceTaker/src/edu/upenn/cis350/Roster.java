package edu.upenn.cis350;

import java.util.List;

import com.parse.ParseObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

/**
 * represents the 'roster' page.
 * extends ListActivity. Each list item is defined in list_item.xml, 
 * while the overall layout is defined in roster.xml
 *
 */
public class Roster extends Activity{

	private List<StudentObject> listOfItems; //change variable name
	private static final int ADD_STUDENT = 0;
	private DatabaseHandler db;
	private String activityName;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roster);
   
        Bundle extras = getIntent().getExtras();
        activityName = extras.getString("Name");
        
        //db = new DatabaseHandler(this);
        createRoster(); 
        //db.close();
    }
    
    public void createRoster() {
    	listOfItems = populateRoster();
        ListView list = (ListView) findViewById(R.id.RosterList);
        
        //Log.v("list", list +" "+ findViewById(R.id.RosterList));
        //handles null case
  		if(list == null){
  			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  			list = (ListView) inflater.inflate(R.id.RosterList, null);
  		}
        
        list.setClickable(true);

        RosterAdapter adapter = new RosterAdapter(this, listOfItems);
        
        //click listener for each list item
        list.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        	  // get info for the student to be displayed
        	  StudentObject student = listOfItems.get(position);
        	  String name = student.getName();
        	  String idNumber = student.getStudentid();
        	  int grade = student.getGradeLevel();
        	  String phone = student.getPhone();
        	  String address = student.getAddress();
        	  String school = student.getSchool();
        	  String site = student.getSite();
        	  String contactName = student.getContactName();
        	  String contactType = student.getContactType();
        	  
        	  Intent i = new Intent(view.getContext(), ProfileActivity.class);
        	  
        	  // put info for the student in intent
        	  i.putExtra("Name", name);
        	  i.putExtra("idNumber", idNumber);
        	  i.putExtra("grade", grade);
        	  i.putExtra("phone", phone);
        	  i.putExtra("address", address);
        	  i.putExtra("school", school);
        	  i.putExtra("site", site);
        	  i.putExtra("contactName", contactName);
        	  i.putExtra("contactType", contactType);
        	  startActivityForResult(i, AttendanceTakerActivity.ACTIVITY_ProfileActivity);
          }
        });
        
        list.setAdapter(adapter);
 
    }
    
    /** creates and populates roster **/
	public List<StudentObject> populateRoster(){
		List<StudentObject> actlist = ParseHandler.getAllStudents();
		return actlist;
	}

	
    public void onAddStudentClick(View view) {
    	showDialog(ADD_STUDENT);
    }
    
	/** handles click for Back button **/
	public void onSubmitButtonClick(View view) {
		//do some stuff with Google Spreadsheets
		if(listOfItems == null){
			Toast.makeText(getApplicationContext(), "No Students in Roster",
	                Toast.LENGTH_SHORT).show();
		}
		else{
			String output = "";
			for(StudentObject i : listOfItems){
				output += i.getName() + " : " + i.getStatus() + "\n";
			}
			Toast.makeText(getApplicationContext(), output,
	                Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * under construction
	 */
	public void submitRoster(){
		//A = getActivity();
		Bundle extras = getIntent().getExtras();
        String activityName = extras.getString("Name");
        //get Activity ParseObject with name == activityName
        
        //for each student S
        for(StudentObject s: listOfItems){
        	//get Student ParseObject p corresponding to this StudentObject
        	
        	ParseObject attendanceRow = new ParseObject("AttendanceRecord");
        	
        	//attendanceRow.put("date", get the date somehow);
        	attendanceRow.put("studentName", s.getName());
        	//attendanceRow.put("studentId", StudentParseObject.getId());
        	//attendanceRow.put("activityName", ActivityParseObject.getName());
        	//attendanceRow.put("activityId", ActivityParseObject.getId());
        	attendanceRow.put("status", s.getStatus());

        	attendanceRow.saveInBackground();
        }
	}
	
    @Override
	protected Dialog onCreateDialog(int id) {
    	if (id == ADD_STUDENT) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Enter student name");
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(1);
            final EditText input = new EditText(this);
	        ll.addView(input);
	        builder.setView(ll);
	    	builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int id) {
	    			Editable activity = input.getText();
	    	        String activity_string = activity.toString();
	    	        boolean activity_already_exists = false;
	    	        for(int i = 0; i < listOfItems.size(); i++) {
	    	        	if(listOfItems.get(i).getName().equals(activity_string)) {
	    	        		activity_already_exists = true;
	    	        	}
	    	        }
	    	        if(activity_already_exists) {
    	        		Context context = getApplicationContext();
    	        		CharSequence text = "Activity already exists.";
    	        		int duration = Toast.LENGTH_SHORT;
    	        		Toast toast = Toast.makeText(context, text, duration);
    	        		dialog.cancel();
    	        		toast.show();
	    	        }
	    	        else {
	    	        	ParseHandler.addStudent(new StudentObject(activity_string));
	    	        	dialog.cancel();
	    	        	createRoster();
	    	        }
	    	    }
	    	 });
	    	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int id) {
	    			dialog.cancel();
	    		}
	    	});
	    	return builder.create();
    	}
    	else return null;
    }
}
