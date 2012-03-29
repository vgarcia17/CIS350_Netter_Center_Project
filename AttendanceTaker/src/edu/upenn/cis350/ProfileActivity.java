package edu.upenn.cis350;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends Activity{

	private static final int EDIT_NAME = 0;
	private static final int EDIT_PHONE = 1;
	private static final int EDIT_ADDRESS = 2;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileactivity);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        TextView t = (TextView)findViewById(R.id.profileName);
        t.setText(name);
    }

	public void onEditNameButtonClick(View view) {
		showDialog(EDIT_NAME);	
	}
	
	public void onEditPhoneButtonClick(View view) {
		showDialog(EDIT_PHONE);	
	}
	
	public void onEditAddressButtonClick(View view) {
		showDialog(EDIT_ADDRESS);
	}
	
    @Override
	protected Dialog onCreateDialog(int id) {
    	if (id == EDIT_NAME) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("New name:");
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(1);
            final EditText input = new EditText(this);
	        ll.addView(input);
	        builder.setView(ll);
	    	builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int id) {
	    			Editable name = input.getText();
	    	        String name_string = name.toString();
	    	        TextView tv = (TextView)findViewById(R.id.profileName);
	    	        tv.setText(name_string);
	    		}
	    	        
	    	 });
	    	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int id) {
	    			dialog.cancel();
	    		}
	    	});
	    	return builder.create();
    	}
    	else if (id == EDIT_PHONE) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("New phone number:");
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(1);
            final EditText input = new EditText(this);
	        ll.addView(input);
	        builder.setView(ll);
	    	builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int id) {
	    			Editable number = input.getText();
	    	        String number_string = number.toString();
	    	        TextView tv = (TextView)findViewById(R.id.profilePhone);
	    	        tv.setText(number);
	    		}
	    	        
	    	 });
	    	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int id) {
	    			dialog.cancel();
	    		}
	    	});
	    	return builder.create();
    	}
    	if (id == EDIT_ADDRESS) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("New address:");
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(1);
            final EditText input = new EditText(this);
	        ll.addView(input);
	        builder.setView(ll);
	    	builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int id) {
	    			Editable address = input.getText();
	    	        String address_string = address.toString();
	    	        TextView tv = (TextView)findViewById(R.id.profileAddress);
	    	        tv.setText(address_string);
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