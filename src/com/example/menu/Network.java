package com.example.menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Network extends Activity {
	private static final String DEBUG_TAG = null;

	private ProgressBar progressBar=null;
	private NetworkWebFragment web = null;
	private DownloadWebpageText task=null;
	private EditText	postParams=null;
	private WebView 	webView=null;


	
	boolean conectado = false;

	private PostQuery taskPost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_network);

		FragmentManager fm = getFragmentManager();
		web = (NetworkWebFragment) fm
				.findFragmentById(R.id.layout_fragment_network_web);

		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			conectado = true;
			Toast.makeText(this, "Conectado", Toast.LENGTH_LONG).show();// fetch
																		// data
																		// }
		} else { // display error }
			conectado = false;
			Toast.makeText(this, "No Conectado", Toast.LENGTH_LONG).show();
		}
		
		
		webView = (WebView) findViewById(R.id.network_web_webView);
		//WebSettings webSettings = webView.getSettings();
		//webSettings.setJavaScriptEnabled(true);
		
		final NetworkURLFragment uri = (NetworkURLFragment) fm
				.findFragmentById(R.id.layout_fragment_network_URL);

		
		
		postParams = (EditText)findViewById(R.id.network_navigation_edittext_postparams);
		
		progressBar = (ProgressBar) findViewById(R.id.network_url_downloadprogress);
		
		Button go = (Button)findViewById(R.id.network_url_browse);
		go.setOnClickListener(new OnClickListener(){
			public void onClick(View view)
			{
				if (uri != null) {	
					webView.setWebViewClient(new WebViewClient() {
						   public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
						     Toast.makeText(Network.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
						   }
						 });

					webView.loadUrl(uri.getURLString());
				}
			}
		});
	}

	// Uses AsyncTask to create a task away from the main UI thread. This task
	// takes a
	// URL string and uses it to create an HttpUrlConnection. Once the
	// connection
	// has been established, the AsyncTask downloads the contents of the webpage
	// as
	// an InputStream. Finally, the InputStream is converted into a string,
	// which is
	// displayed in the UI by the AsyncTask's onPostExecute method.
	private class DownloadWebpageText extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... urls) {

			// params comes from the execute() call: params[0] is the url.
			try {
				String data=downloadUrl((String) urls[0]);
				
				
				return data;
			} catch (IOException e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}

		// onPostExecute displays the results of the AsyncTask.
		protected void onPostExecute(final String result) {
			web.setText(result);

		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			progressBar.setProgress(values[0]);
			progressBar.postInvalidate();
			super.onProgressUpdate(values);
		}
		

	}
	
	
	private class PostQuery extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... urls) {

			// params comes from the execute() call: params[0] is the url.
			try {
				if(urls.length>=2)
					
				return downloadUrlByPost((String) urls[0],urls[1]);
				else
					return null;
			} catch (IOException e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}

		// onPostExecute displays the results of the AsyncTask.
		protected void onPostExecute(final String result) {
			web.setText(result);

		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			progressBar.setProgress(values[0]);
			progressBar.postInvalidate();
			super.onProgressUpdate(values);
		}
		

	}
	

	private class SocketConnection extends AsyncTask<URL, String, String> {
		@Override
		protected String doInBackground(URL... urls) {

			// params comes from the execute() call: params[0] is the url.

			return conectaSocket(urls[0]);

		}

		// onPostExecute displays the results of the AsyncTask.

		protected void onPostExecute(final String result) {
			web.setText(result);

		}

	}

	/**
	 * Se ejecuta al pulsar el botón conectar con get
	 * 
	 * @param view
	 */
	public void onConnectHTTP(View view) {
		FragmentManager fm = getFragmentManager();

		NetworkURLFragment uri = (NetworkURLFragment) fm
				.findFragmentById(R.id.layout_fragment_network_URL);

		if (uri != null) {

			progressBar.setProgress(0);
			web.setText("");
			task = new DownloadWebpageText();
			task.execute(uri.getURLString());

		}

	}
	
	/**
	 * Se ejecuta al pulsar el botón conectar con get
	 * 
	 * @param view
	 */
	public void onConnectHTTPPost(View view) {
		FragmentManager fm = getFragmentManager();

		NetworkURLFragment uri = (NetworkURLFragment) fm
				.findFragmentById(R.id.layout_fragment_network_URL);

		if (uri != null) {

			progressBar.setProgress(0);
			web.setText("");
			taskPost = new PostQuery();
			taskPost.execute(uri.getURLString(),postParams.getEditableText().toString());

		}

	}

	/**
	 * Se ejecuta al pulsar el botón conectar
	 * 
	 * @param view
	 */
	public void onConnectSocket(View view) {
		FragmentManager fm = getFragmentManager();
		NetworkURLFragment urifragment = (NetworkURLFragment) fm
				.findFragmentById(R.id.layout_fragment_network_URL);

		if (urifragment != null) {

			web.setText("");
			URL url = urifragment.getURI();

			SocketConnection task = new SocketConnection();
			task.execute(url);

		}

	}
	
	/**
	 * Se ejecuta al pulsar el botón conectar
	 * 
	 * @param view
	 */
	public void onNetworkService(View view) {
		FragmentManager fm = getFragmentManager();
		NetworkURLFragment urifragment = (NetworkURLFragment) fm
				.findFragmentById(R.id.layout_fragment_network_URL);

		if (urifragment != null) {

			//web.setText("");
			//URL url = urifragment.getURI();

			
			Intent intent = new Intent(this, Download.class);
			intent.putExtra(Download.PARAMETER_URL, urifragment.getURI().toString());
			startService(intent);

		}

	}

	public String conectaSocket(URL url) {

		if (url != null) {
			String contentAsString="";
			Socket s = new Socket();
			InputStream is;
			DataOutputStream dos;

			try {
				int port=url.getPort();
				s = new Socket(url.getHost(), port);
				
				
				is = s.getInputStream();
				dos = new DataOutputStream(s.getOutputStream());

				dos.writeUTF("get /index.html HTTP/1.1\r\nHOST=www.ujaen.es\r\n");
				dos.flush();
				
				// Convert the InputStream into a string
				
				contentAsString=contentAsString+readIt(is,100);
				
				dos.close();
				is.close();
				s.close();
				return contentAsString;
			} catch (IOException e) {
				return e.getMessage();
				
			} catch (IllegalArgumentException e) {
				return e.getMessage();
				
			}
		}
		return "Conexión fallida";

	}

	// Given a URL, establishes an HttpUrlConnection and retrieves
	// the web page content as a InputStream, which it returns as
	// a string.
	private String downloadUrl(String myurl) throws IOException {
		InputStream is = null;
		String result = "";
		
		HttpURLConnection conn = null;
		try {
			String contentAsString="";
			String tempString="";
			URL url = new URL(myurl);
			System.out.println("Abriendo conexión: " + url.getHost()
					+ " puerto=" + url.getPort());
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			// Starts the query
			conn.connect();
			final int response = conn.getResponseCode();
			final int contentLength = conn.getHeaderFieldInt("Content-length",1000);
			String mimeType=conn.getHeaderField("Content-Type");
			String encoding=mimeType.substring(mimeType.indexOf(";"));
			progressBar.setMax(contentLength);
			
			Log.d(DEBUG_TAG, "The response is: " + response);
			is = conn.getInputStream();

			BufferedReader br = new BufferedReader( new InputStreamReader(is, "UTF-8"));
			
			while((tempString=br.readLine())!=null)
			{
				 contentAsString = contentAsString + tempString;
				 task.onProgressUpdate(contentAsString.length());
			}
			
			webView.loadData(contentAsString, mimeType, encoding);
			//Convert the InputStream into a string
			
			return contentAsString;
		} catch (IOException e) {
			result = "Excepción: " + e.getMessage();
			System.out.println(result);

			// Makes sure that the InputStream is closed after the app is
			// finished using it.
		} finally {
			if (is != null) {
				is.close();
				conn.disconnect();
			}
		}
		return result;
	}

	

	// Given a URL, establishes an HttpUrlConnection and retrieves
	// the web page content as a InputStream, which it returns as
	// a string.
	private String downloadUrlByPost(String myurl,String postparam) throws IOException {
		InputStream is = null;
		String result = "";
		
		HttpURLConnection conn = null;
		try {
			String contentAsString="";
			String tempString="";
			URL url = new URL(myurl);
			System.out.println("Abriendo conexión: " + url.getHost()
					+ " puerto=" + url.getPort());
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod("POST");
			
			conn.setDoInput(true);
			conn.setDoOutput(true);

		      //Send request
			OutputStream os = conn.getOutputStream();
			BufferedWriter wr = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			
		      wr.write(postparam);
		      wr.flush();
		      wr.close ();
			// Starts the query
			conn.connect();
			final int response = conn.getResponseCode();
			final int contentLength = conn.getHeaderFieldInt("Content-length",1000);
			progressBar.setMax(contentLength);
			
			Log.d(DEBUG_TAG, "The response is: " + response);
			is = conn.getInputStream();

			BufferedReader br = new BufferedReader( new InputStreamReader(is, "UTF-8"));
			
			while((tempString=br.readLine())!=null)
			{
				 contentAsString = contentAsString + tempString;
				 taskPost.onProgressUpdate(contentAsString.length());
			}
			
			//Convert the InputStream into a string
			// contentAsString = readIt(is, len);
			return contentAsString;
		} catch (IOException e) {
			result = "Excepción: " + e.getMessage();
			System.out.println(result);

			// Makes sure that the InputStream is closed after the app is
			// finished using it.
		} finally {
			if (is != null) {
				is.close();
				conn.disconnect();
			}
		}
		return result;
	}
	// Reads an InputStream and converts it to a String.
	public String readIt(InputStream stream, int len) throws IOException,
			UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
		
		reader.read(buffer);
		return new String(buffer);
	}
}
