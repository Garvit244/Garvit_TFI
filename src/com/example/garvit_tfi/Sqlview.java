package com.example.garvit_tfi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

// to show all the users who uses that application
public class Sqlview extends Activity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		
		// create the object of TextView
		TextView tv = (TextView) findViewById(R.id.tableinfo);
		
		// create the object of Rate which refer to the current event
		Rate info = new Rate(this);
		// open that created object
		info.open();
		// retrieve the data from Rate.getData() method
		String data = info.getData();
		// close that info
		info.close();
		// show the retrieve content in the TextView
		tv.setText(data);
	}
}