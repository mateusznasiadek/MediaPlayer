package com.example.mediaplayer2

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mediaplayer2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    var player: MediaPlayer? = null

    private lateinit var viewModel: ActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: ActivityViewModel by viewModels()
        viewModel = vm

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = vm

        volumeControlStream = AudioManager.STREAM_MUSIC
    }

    fun play(v: View?) {
        if (player == null) {
            player = MediaPlayer.create(this, viewModel.playlist[viewModel.index.value!!])

            player!!.setOnCompletionListener { nextTrack(v) }
        }
        player!!.start()
    }

    fun pause(v: View?) {
        if (player != null) {
            player!!.pause()
        }
    }

    fun stop(v: View?) {
        stopPlayer()
    }

    fun fastForward(v: View?) {
        if (player != null) {
            player?.currentPosition?.plus(10000)?.let { player?.seekTo(it) }
        }
    }

    fun rewindBack(v: View?) {
        if (player != null) {
            player?.currentPosition?.minus(10000)?.let { player?.seekTo(it) }
        }
    }

    fun nextTrack(v: View?) {
        viewModel.index.value = viewModel.index.value?.plus(1)
        if (viewModel.index.value!! >= viewModel.playlist.size)
            viewModel.index.value = 0
        if (player != null) {
            player!!.reset()
            player = MediaPlayer.create(this, viewModel.playlist[viewModel.index.value!!])
            player!!.start()
            player!!.setOnCompletionListener { nextTrack(v) }
        }
    }

    fun previousTrack(v: View?) {
        viewModel.index.value = viewModel.index.value?.minus(1)
        if (viewModel.index.value!! < 0)
            viewModel.index.value = viewModel.playlist.size - 1
        if (player != null) {
            player!!.reset()
            player = MediaPlayer.create(this, viewModel.playlist[viewModel.index.value!!])
            player!!.start()
            player!!.setOnCompletionListener { nextTrack(v) }
        }
    }

    private fun stopPlayer() {
        if (player != null) {
            player!!.release()
            player = null
        }
    }

    override fun onStop() {
        super.onStop()
        stopPlayer()
    }
}