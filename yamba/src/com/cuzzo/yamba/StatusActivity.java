package com.cuzzo.yamba;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StatusActivity extends Activity implements OnClickListener, TextWatcher, OnSharedPreferenceChangeListener {
	
	private static final String TAG = "StatusActivity";
	EditText editText;
	Button updateButton;
	Twitter twitter;
	TextView textCount;
	SharedPreferences prefs;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        
        //find the views
        editText = (EditText) findViewById(R.id.editText);
        updateButton = (Button) findViewById(R.id.buttonUpdate);
        
        //setup the text counter
        textCount = (TextView) findViewById(R.id.textCount);
        textCount.setText(Integer.toString(140));
        textCount.setTextColor(Color.GREEN);
        textCount.addTextChangedListener(this);
        
        updateButton.setOnClickListener(this);
        
        twitter = new Twitter("student", "password");
        twitter.setAPIRootUrl("http://yamba.marakana.com/api");
        
        //setup preferences
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }
    
    class PostToTwitter extends AsyncTask<String, Integer, String> {
    	
    	@Override
    	protected String doInBackground(String... statuses) {
    		try {
    			Twitter.Status status = twitter.updateStatus(statuses[0]);
    			return status.text;
    		} catch (TwitterException e) {
    			Log.e(TAG, e.toString());
    			e.printStackTrace();
    			return "Failed to post";
    		}
    	}
    	
    	
    	@Override
    	protected void onProgressUpdate(Integer... values) {
    		super.onProgressUpdate(values);
    	}
    	
    	
    	@Override
    	protected void onPostExecute(String result) {
    		String status = editText.getText().toString();
    		new PostToTwitter().execute(status);
    		Log.d(TAG, "onClicked");
    	}
    }
    
    public void onClick(View v) {
    	String status = editText.getText().toString();
    	try{
    		 getTwitter().setStatus(status);
    	} catch (TwitterException e) {
    		Log.d(TAG, "twitter setstatus failed: " + e);
    	}
    	Log.d(TAG, "onClicked");
    }
    
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
    	//invalidate twitter object
    	twitter = null;
    }
    
    //textwatcher biz
    public void afterTextChanged(Editable statusText) {
    	int count = 140 - statusText.length();
    	textCount.setText(Integer.toString(count));
    	textCount.setTextColor(Color.GREEN);
    	if(count < 10) {
    		textCount.setTextColor(Color.YELLOW);
    	}
    	if(count < 0) {
    		textCount.setTextColor(Color.RED);
    	}
    }
    
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    	
    }
    
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    	
    }
    
    private Twitter getTwitter() {
    	if(twitter == null) {
    		String username, password, apiRoot;
    		username = prefs.getString("username", "");
    		password = prefs.getString("password", "");
    		apiRoot = prefs.getString("apiRoot", "http://yamba.marakana.com/api");
    		
    		twitter = new Twitter(username, password);
    		twitter.setAPIRootUrl(apiRoot);
    	}
    	
    	return twitter;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.itemPrefs:
    		startActivity(new Intent(this, PrefsActivity.class));
    		break;
    	}
    	return true;
    }
}