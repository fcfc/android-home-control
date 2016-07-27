package com.ice.homecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class HomeRemote extends Activity {

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.remote);
        }
        
        // onclick routine for button
        // set extras, goot GoToURL
        public void onItemClick(AdapterView parent, View view, int position, long id) {
    	    
//    		Object o = this.getItem(position);
            CharSequence testString = ((TextView) view).getText();
            Intent i = new Intent(HomeRemote.this, GoToURL.class);
    	    i.putExtra("device", testString);
    	    i.putExtra("pin", testString);
    	    i.putExtra("command", testString);
            startActivity(i);

    }

}
       