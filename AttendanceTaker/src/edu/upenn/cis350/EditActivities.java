package edu.upenn.cis350;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditActivities extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editactivities);
    }

    public void onAddActivityClick(View view) {
    	Intent i = new Intent(this, AddActivity.class);
    	startActivityForResult(i, AttendanceTakerActivity.ACTIVITY_AddActivity);
    }
    
    public void onBackButtonClick(View view) {
		Intent i = new Intent();
		setResult(RESULT_OK, i);
		finish();	
	}
	
}