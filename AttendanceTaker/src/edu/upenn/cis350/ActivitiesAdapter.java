package edu.upenn.cis350;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitiesAdapter extends BaseAdapter implements OnClickListener{
	
	private Context context;
	private List<ActivityObject> listActivities;
	private static final int EDIT_ACTIVITY = 0;
	
	//constructor
	public ActivitiesAdapter(Context context, List<ActivityObject> listActivities){
		this.context = context;
		this.listActivities = listActivities;
	}
	
	public int getCount() {
        return listActivities.size();
    }
	
	public Object getItem(int position) {
        return listActivities.get(position);
    }
	
	public long getItemId(int position) {
        return position;
    }
	
	public View getView(int position, View convertView, ViewGroup viewGroup){
		//gets entry associated with current row in ListView
		ActivityObject entry = listActivities.get(position);
		
		//handles null case
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_item, null);
		}
		// handles Student Name
		TextView tvActivityName = (TextView) convertView.findViewById(R.id.tvActivityName);
		tvActivityName.setText(entry.getName());
   
		// button to edit an activity
//      Button btnEdit = (Button) convertView.findViewById(R.id.btnEdit);
//        btnEdit.setFocusableInTouchMode(false);
//        btnEdit.setFocusable(false);
//        btnEdit.setOnClickListener(this);
//        btnEdit.setTag(entry);	// associates this row with this button
        
		EditText textEdit = (EditText) convertView.findViewById(R.id.hidden_edit_view);
		textEdit.setText(entry.getName());
   
//        Button btnOk = (Button) convertView.findViewById(R.id.btnOk);
//        btnEdit.setFocusableInTouchMode(false);
//        btnEdit.setFocusable(false);
//        btnEdit.setOnClickListener(this);
//        btnEdit.setTag(entry);	// associates this row with this button
        
        return convertView;
	}

	/** handles 'edit' button for each list item **/
	public void onClick(View v) {
		// get current string for activity
	//	ActivitiesListItem entry = (ActivitiesListItem) v.getTag();
	//	String current = entry.getName();
		
		// switch view to edittext and switch button
		//ViewSwitcher view_switcher = (ViewSwitcher) v.findViewById(R.id.view_switcher);
	    //ViewSwitcher button_switcher = (ViewSwitcher) v.findViewById(R.id.button_switcher);
	    //view_switcher.showNext(); //or switcher.showPrevious();
	    //button_switcher.showNext();
       // notifyDataSetChanged();
    }
/*    protected Dialog onCreateDialog(int id) {
    	if (id == EDIT_ACTIVITY) {
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
    }*/

	
}