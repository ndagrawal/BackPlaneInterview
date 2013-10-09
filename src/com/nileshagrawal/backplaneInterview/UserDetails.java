package com.nileshagrawal.backplaneInterview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nileshagrawal.backplaneInterview.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class UserDetails extends ListActivity {

	// url to make request
	private static String url = "http://sampleapi.bplane.com/overview/1/";
	
	// JSON Names	
	private static final String TAG_USERS = "user";
	private static final String TAG_USER_NAME = "name";
	private static final String TAG_USER_BALANCE = "balance";

		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Hashmap for ListView
		ArrayList<HashMap<String,String>> userList = new ArrayList<HashMap<String,String>>();
		
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();
		
		// getting JSON object from URL
		JSONObject json = null;
		
		try {
			json = jParser.getJSONFromUrl(url);
		} catch (HttpException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		try {
			// Getting Strings out of Object
			// as we are having only one User we are not looping through it.
			JSONObject user = json.getJSONObject(TAG_USERS);
			String userName = user.getString(TAG_USER_NAME);
			String userBalance = user.getString(TAG_USER_BALANCE);
			
			HashMap<String,String> map2 = new HashMap<String,String>();
			map2.put(TAG_USER_NAME, userName);
			map2.put(TAG_USER_BALANCE, userBalance);
			userList.add(map2);

			// looping through All Contacts	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		/**
		 * Updating parsed JSON data into ListView
		 * */
		
		ListAdapter adapter = new SimpleAdapter(this, userList,
				R.layout.list_item_another,
				new String[] { TAG_USER_NAME, TAG_USER_BALANCE }, new int[] {
						R.id.tvUserName, R.id.tvUserBalance });
		

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		// Launching new screen on Selecting Single ListItem
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent in = new Intent(UserDetails.this, ReceiptsDetails.class);
				startActivity(in);

			}
		});
		
		

	}

}