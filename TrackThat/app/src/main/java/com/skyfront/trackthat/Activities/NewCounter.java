package com.skyfront.trackthat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfront.trackthat.R;

import Helpers.DatabaseAdaptor;

public class NewCounter extends ActionBarActivity {
    TextView data,query;
    EditText search;
    DatabaseAdaptor dbAdaptor;
    String countTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_counter);

        //grab title
        Intent fromIntent = getIntent();
        countTitle = fromIntent.getStringExtra("countTitle");

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
        data = (TextView)findViewById(R.id.dataText);
        query = (TextView)findViewById(R.id.query);
        //edittext focus
        search = (EditText)findViewById(R.id.nameInQuestion);

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

    public void displayData(View view){
        //show data
        String dbData = dbAdaptor.getAllData();
        data.setText(dbData);
    }

    public void searchData(View view){
        String name=search.getText().toString().trim();
        String result = dbAdaptor.getQuantity(name);

        query.setText(result);

    }
}
