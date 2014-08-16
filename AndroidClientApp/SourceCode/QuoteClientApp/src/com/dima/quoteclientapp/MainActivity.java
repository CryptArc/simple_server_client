package com.dima.quoteclientapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView m_quotLV; //listview to hold display quotations
	List<String> m_quotList = new ArrayList<String>(); //list to hold the actual quotes
	ArrayAdapter<String> m_Adapter; //adapter to bind between list and listview
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.setResLayout();
	}

	//initialize listview and adapter
	private void setResLayout() {
		m_quotLV = (ListView) findViewById(R.id.quoteView);
		// Create The Adapter with passing ArrayList as 3rd parameter
		m_Adapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                m_quotList );
		m_quotLV.setAdapter(m_Adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//executed upon button click.
	//creates anonymous AsyncHTTPClient,
	//handles response from server
	public void getQuote(View view) {
		System.out.println("Hello world...");
		new AsyncHTTPClient() {

			@Override
			public HttpUriRequest getHttpRequestMethod() {
				return new HttpGet("http://dimanememets1986-serverapp.nodejitsu.com:80/quote");
			}

			@Override
			public void onResponse(String result) {
				m_quotList.add(result); //add server response to LV
				m_Adapter.notifyDataSetChanged(); //notify UI
			}
		}.execute();
	}
	
	

}
