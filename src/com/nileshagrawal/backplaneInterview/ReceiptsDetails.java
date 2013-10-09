package com.nileshagrawal.backplaneInterview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nileshagrawal.backplaneInterview.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ReceiptsDetails  extends ListActivity {
	
	// url to make request
	private static String url = "http://sampleapi.bplane.com/overview/1/";
	
	// JSON node keys
	private static final String TAG_RECEIPTS = "receipts";
	private static final String TAG_RECEIPT_RESTAURANT = "restaurant";
	private static final String TAG_RECEIPT_DATE = "date";
	private static final String TAG_RECEIPT_TOTAL = "total";
	private static final String TAG_RECEIPT_ID = "id";
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayList<HashMap<String, String>> receiptList = new ArrayList<HashMap<String, String>>();

     
        JSONParser jParser = new JSONParser();
    
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
     		try{
     		JSONArray receipt = json.getJSONArray(TAG_RECEIPTS);
			for(int i=0;i<receipt.length();i++){
				JSONObject c = receipt.getJSONObject(i);
				String receiptRestaurant = c.getString(TAG_RECEIPT_RESTAURANT);
				String receiptDate = c.getString(TAG_RECEIPT_DATE);
				String receiptId = c.getString(TAG_RECEIPT_ID);
				String receiptTotal = c.getString(TAG_RECEIPT_TOTAL);
				
				HashMap<String, String> map1 = new HashMap<String, String>();
				
				map1.put(TAG_RECEIPT_ID, receiptId);
				map1.put(TAG_RECEIPT_DATE, receiptDate);
				map1.put(TAG_RECEIPT_RESTAURANT, receiptRestaurant);
				map1.put(TAG_RECEIPT_TOTAL, receiptTotal);
				receiptList.add(map1);
			}
     		}catch(JSONException e){
     			
     		}
     		
        // Displaying all values on the screen
     		ListAdapter adapter2 = new SimpleAdapter(this, receiptList,
    				R.layout.list_item_restaurant,
    				new String[] { TAG_RECEIPT_RESTAURANT, TAG_RECEIPT_ID, TAG_RECEIPT_TOTAL }, new int[] {
    						R.id.tvRestaurantName, R.id.tvReceiptId, R.id.tvReceiptTotal});
  
     		setListAdapter(adapter2);   		
     		ListView lv = getListView();

    		// Launching new screen on Selecting Single ListItem
    		lv.setOnItemClickListener(new OnItemClickListener() {

    			@Override
    			public void onItemClick(AdapterView<?> parent, View view,
    					int position, long id) {
    				// to get the details only first item
    				if(id == 0){
    				Intent in1 = new Intent(ReceiptsDetails.this,ItemsDetails.class);
    				startActivity(in1);
    				}
    			}
    		});
    		
    	}

        
        
    }

