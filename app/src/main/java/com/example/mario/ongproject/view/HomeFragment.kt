package com.example.mario.ongproject.view

import android.content.ContentValues.TAG
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mario.ongproject.R
import com.example.mario.ongproject.R.id.videoView
import com.universalvideoview.UniversalMediaController
import com.universalvideoview.UniversalVideoView

/**
 * Created by mario on 05/05/17.
 */

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.home_view, container, false)

        val mVideoView: UniversalVideoView
        val mMediaController: UniversalMediaController

        mVideoView = v.findViewById(videoView) as UniversalVideoView

        mVideoView.setVideoURI(Uri.parse("android.resource://" + v.context.packageName + "/" + R.raw.videoplayback))
        mVideoView.start()
        mVideoView.contentDescription = getString(R.string.home_video_descr)

        mMediaController = v.findViewById(R.id.media_controller) as UniversalMediaController
        mVideoView.setMediaController(mMediaController)

        mVideoView.setVideoViewCallback(object : UniversalVideoView.VideoViewCallback {
            internal var mVideoLayout = v.findViewById(R.id.video_layout) as View
            internal var txt_Title = v.findViewById(R.id.txt_Title) as TextView
            internal var txtHome1 = v.findViewById(R.id.txtHome1) as TextView
            internal var txtHome2 = v.findViewById(R.id.txt_home2) as TextView

            internal var cachedHeight = v.height

            override fun onScaleChange(isFullscreen: Boolean) {
                if (isFullscreen) {
                    val layoutParams = mVideoLayout.layoutParams
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                    mVideoLayout.layoutParams = layoutParams

                    hideTexts()
                } else {
                    val layoutParams = mVideoLayout.layoutParams
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                    layoutParams.height = cachedHeight
                    mVideoLayout.layoutParams = layoutParams

                    showTexts()
                }
            }

            private fun hideTexts() {

                txt_Title.visibility = View.GONE
                txtHome1.visibility = View.GONE
                txtHome2.visibility = View.GONE
            }

            private fun showTexts() {

                txt_Title.visibility = View.VISIBLE
                txtHome1.visibility = View.VISIBLE
                txtHome2.visibility = View.VISIBLE
            }


            override fun onPause(mediaPlayer: MediaPlayer) { // Video pause
                Log.d(TAG, "onPause UniversalVideoView callback")
            }

            override fun onStart(mediaPlayer: MediaPlayer) { // Video start/resume to play
                Log.d(TAG, "onStart UniversalVideoView callback")
            }

            override fun onBufferingStart(mediaPlayer: MediaPlayer) {// steam start loading
                Log.d(TAG, "onBufferingStart UniversalVideoView callback")
            }

            override fun onBufferingEnd(mediaPlayer: MediaPlayer) {// steam end loading
                Log.d(TAG, "onBufferingEnd UniversalVideoView callback")
            }

        })
        return v
    }
}
