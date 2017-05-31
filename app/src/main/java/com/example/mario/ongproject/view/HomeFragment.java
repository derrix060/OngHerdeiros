package com.example.mario.ongproject.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.mario.ongproject.R;

/**
 * Created by mario on 05/05/17.
 */

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_view, container, false);

        VideoView videoView = (VideoView) v.findViewById(R.id.videoHome);

        // Get video URI
        videoView.setVideoURI(Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+R.raw.videoplayback
        ));
        videoView.setMediaController(new MediaController(getActivity()));
        videoView.requestFocus();

        // Start video
        videoView.start();

        return v;
    }
}
