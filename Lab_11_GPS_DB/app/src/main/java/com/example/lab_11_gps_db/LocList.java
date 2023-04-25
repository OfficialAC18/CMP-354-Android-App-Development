package com.example.lab_11_gps_db;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LocList extends ListActivity implements AdapterView.OnItemClickListener {

    public static final String ROW_ID = "row_id";
    private ListView entriesListView;   //   The ListActivity's ListView

    //Sets User editable permissions
    public Boolean editPermission;

    //The Search text to query entries
    public String searchText;

    //Adapter for the ListView
    private CursorAdapter entryAdapter;
    //Adapter basically allows the ListView to get access to the data from Cursor
    //Cursor object must have a column named "_id" else it won't work


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Permission and Search Text
        editPermission = getIntent().getBooleanExtra("EDIT_PERMISSION",true);
        searchText = getIntent().getStringExtra("SEARCH_STRING");

        //Create the ListView and Set The event handler
        entriesListView = getListView();
        entriesListView.setOnItemClickListener(this);

        String[] from = new String[] {"DESCRIPTION","LATITUDE","LONGITUDE"};
        int[] to = new int[] {R.id.textViewDescList, R.id.textViewLatiList, R.id.textViewLongiList};

        entryAdapter = new SimpleCursorAdapter(
                LocList.this, R.layout.listview_item,null, from,to, 0);

        setListAdapter(entryAdapter);
    }

    @Override
    public void onResume(){
        //For some reason doesn't work in the onCreate
        super.onResume();
        if(editPermission) {
            DatabaseConnector dbConnector = new DatabaseConnector(LocList.this);
            dbConnector.open();
            Cursor allEntries = dbConnector.getAllEntries();
            entryAdapter.changeCursor(allEntries);
            dbConnector.close();
        }
        else{
            DatabaseConnector dbConnector = new DatabaseConnector(LocList.this);
            dbConnector.open();
            Cursor requiredEntries = dbConnector.getSearchEntries(searchText);
            entryAdapter.changeCursor(requiredEntries);
            dbConnector.close();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Lab-10", "On item Click has been called");
        if(editPermission){
            TextView c = (TextView) view.findViewById(R.id.textViewDescList);
            String msg = c.getText().toString();
            AlertDialog.Builder builder = new AlertDialog.Builder(LocList.this);
            builder.setTitle("Delete");
            builder.setMessage("Do you want to delete \""+ msg + "\" ?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseConnector dbConnector = new DatabaseConnector(LocList.this);
                    dbConnector.open();
                    dbConnector.deleteContact(id);
                    dbConnector.close();
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            DatabaseConnector dbConnector = new DatabaseConnector(LocList.this);
                            dbConnector.open();
                            Cursor allEntries = dbConnector.getAllEntries();
                            entryAdapter.changeCursor(allEntries);
                            dbConnector.close();
                            entryAdapter.notifyDataSetChanged();
                        }
                    });
                }
            });
            builder.setNegativeButton("Cancel",null);
            builder.show();

        }

        else{

            Toast.makeText(this, "Deletion Is Not Allowed", Toast.LENGTH_SHORT).show();
        }

    }
}