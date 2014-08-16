package com.dima.quoteclientapp;

import org.apache.http.client.methods.HttpUriRequest;

import com.dima.quoteclientapp.AsyncHttpTask;

public abstract class AsyncHTTPClient {

	public abstract HttpUriRequest getHttpRequestMethod();
	
	public abstract void onResponse(String result);
	
	public void execute(){
		new AsyncHttpTask(this).execute();
	}	
}