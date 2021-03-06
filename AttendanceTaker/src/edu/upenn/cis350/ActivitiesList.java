package edu.upenn.cis350;

import java.util.List;

import com.parse.Parse;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ActivitiesList extends Activity {

	private List<ActivityObject> listOfItems;
	private static final int ADD_ACTIVITY = 0;
	private DatabaseHandler db;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitieslist);
        //db = new DatabaseHandler(this);
        
        Parse.initialize(this, "cuoXWbqvBKs8SUrhnyKdyNWiMPZxuDBZ31ehltVI", "tl8VMcFHu7u3haym9KSbRKEP61MmxPDvmL06dxeo"); 
        
        createList();
        //db.close();
        
    }
    
    public void createList() {
    	listOfItems = populateList();
        ActivitiesAdapter adapter = new ActivitiesAdapter(this, listOfItems);
        
        ListView list = (ListView) findViewById(R.id.ActivitiesList);
        list.setClickable(true);
        //click listener for each list item - on click, goes to Roster page
        list.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {  
        	String name = listOfItems.get(position).getName();
            Intent i = new Intent(view.getContext(), Roster.class);
      	  	i.putExtra("name", name);
        	startActivityForResult(i, AttendanceTakerActivity.ACTIVITY_Roster);
          }
        });
        
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onAddActivityClick(View view){
    	showDialog(ADD_ACTIVITY);
    }
    
    @Override
	protected Dialog onCreateDialog(int id) {
    	if (id == ADD_ACTIVITY) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Enter activity name");
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
	    	        	//db.addActivity(new ActivityObject(activity_string, 123));
	    	        	ParseHandler.addActivity(activity_string);
	    	        	dialog.cancel();
	    	        	createList();
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
    
    /** creates and populates roster **/
	public List<ActivityObject> populateList(){
		List<ActivityObject> actlist = ParseHandler.getAllActivities();
		//List<edu.upenn.cis350.ActivityObject> actlist = db.getAllActivities();
		return actlist;
	}
	
}