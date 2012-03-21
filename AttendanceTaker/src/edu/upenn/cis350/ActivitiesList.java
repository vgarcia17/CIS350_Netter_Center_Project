package edu.upenn.cis350;

import java.util.ArrayList;
import java.util.List;

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

	private List<ActivitiesListItem> listOfItems;
	private static final int ADD_ACTIVITY = 0;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitieslist);
        listOfItems = populateList();
        createList();
    }
    
    public void createList() {
        
        ListView list = (ListView) findViewById(R.id.ActivitiesList);
        list.setClickable(true);

        ActivitiesAdapter adapter = new ActivitiesAdapter(this, listOfItems);
        
        //click listener for each list item - on click, goes to Roster page
        list.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {          
            Intent i = new Intent(view.getContext(), Roster.class);
        	startActivityForResult(i, AttendanceTakerActivity.ACTIVITY_Roster);
          }
        });
        
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onAddActivityClick(View view){
    	showDialog(ADD_ACTIVITY);
    }
    
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
	    	        	listOfItems.add(new ActivitiesListItem(activity_string));
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
	public List<ActivitiesListItem> populateList(){
		List<ActivitiesListItem> lst = new ArrayList<ActivitiesListItem>();
		lst.add(new ActivitiesListItem("basketball"));
        lst.add(new ActivitiesListItem("chess club"));
        lst.add(new ActivitiesListItem("arts & crafts"));
		return lst;
	}
	
}