package com.skyfront.trackthat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfront.trackthat.R;

import Helpers.DatabaseAdaptor;

public class NewCounter extends ActionBarActivity {
    TextView count, title;
    Button increment;
    DatabaseAdaptor dbAdaptor;
    String countTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_counter);

        //grab title
        Intent fromIntent = getIntent();
        countTitle = fromIntent.getStringExtra("countTitle");
        title = (TextView)findViewById(R.id.newTrackTitle);
        title.setText(countTitle);

        //create db
        dbAdaptor = new DatabaseAdaptor(this);

        //insert row
        if(!countTitle.isEmpty()) {
            long id = dbAdaptor.insertData(countTitle, 0);

            if(id>0){
                Toast.makeText(this, "Insert worked!", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Insert unsuccessful", Toast.LENGTH_SHORT).show();
        }

        //textview focus
       count = (TextView)findViewById(R.id.countNumber);
        //button focus
       increment = (Button)findViewById(R.id.incrementButton);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_counter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void increment(View view){
        int currentCount = Integer.parseInt(count.getText().toString());
        currentCount++;
        count.setText(currentCount);

        //update database
        dbAdaptor.updateQualtity(countTitle, currentCount);
    }
}
