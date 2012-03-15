package edu.upenn.cis350;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ActivitiesList extends Activity {

	private List<ActivitiesListItem> listOfItems;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitieslist);
        
        ListView list = (ListView) findViewById(R.id.ActivitiesList);
        list.setClickable(true);
        
        listOfItems = populateList();

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
    }

    /** handles click for Edit Activity button **/
    /*public void onEditActivitiesClick(View view) {
    	Intent i = new Intent(this, EditActivities.class);
    	startActivityForResult(i, AttendanceTakerActivity.ACTIVITY_EditActivities);
    }*/
    
    public void onAddActivityClick(View view){
    	//does nothing right now
    }
    
    /** creates and populates roster **/
	public List<ActivitiesListItem> populateList(){
		List<ActivitiesListItem> lst = new ArrayList<ActivitiesListItem>();
		lst.add(new ActivitiesListItem("basketball"));
        lst.add(new ActivitiesListItem("chess club"));
        lst.add(new ActivitiesListItem("activity 3"));
		return lst;
	}
	
}