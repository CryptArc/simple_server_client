package com.dima.quoteclientapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class AsyncHttpTask extends AsyncTask<String, Void, String> {

	private AsyncHTTPClient m_httpClient;

	public AsyncHttpTask(AsyncHTTPClient httpClient) {

		this.m_httpClient = httpClient;
	}

	@Override
	protected String doInBackground(String... arg0) {
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// make the http request
			HttpResponse httpResponse = httpclient.execute(m_httpClient
					.getHttpRequestMethod());

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			System.out.println("InputStream" + e.getLocalizedMessage());
		}

		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		m_httpClient.onResponse(result);
	}

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}
}
