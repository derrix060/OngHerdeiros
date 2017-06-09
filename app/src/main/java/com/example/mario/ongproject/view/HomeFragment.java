package com.example.mario.ongproject.view;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mario.ongproject.R;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import static android.content.ContentValues.TAG;
import static com.example.mario.ongproject.R.id.videoView;

/**
 * Created by mario on 05/05/17.
 */

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.home_view, container, false);

        UniversalVideoView mVideoView;
        UniversalMediaController mMediaController;

        mVideoView = (UniversalVideoView) v.findViewById(videoView);

        mVideoView.setVideoURI(Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+R.raw.videoplayback));
        mVideoView.start();
        mVideoView.setContentDescription(getString(R.string.home_video_descr));

        mMediaController = (UniversalMediaController) v.findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);

        mVideoView.setVideoViewCallback(new UniversalVideoView.VideoViewCallback() {
            View mVideoLayout = (View) v.findViewById(R.id.video_layout);
            TextView txt_Title = (TextView) v.findViewById(R.id.txt_Title);
            TextView txtHome1 = (TextView) v.findViewById(R.id.txtHome1);
            TextView txtHome2 = (TextView) v.findViewById(R.id.txt_home2);

            int cachedHeight = v.getHeight();

            @Override
            public void onScaleChange(boolean isFullscreen) {
                if (isFullscreen) {
                    ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    mVideoLayout.setLayoutParams(layoutParams);

                    hideTexts();
                } else {
                    ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = cachedHeight;
                    mVideoLayout.setLayoutParams(layoutParams);

                    showTexts();
                }
            }

            private void hideTexts(){

                txt_Title.setVisibility(View.GONE);
                txtHome1.setVisibility(View.GONE);
                txtHome2.setVisibility(View.GONE);
            }

            private void showTexts(){

                txt_Title.setVisibility(View.VISIBLE);
                txtHome1.setVisibility(View.VISIBLE);
                txtHome2.setVisibility(View.VISIBLE);
            }


            @Override
            public void onPause(MediaPlayer mediaPlayer) { // Video pause
                Log.d(TAG, "onPause UniversalVideoView callback");
            }

            @Override
            public void onStart(MediaPlayer mediaPlayer) { // Video start/resume to play
                Log.d(TAG, "onStart UniversalVideoView callback");
            }

            @Override
            public void onBufferingStart(MediaPlayer mediaPlayer) {// steam start loading
                Log.d(TAG, "onBufferingStart UniversalVideoView callback");
            }

            @Override
            public void onBufferingEnd(MediaPlayer mediaPlayer) {// steam end loading
                Log.d(TAG, "onBufferingEnd UniversalVideoView callback");
            }

        });
        return v;
    }
}
