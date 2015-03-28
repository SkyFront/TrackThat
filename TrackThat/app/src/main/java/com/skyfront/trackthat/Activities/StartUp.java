package com.skyfront.trackthat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.skyfront.trackthat.R;


public class StartUp extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_up, menu);
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

    public void newTrack(View view){
//        Toast.makeText(this, "Create new list", Toast.LENGTH_LONG).show();
        LayoutInflater inflate = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflate.inflate(R.layout.new_list_popup,null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();

        Button cancel = (Button)popupView.findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button newList = (Button)popupView.findViewById(R.id.createButton);
        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText listTitle = (EditText)popupView.findViewById(R.id.listName);
                String listName = listTitle.getText().toString().trim();

                if(!listName.isEmpty()){
                    //TODO save new list
                    //TODO jump to new Helpers.Counter activity
                    Intent newCounter = new Intent(popupView.getContext(),NewCounter.class);
                    newCounter.putExtra("countTitle",listName );
                    startActivity(newCounter);
//                    Toast.makeText(popupView.getContext(), listName, Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(popupView.getContext(),"Please enter a name for your list", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void viewTrack(View view){
        Toast.makeText(this, "View lists if available", Toast.LENGTH_LONG).show();
    }
}
