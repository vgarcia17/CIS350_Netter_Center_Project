package edu.upenn.cis350;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ActivitiesAdapter extends BaseAdapter implements OnClickListener{
	
	private Context context;
	private List<ActivitiesListItem> listActivities;
	
	//constructor
	public ActivitiesAdapter(Context context, List<ActivitiesListItem> listActivities){
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
		ActivitiesListItem entry = listActivities.get(position);
		
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
        Button btnEdit = (Button) convertView.findViewById(R.id.btnEdit);
        btnEdit.setFocusableInTouchMode(false);
        btnEdit.setFocusable(false);
        btnEdit.setOnClickListener(this);
        btnEdit.setTag(entry);	// associates this row with this button
        
        return convertView;
	}

	/** handles 'edit' button for each list item **/
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ActivitiesListItem entry = (ActivitiesListItem) v.getTag();
        entry.setName("chnage");
        notifyDataSetChanged();
	}
	

	
}