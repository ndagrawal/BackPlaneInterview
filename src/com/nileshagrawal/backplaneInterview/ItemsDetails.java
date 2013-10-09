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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class ItemsDetails extends ListActivity{
	
		// url to make request
		private static String url = "http://sampleapi.bplane.com/receipt/1/";
		
		// JSON node keys
		private static final String TAG_COUNT = "count";
		private static final String TAG_ITEM_NAME = "name";
		private static final String TAG_TOTAL_PRICE = "total_price";
		private static final String TAG_RECEIPT_ITEM_PRICE= "item_price";
		
		JSONArray itemList=null;
		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();

	     
	        JSONParser jParser = new JSONParser();
	        // Get JSON values from previous intent
	     // getting JSON string from URL
	     		JSONArray json = null;
	     		try {
	     			json = jParser.getJSONArrayFromUrl(url);
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
	     		try{
	     		JSONArray items = json;
	     		
				for(int i=0;i<items.length();i++){
					JSONObject c = items.getJSONObject(i);
					String itemCount = c.getString(TAG_COUNT);
					String itemName = c.getString(TAG_ITEM_NAME);
					String itemTotalPrice= c.getString(TAG_TOTAL_PRICE);
					String itemPrice = c.getString(TAG_RECEIPT_ITEM_PRICE);
					
					HashMap<String, String> map3 = new HashMap<String, String>();
					
					map3.put(TAG_COUNT, itemCount);
					map3.put(TAG_ITEM_NAME, itemName);
					map3.put(TAG_TOTAL_PRICE, itemTotalPrice);
					map3.put(TAG_RECEIPT_ITEM_PRICE, itemPrice);
					itemList.add(map3);
				}
	     		}catch(JSONException e){
	     			
	     		}
	        // Displaying all values on the screen
	     		ListAdapter adapter2 = new SimpleAdapter(this, itemList,
	    				R.layout.list_item_items,
	    				new String[] { TAG_COUNT, TAG_ITEM_NAME, TAG_TOTAL_PRICE ,TAG_RECEIPT_ITEM_PRICE}, new int[] {
	    						R.id.tvItemCount, R.id.tvItemName, R.id.tvItemTotalPrice,R.id.tvItemPrice});
	     		
	     		setListAdapter(adapter2);
	     		
	     		ListView lv = getListView();

	    		// Launching new screen on Selecting Single ListItem
	    		lv.setOnItemClickListener(new OnItemClickListener() {

	    			@Override
	    			public void onItemClick(AdapterView<?> parent, View view,
	    					int position, long id) {
	    			
	    				
	    				
	    			}
	    		});
	    		
	    		

	    	}

	        
}
