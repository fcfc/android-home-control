package com.ice.homecontrol;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class GoToURL extends Activity {
    String device = "";
    String pin = "";
    String location = "";
    String command = "";
    String baseURL = "http://www.crimi.com/IPL/sendCommand.php?location=";

	@Override
	public void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras(); 
		if(extras !=null)
		{
		    device  = extras.getString("device");
		    pin  = extras.getString("pin");
		    command  = extras.getString("command");

		}

		String inURL = baseURL + location + "&device=" + device +"&pin=" + pin + "&command=" + command;

        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );
        startActivity( browse );
        
    }
	
}
