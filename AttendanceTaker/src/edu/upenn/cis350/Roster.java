package edu.upenn.cis350;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/**
 * represents the 'roster' page.
 * extends ListActivity. Each list item is defined in list_item.xml, 
 * while the overall layout is defined in roster.xml
 *
 * TODO: 	-when clicked, each list item should go to corresponding profile
 * 			-pull names from persistent data source
 */
public class Roster extends Activity{

	private List<RosterListItem> listOfItems;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roster);
        
        ListView list = (ListView) findViewById(R.id.RosterList);
        
        Log.v("list", list +" "+ findViewById(R.id.RosterList));
        
        //handles null case
  		if(list == null){
  			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  			list = (ListView) inflater.inflate(R.id.RosterList, null);
  		}
        
        list.setClickable(true);
        
        listOfItems = populateRoster();

        RosterAdapter adapter = new RosterAdapter(this, listOfItems);
        
        //click listener for each list item
        list.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
            //Intent i = new Intent(view.getContext(), AddActivity.class);
        	//startActivityForResult(i, AttendanceTakerActivity.ACTIVITY_AddActivity);
          }
        });
        
        list.setAdapter(adapter);
        
    }
	
    /** handles click for Back button **/
	public void onBackButtonClick(View view) {
		Intent i = new Intent();
		setResult(RESULT_OK, i);
		finish();	
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
			for(RosterListItem i : listOfItems){
				output += i.getName() + " : " + i.getStatus() + "\n";
			}
			Toast.makeText(getApplicationContext(), output,
	                Toast.LENGTH_SHORT).show();
		}
	}
	
	/** handles click for Edit button **/
	public void onEditButtonClick(View view) {
		
	}
	
	/** creates and populates roster **/
	public List<RosterListItem> populateRoster(){
		List<RosterListItem> lst = new ArrayList<RosterListItem>();
		lst.add(new RosterListItem("Sean W.", "Present"));
        lst.add(new RosterListItem("Christian C.", "Present"));
        lst.add(new RosterListItem("Rupi S.", "Present"));
		return lst;
	}

}
