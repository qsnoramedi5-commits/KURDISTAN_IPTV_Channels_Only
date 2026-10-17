package com.kurdistan.iptv

import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class PlayerActivity : Activity() {
    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    private var fullscreen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.BLACK; window.navigationBarColor = Color.BLACK
        val name = intent.getStringExtra("name") ?: "KURDISTAN IPTV"
        val url = intent.getStringExtra("url") ?: return finish()
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setBackgroundColor(0xFF07111F.toInt()) }
        val top = LinearLayout(this).apply { gravity = Gravity.CENTER_VERTICAL; setPadding(16, 8, 16, 8) }
        top.addView(TextView(this).apply { text = "‹   $name"; textSize = 17f; setTextColor(Color.WHITE) }, LinearLayout.LayoutParams(0, 48, 1f))
        val full = TextView(this).apply { text = "⛶"; textSize = 26f; setTextColor(0xFFFFD21F.toInt()); gravity = Gravity.CENTER; setOnClickListener { toggleFullscreen(root, top, this) } }
        top.addView(full, LinearLayout.LayoutParams(48, 48)); root.addView(top)
        playerView = PlayerView(this).apply { useController = true; setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS); setBackgroundColor(Color.BLACK) }
        root.addView(playerView, LinearLayout.LayoutParams(-1, 0, 1f))
        root.addView(TextView(this).apply { text = "KURDISTAN IPTV  •  LIVE STREAM"; setTextColor(0xFFFFD21F.toInt()); gravity = Gravity.CENTER; setPadding(8, 14, 8, 14); textSize = 12f })
        setContentView(root)
        player = ExoPlayer.Builder(this).build().also { exo ->
            playerView.player = exo
            exo.setMediaItem(MediaItem.fromUri(url)); exo.prepare(); exo.playWhenReady = true
            exo.addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    Toast.makeText(this@PlayerActivity, "پەخش نەکرا؛ لینک یان سێرڤەر بپشکنە", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
    private fun toggleFullscreen(root: LinearLayout, top: View, button: View) {
        fullscreen = !fullscreen
        requestedOrientation = if (fullscreen) ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE else ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        if (fullscreen) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            top.visibility = View.GONE
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            top.visibility = View.VISIBLE
        }
    }
    override fun onStop() { super.onStop(); player?.pause() }
    override fun onDestroy() { playerView.player = null; player?.release(); player = null; super.onDestroy() }
}
