package edu.upenn.cis350;

/** put old code here if hesitant about deleting it **/
public class OldCode {
	
/**ActivitiesAdapter.java**/
	/**getView()**/
		// button to edit an activity
		//  Button btnEdit = (Button) convertView.findViewById(R.id.btnEdit);
		//    btnEdit.setFocusableInTouchMode(false);
		//    btnEdit.setFocusable(false);
		//    btnEdit.setOnClickListener(this);
		//    btnEdit.setTag(entry);	// associates this row with this button
	   
		//  Button btnOk = (Button) convertView.findViewById(R.id.btnOk);
		//  btnEdit.setFocusableInTouchMode(false);
		//  btnEdit.setFocusable(false);
		//  btnEdit.setOnClickListener(this);
		//  btnEdit.setTag(entry);	// associates this row with this button

	/**onClick**/
		// get current string for activity
		//	ActivitiesListItem entry = (ActivitiesListItem) v.getTag();
		//	String current = entry.getName();
			
			// switch view to edittext and switch button
			//ViewSwitcher view_switcher = (ViewSwitcher) v.findViewById(R.id.view_switcher);
		    //ViewSwitcher button_switcher = (ViewSwitcher) v.findViewById(R.id.button_switcher);
		    //view_switcher.showNext(); //or switcher.showPrevious();
		    //button_switcher.showNext();
	       // notifyDataSetChanged();

	/* protected Dialog onCreateDialog(int id) {
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
	
/** AttendanceTakerActivity.java **/
	/*ParseObject testStudent = new ParseObject("Student");
    testStudent.put("Name", "Sean Welleck");
    testStudent.put("Phone", "123-444-5678");
    ParseObject testStudent2 = new ParseObject("Student");
    testStudent2.put("Name", "Christian Cocking");
    testStudent2.put("Phone", "222-222-3333");
    ParseObject testStudent3 = new ParseObject("Student");
    testStudent3.put("Name", "Victor Garcia");
    testStudent3.put("Phone", "222-897-3333");
    testStudent.saveInBackground();
    testStudent2.saveInBackground();
    testStudent3.saveInBackground();
    ParseObject testActivity = new ParseObject("Activity");
    testActivity.put("Name", "coding");
    testActivity.saveInBackground();
    ParseObject testRoster = new ParseObject("Roster");
    testRoster.put("activityName", testActivity);
    testRoster.put("member", testStudent);
    
    testRoster.saveInBackground();
    Log.v("adf", "fewds");
    
    /*Log.d("Insert: ", "Inserting ..");
        db.addActivity(new edu.upenn.cis350.Activity("coding", 234));
        db.addActivity(new edu.upenn.cis350.Activity("chipotle", 124));
        db.addActivity(new edu.upenn.cis350.Activity("class", 5234));
        
        db.close();*/
        //db.addMember(new ActivityObject(52, "coding", 32234), new StudentObject(41, "larry"));
        //db.close();
	
	 //DatabaseHandler db = new DatabaseHandler(this);
    //String s = db.getAllMembers().get(0);
    //db.close();
    //Log.v("adf", s);
    
	
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


}
