package com.example.app_5gpacalculatorusingspinners;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent>activityAboutLauncher;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate Converts the XML into Java Objects
        //Return True (Here, super.onCreateOptionsMenu(menu) returns true after inflating) displays the menu
        getMenuInflater().inflate(R.menu.app_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityAboutLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                            build.setTitle("About");
                            build.setMessage("This GPA Calculator calculates your GPA upto 5 courses");
                            build.create().show();

                        }
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Return True means that the event has been processed and no further processing is required
        switch (item.getItemId()){
            case R.id.menu_about:
                Intent intent = new Intent(this, About_page.class);
                activityAboutLauncher.launch(intent);
                return true;
            case R.id.menu_help:
                Toast.makeText(this, "Help has not been implemented yet", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}