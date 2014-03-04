package com.example.garvit_tfi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Program to open and do all the functionality for the user
public class Rate 
{
	// create all the columns needed in you table
	public static final String KEY_ROWID ="_id";
	public static final String KEY_NAME ="user_name";
	public static final String KEY_PARAMETER1 ="parameter_1";
	public static final String KEY_PARAMETER2 ="parameter_2";
	public static final String KEY_PARAMETER3 ="parameter_3";
	public static final String KEY_PARAMETER4 ="parameter_4";
	public static final String KEY_PARAMETER5 ="parameter_5";
	
	// create the name of the Database and Table to be used in the Database
	private static final String DATABASE_NAME = "Rateuser";
	private static final String DATABASE_TABLE = "usertable";
	private static final int DATABASE_VERSION = 1;
	
	private Dbuser ouruser;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	// create the calss Dbuser which extends the SQLiteOpenHelper
	private static class Dbuser extends SQLiteOpenHelper
	{
		// define the constructor of Dbuser class
		public Dbuser(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		// to create a database
		public void onCreate(SQLiteDatabase db) 
		{
			// TODO Auto-generated method stub
			// to set up the database i,e., create a table for our database
			db.execSQL(
					"CREATE TABLE "+ DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_PARAMETER1 + " FLOAT NOT NULL, " +
					KEY_PARAMETER2 + " FLOAT NOT NULL, " +
					KEY_PARAMETER3 + " FLOAT NOT NULL, " +
					KEY_PARAMETER4 + " FLOAT NOT NULL, " +
					KEY_PARAMETER5 + " FLOAT NOT NULL);"
					);
		}

		@Override
		// if database is already created then call this on upgrade method
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	 public Rate(Context c)
	 {
		 ourContext = c;
	 }
	 
	 // to open the database and write to it
	 public Rate open() throws SQLException
	 {
		 ouruser = new Dbuser(ourContext);
		 //open up the database through our  user
		 ourDatabase = ouruser.getWritableDatabase();
		 return this;
	 }
	 
	 // to close the database
	 public void close()
	 {
		 ouruser.close();
	 }

	 // to insert into database values in the basis of retrieve input from user
	public long createntry(String name, float rating1, float rating2,
			float rating3, float rating4, float rating5) 
	{
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME,name);
		cv.put(KEY_PARAMETER1,rating1);
		cv.put(KEY_PARAMETER2,rating2);
		cv.put(KEY_PARAMETER3,rating3);
		cv.put(KEY_PARAMETER4,rating4);
		cv.put(KEY_PARAMETER5,rating5);
		
		return ourDatabase.insert(DATABASE_TABLE,null,cv);
	}

	// retrieve the data form the table when user presses the View Button
	public String getData() 
	{
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_PARAMETER1,KEY_PARAMETER2,KEY_PARAMETER3,KEY_PARAMETER4,KEY_PARAMETER5};
		Cursor c= ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
		String result ="";
		
		int rID =c.getColumnIndex(KEY_ROWID);
		int rNAME =c.getColumnIndex(KEY_NAME);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			result = result + c.getString(rID) + " " + c.getString(rNAME) + "\n";
		}
		return result;
	}
}

