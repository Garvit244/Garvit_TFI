package com.example.garvit_tfi;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {

	// to create the two buttons, five rating bar and one textbox for user
		Button sqlinsert, sqlview;
		EditText sqlname;
		RatingBar r1,r2,r3,r4,r5;
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			// call on super class
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			// initalize all the variables
			sqlname = (EditText) findViewById(R.id.etusername);
			r1 = (RatingBar) findViewById(R.id.ratingBar1);
			r2 = (RatingBar) findViewById(R.id.RatingBar01);
			r3 = (RatingBar) findViewById(R.id.ratingBar3);
			r4 = (RatingBar) findViewById(R.id.ratingBar2);
			r5 = (RatingBar) findViewById(R.id.ratingBar4);
			sqlinsert = (Button) findViewById(R.id.bsqlinsert);
			sqlview = (Button) findViewById(R.id.bsqlview);
			
			// adding action listener to the two buttons 
			sqlinsert.setOnClickListener(this);
			sqlview.setOnClickListener(this);
		}

		@Override
		public void onClick(View arg0) 
		{
			// get the id of that button and proceed according to it
			switch(arg0.getId())
			{
				// if user select the insert option 
				case R.id.bsqlinsert:
					
					boolean work = true;
					try
					{
						String name = sqlname.getText().toString();	
						float rating1 = r1.getRating();
						float rating2 = r2.getRating();
						float rating3 = r3.getRating();
						float rating4 = r4.getRating();
						float rating5 = r5.getRating();
					
						// create an instance of Rate
						Rate entry = new Rate(this);
						//call the open method define in it
						entry.open();
						// enter the details of entered details into it
						entry.createntry(name,rating1,rating2,rating3,rating4,rating5);
						// close that event
						entry.close();
					}
					catch(Exception e)
					{
						work = false;
					}
					finally
					{
						// when finally all the work is done the check whether it is sucesfully entered or not
						if(work)
						{
							// to pop up the information menu choose the Dialog 
							Dialog d = new Dialog(this);
							// set the title to Rated
							d.setTitle("Rated");
							// create the instance of TextView
							TextView tx = new TextView(this);
							// when TextView is successfully created the show success
							tx.setText("Success!!");
							d.setContentView(tx);
							// show the Dialog
							d.show();
						}
					}
					sqlname.setText("");
					r1.setRating(0.0f);	
					break;
					
					// when the user select the view option to view all the users
				case R.id.bsqlview:
					// to view we need to create the intent
					Intent i = new Intent(this,Sqlview.class);
					// after creating the intent start that intent
					startActivity(i);
					break;
			}
			
		}

}
