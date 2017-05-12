package com.example.mario.ongproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by mario on 05/05/17.
 */

public class DonateFragment  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.donate_view, container, false);


        // Play Video
        WebView mWebView = (WebView) v.findViewById(R.id.donateWebView);
        mWebView.loadUrl("https://mario-apra.tk/donation");

        // Enable Javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        // Change scale from webpage to fit the boy.
        mWebView.setInitialScale(90);

        return v;
    }
}
