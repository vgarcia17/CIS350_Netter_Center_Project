package edu.upenn.cis350;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AttendanceTakerActivity extends Activity {

    private static final int LOGIN_DIALOG = 0;
    public static final int ACTIVITY_Attendance = 1;
    public static final int ACTIVITY_Roster = 2;
    public static final int ACTIVITY_AddActivity = 3;
    public static final int ACTIVITY_ActivitiesList = 4;
    public static final int ACTIVITY_EditActivities = 5;
    public static final int ACTIVITY_ProfileActivity = 6;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        showDialog(LOGIN_DIALOG);
        DatabaseHandler db = new DatabaseHandler(this);
        //db.onCreate(db.getWritableDatabase());
        /*Log.d("Insert: ", "Inserting ..");
        db.addActivity(new edu.upenn.cis350.Activity("coding", 234));
        db.addActivity(new edu.upenn.cis350.Activity("chipotle", 124));
        db.addActivity(new edu.upenn.cis350.Activity("class", 5234));
        
        db.close();*/
    }
    /*
     * https://docs.google.com/spreadsheet/ccc?key=0AnxAdFnBqUmwdGI2ZUVaRG5yOHlHdjh6NHpZdExxVUE
    protected Dialog onCreateDialog(int id) {
    	if (id == LOGIN_DIALOG) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Enter your username and password");
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(1);
            final EditText input1 = new EditText(this);
	        final EditText input2 = new EditText(this);
	        ll.addView(input1);
	        ll.addView(input2);
	        builder.setView(ll);
	    	builder.setPositiveButton(R.string.Login,
	    		new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	        	   dialog.cancel();
	    	           }
	    	         });
    		return builder.create();
    	}
    	else return null;
    }
    
    public void onRosterClick(View view) {
    	Intent i = new Intent(this, Roster.class);
    	startActivityForResult(i, AttendanceTakerActivity.ACTIVITY_Roster);
    }
    
    public void onActivityClick(View view) {
    	Intent i = new Intent(this, AddActivity.class);
    	startActivityForResult(i, AttendanceTakerActivity.ACTIVITY_AddActivity);
    }
    */
    
    public void onLoginClick(View view) {
    	Intent i = new Intent(this, ActivitiesList.class);
    	startActivityForResult(i, AttendanceTakerActivity.ACTIVITY_ActivitiesList);
    }
    
    public void onRegisterClick(View view) {
    	Context context = getApplicationContext();
		CharSequence text = "Registration not available yet.";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	super.onActivityResult(requestCode, resultCode, intent);
    }
}

