package com.mixsee;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FragmentStackSupport extends FragmentActivity {
	int mStackLevel = 1;
	private static final String TAG = "FragmentStackSupport";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_stack);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level", mStackLevel);
    }





    public static class CountingFragment extends Fragment {
        int mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static CountingFragment newInstance(int num) {
            CountingFragment f = new CountingFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           // mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @SuppressWarnings("deprecation")
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.webview_layout, container, false);
            WebView wv = (WebView) v.findViewById(R.id.mixsee_webview);
            wv.setWebViewClient(new MixseeWebViewClient());
            wv.getSettings().setJavaScriptEnabled(true);
            wv.loadUrl("http://mobile.mixsee.com");
            wv.setHorizontalScrollBarEnabled(false);
            
            //prevent horizontal scrolling 
            wv.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
            return v;
        }
        
        
        private class MixseeWebViewClient extends WebViewClient {
// 			droid bug        	
        	@Override
        	public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		Log.i(TAG, "lulzurl: " + url.toString());
        		//view.loadUrl(url);
        		return true;
        	}
        	
        	@Override
        	public void onPageStarted(WebView webView, String url, Bitmap favicon) {


        	}
        	
//        	@Override
//        	public void onLoadResource (WebView view, String url)
//        	{
//        		Log.i(TAG, "lulzurl2: " + url.toString());
//        	}
        	
        	@Override
        	public void onPageFinished(WebView webView, String url) 
        	{
        		//use this to listen for url pressing in the webview for now
        		Log.i(TAG, "onpagefinished: " + url.toString());
        		
        	}
        	
        }
    }
}
