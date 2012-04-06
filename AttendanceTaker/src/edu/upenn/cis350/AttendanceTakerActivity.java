package edu.upenn.cis350;

import com.parse.Parse;
import com.parse.ParseObject;

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
        
        //initializes connection with Parse
        Parse.initialize(this, "cuoXWbqvBKs8SUrhnyKdyNWiMPZxuDBZ31ehltVI", "tl8VMcFHu7u3haym9KSbRKEP61MmxPDvmL06dxeo"); 
    }
    
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

