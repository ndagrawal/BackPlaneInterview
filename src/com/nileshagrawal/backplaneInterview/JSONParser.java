package com.nileshagrawal.backplaneInterview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

public class JSONParser {


	public JSONObject json1;


	DefaultHttpClient  httpClient;

	// constructor
	public JSONParser() {
		httpClient = new DefaultHttpClient();
		json1 = null;
	}

	public JSONObject getJSONFromUrl(String url)throws HttpException,IOException,JSONException {

		// Making HTTP request
		// defaultHttpClient	
		HttpGet get = new HttpGet(url);
		HttpResponse r = httpClient.execute(get);
		int status = r.getStatusLine().getStatusCode();
		if(status == 200){
			HttpEntity e = r.getEntity();
			String data = EntityUtils.toString(e);
			JSONObject Customer= (JSONObject) new JSONTokener(data).nextValue();
			System.out.println(""+Customer.getString("user"));
			return Customer;
		}else{
			return null;
		}

	}

	public JSONArray getJSONArrayFromUrl(String url)throws HttpException,IOException,JSONException  {
		// TODO Auto-generated method stub

		HttpGet get = new HttpGet(url);
		HttpResponse r = httpClient.execute(get);
		int status = r.getStatusLine().getStatusCode();
		if(status == 200){
			HttpEntity e = r.getEntity();
			String data = EntityUtils.toString(e);
			JSONArray items=  new JSONArray(data);
			return items;
		}else{
			return null;
		}
	}
}
