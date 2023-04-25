package com.example.lab_11_gps_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseConnector {
    //DB Name
    private static final String DATABASE_NAME = "LocationsDB";

    //We create this to run SQL commands
    private SQLiteDatabase db;

    //This is to create or open databases
    private DatabaseOpenHelper dbOpenHelper;

    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        // public constructor
        public DatabaseOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Creates the table LatLongDesc when the database is created
        // This is called from  open()->getWritableDatabase(). Only if the database does not exist.
        @Override
        public void onCreate(SQLiteDatabase db) {
            // query to create a new table named contacts
            String createQuery = "CREATE TABLE LatLongDesc" +
                    "(_id integer primary key autoincrement," +
                    "LATITUDE TEXT, LONGITUDE TEXT, DESCRIPTION TEXT);";

            db.execSQL(createQuery); // execute the query
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
        }
    }

    public DatabaseConnector(Context context){
        dbOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME,null,1);
    }

    //Open DB Connection
    public void open() throws SQLException{
        //Create or open a DB for reading/writing
        //At the first call, onCreate is called
        db = dbOpenHelper.getWritableDatabase();
    }

    //Close DB Connection
    public void close(){
        if (db!=null){
            db.close();
        }
    }

    //Insert a new entry into the DB
    public void insertEntry(String Lat, String Long,
                            String Desc){
        ContentValues newEntry = new ContentValues();
        newEntry.put("LATITUDE",Lat);
        newEntry.put("LONGITUDE",Long);
        newEntry.put("DESCRIPTION",Desc);

        open();
        db.insert("LatLongDesc",null,newEntry);
        close();


    }

    //Return Cursor with all contact info
    public Cursor getAllEntries(){
        return db.rawQuery("SELECT * FROM LatLongDesc",null);
    }

    //Get Cursor containing info based on search query
    public Cursor getSearchEntries(String searchText){
        Log.d("Lab-10", searchText);
        return db.rawQuery("SELECT * FROM LatLongDesc WHERE DESCRIPTION like " +
                "'%"+searchText+"%'",null);
    }


    public void deleteContact(long id){
        open();
        db.delete("LatLongDesc","_id=" + id,null);
        close();
    }

    //Add getAllEntriesInAListOfStrings if required



}
