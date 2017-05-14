package com.example.mario.ongproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by mario on 05/05/17.
 */

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_view, container, false);

        VideoView videoView = (VideoView) v.findViewById(R.id.videoHome);
        videoView.setVideoURI(Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+R.raw.videoplayback
        ));
        videoView.setMediaController(new MediaController(getActivity()));
        videoView.requestFocus();
        videoView.start();

        return v;
    }
}
