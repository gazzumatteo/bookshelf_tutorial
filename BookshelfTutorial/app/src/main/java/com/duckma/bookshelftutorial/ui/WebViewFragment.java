package com.duckma.bookshelftutorial.ui;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.duckma.bookshelftutorial.R;

/**
 * Copyright © 2015 DuckMa S.r.l. - http://duckma.com
 * <p/>
 * Created by Matteo Gazzurelli on 19/11/14.
 *
 * WebView: http://developer.android.com/guide/webapps/webview.html
 */
public class WebViewFragment extends Fragment {
    WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);

        //caricamento mWebView
        mWebView = (WebView) view.findViewById(R.id.wvBookWeb);

        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void loadUrl(String url) {
        //caricamento url
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(url);
    }

    public void clearWebView() {
        if (mWebView != null) {
            mWebView.clearCache(true);
            mWebView.getSettings().setAppCacheEnabled(false);
            mWebView.stopLoading();
            mWebView.loadUrl("about:blank");
        }
    }

}
