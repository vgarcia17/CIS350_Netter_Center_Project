package edu.upenn.cis350;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Custom adapter for the ListView used in Roster.
 * Allows multiple pieces of data to be placed in each row of the list
 *
 */
public class RosterAdapter extends BaseAdapter implements OnClickListener{
	
	private Context context;
	private List<StudentObject> listRoster;
	
	//constructor
	public RosterAdapter(Context context, List<StudentObject> listRoster){
		this.context = context;
		this.listRoster = listRoster;
	}
	
	public int getCount() {
        return listRoster.size();
    }
	
	public Object getItem(int position) {
        return listRoster.get(position);
    }
	
	public long getItemId(int position) {
        return position;
    }
	
	public View getView(int position, View convertView, ViewGroup viewGroup){
		//gets entry associated with current row in ListView
		final StudentObject entry = listRoster.get(position);
		
		//handles null case
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
		}
		// handles Student Name
		TextView tvRosterItemName = (TextView) convertView.findViewById(R.id.tvRosterItemName);
		tvRosterItemName.setText(entry.getName());
		
		//sets click listener for radio buttons - when clicked, changes status of RosterListItem
		RadioGroup radios = (RadioGroup) convertView.findViewById(R.id.radio_group1);	 
		radios.setFocusable(false);
		radios.setFocusableInTouchMode(false);
		for(int i=0; i < radios.getChildCount(); i++){  
			RadioButton btn = (RadioButton) radios.getChildAt(i);  
			btn.setFocusable(false);
			btn.setFocusableInTouchMode(false);
		}
		radios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {  
			public void onCheckedChanged(RadioGroup radiogroup, int checkedId){  
				for(int i=0; i < radiogroup.getChildCount(); i++){  
					RadioButton btn = (RadioButton) radiogroup.getChildAt(i);  
					if(btn.getId() == checkedId){  
						entry.setStatus(btn.getText()+"");                   
						return;   
					}  
				}
			}
		});
		radios.setTag(entry);
        return convertView;
	}
	
	//handles button click for specific row in the ListView
    public void onClick(View view) {
        notifyDataSetChanged();
    }
	
}
