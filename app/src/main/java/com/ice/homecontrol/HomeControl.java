package com.ice.homecontrol;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabContentFactory;


public class HomeControl extends Activity {

	private static final String LIST_TAB_TAG = "List";
	private static final String MAP_TAB_TAG = "Map";
	private static final String MAP_NEU_TAG = "Neutral";
	private static final String MAP_SYN_TAG = "Synthetic";

	   TabHost tabHost;
	private ListView listView;	
	private ListView listView2;
	private ListView listView3;
	private ListView listView4;
	private ListView listView5;
	private TextView category;


	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    Resources res = getResources(); // Resource object to get Drawables
		category = (TextView) findViewById(R.id.category);

	    tabHost = (TabHost) findViewById(android.R.id.tabhost);
	    tabHost.setup();

	    // setup list view
	    listView = (ListView) findViewById(R.id.list);
	    listView.setEmptyView((TextView) findViewById(R.id.empty));
	    
        List<MyListItem> myList = new ArrayList<>();
        TypedArray security = res.obtainTypedArray(R.array.security);

        // populate list
		for (int i=0; i<8; i++)    {
			myList.add(new MyListItem(R.drawable.ic_menu_camera, new String(security.getString(i)), "", "", "", ""));
		}

        listView.setAdapter(new CustomAdapter2(this, myList));
        listView.setOnItemClickListener(new OnItemClickListener() {
    
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                CharSequence testString = ((TextView) view).getText();

                Intent i = new Intent(HomeControl.this, HomeGraph.class);
                category.setText("Security");
                i.putExtra("type", testString);
                startActivity(i);

            }
        });

        listView2 = (ListView) findViewById(R.id.list2);
	    listView2.setEmptyView((TextView) findViewById(R.id.empty));
        List<MyListItem> myList2 = new ArrayList<MyListItem>();
	    
        TypedArray lights = res.obtainTypedArray(R.array.cameras);

        // populate list

        for (int i=0; i<8; i++)    {
            myList2.add(new MyListItem(R.drawable.ic_tab_artists_grey, new String(lights.getString(i)), "", "", "", ""));
        }
        listView2.setAdapter(new CustomAdapter2(this, myList2));
        listView2.setVisibility(View.INVISIBLE);
        listView2.setOnItemClickListener(new OnItemClickListener() {
            
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                    	    
                // programmatically switch tabs to the map view
                CharSequence testString = ((TextView) view).getText();
                Intent i = new Intent(HomeControl.this, HomeRemote.class);
                i.putExtra("type", testString);
                startActivity(i);
            }
        });
        
        listView3 = (ListView) findViewById(R.id.list3);
	    listView3.setEmptyView((TextView) findViewById(R.id.empty));
	    
        List<MyListItem> myList3 = new ArrayList<MyListItem>();
        TypedArray thermostat = res.obtainTypedArray(R.array.lights);

        // populate list

        for (int i=0; i<8; i++)    {
            myList3.add(new MyListItem(R.drawable.ic_tab_artists_grey, new String(thermostat.getString(i)), "", "", "", ""));
        }


        listView3.setAdapter(new CustomAdapter(this, myList3));
        listView3.setVisibility(View.INVISIBLE);
        listView3.setOnItemClickListener(new OnItemClickListener() {
            
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                CharSequence testString = ((TextView) view).getText();
                Intent i = new Intent(HomeControl.this, HomeGraph.class);
                i.putExtra("type", testString);
                startActivity(i);

            }
        });
        
        listView4 = (ListView) findViewById(R.id.list4);
	    listView4.setEmptyView((TextView) findViewById(R.id.empty));

		List<MyListItem> myList4 = new ArrayList<MyListItem>();
		TypedArray synthetic = res.obtainTypedArray(R.array.thermostat);

        for (int i=0; i<8; i++)    {
			myList4.add(new MyListItem(R.drawable.ic_tab_artists_grey, new String(synthetic.getString(i)), "", "", "", ""));

	   }

        listView4.setAdapter(new CustomAdapter2(this, myList4));
        listView4.setVisibility(View.INVISIBLE);
        listView4.setOnItemClickListener(new OnItemClickListener() {
            
           public void onItemClick(AdapterView parent, View view, int position, long id) {
              // programmatically switch tabs to the map view
        	  // Object o = this.getItem(position);
              CharSequence testString = ((TextView) view).getText();
              Intent i = new Intent(HomeControl.this, HomeGraph.class);
              i.putExtra("type", testString);
              startActivity(i);	
            }
        });


		listView5 = (ListView) findViewById(R.id.list5);
		listView5.setEmptyView((TextView) findViewById(R.id.empty));

		List<String> point5List = new ArrayList<String>();
		TypedArray locks = res.obtainTypedArray(R.array.locks);

		for (int i=0; i<8; i++)    {
			point5List.add(new String(locks.getString(i)));

		}

		listView5.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, point5List));
		listView5.setVisibility(View.INVISIBLE);
		listView5.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView parent, View view, int position, long id) {
				// programmatically switch tabs to the map view
				// Object o = this.getItem(position);
				CharSequence testString = ((TextView) view).getText();
				Intent i = new Intent(HomeControl.this, HomeGraph.class);
				i.putExtra("type", testString);
				startActivity(i);
			}
		});

        // add views to tab host
	    tabHost.addTab(tabHost.newTabSpec(LIST_TAB_TAG).setIndicator("Security",
	            res.getDrawable(R.drawable.ic_tab_bull)).setContent(new TabContentFactory() {
	                public View createTabContent(String arg0) {
	                    return listView;
	                }
	            }));
	    tabHost.addTab(tabHost.newTabSpec(MAP_TAB_TAG).setIndicator("Cameras",
	            		res.getDrawable(R.drawable.ic_tab_bear)).setContent(new TabContentFactory() {
	                public View createTabContent(String arg0) {
	                    return listView2;
	                }
	            }));
	    tabHost.addTab(tabHost.newTabSpec(MAP_NEU_TAG).setIndicator("Lights",
	                      res.getDrawable(R.drawable.ic_tab_albums)).setContent(new TabContentFactory() {
	                public View createTabContent(String arg0) {
	                    return listView3;
	                }
	            }));
	    tabHost.addTab(tabHost.newTabSpec(MAP_SYN_TAG).setIndicator("Thermostats",
	                      res.getDrawable(R.drawable.ic_tab_albums)).setContent(new TabContentFactory() {
	                public View createTabContent(String arg0) {
	                    return listView4;
	                }
	            }));

		tabHost.addTab(tabHost.newTabSpec(MAP_SYN_TAG).setIndicator("Locks",
				res.getDrawable(R.drawable.ic_tab_albums)).setContent(new TabContentFactory() {
			public View createTabContent(String arg0) {
				return listView5;
			}
		}));


		tabHost.setCurrentTab(4);
		tabHost.setCurrentTab(3);
		tabHost.setCurrentTab(2);
		tabHost.setCurrentTab(1);
		tabHost.setCurrentTab(0);

	}



	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;

		}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
	switch (item.getItemId()) {
	   case 1: 
           break;
	   case 2: /* .. start help activity .. */ break;
	   case 3: /* .. start feedback activity .. */ break;
	   case 4: /* .. start about activity .. */ break;
	}
	return false;
	}
	


}


