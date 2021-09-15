package com.example.finalpds.view

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.example.finalpds.R
import com.example.finalpds.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.streams.asStream


class MainActivity : AppCompatActivity() {
    private val notificator by lazy {Toast.makeText(this,"",Toast.LENGTH_SHORT)}
    private val model: MainActivityViewModel by viewModels()
    private val buttonState =
        { button: Button, condition: Boolean -> button.isEnabled = !condition }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setButtonsEvents()
        setObservers()
        setPermissions()

    }

    private fun setObservers() {
        model.isRecording.observe(this,  { isRecording ->
            buttonState(this.main_act_btn_play, isRecording)
            this.main_act_btn_recording.text = if (isRecording) "Stop" else "Record"
        })

        model.isPlaying.observe(this,  { isPlaying ->
            buttonState(this.main_act_btn_recording, isPlaying)
            this.main_act_btn_play.text = if (isPlaying) "Stop" else "Play"

        })

        model.statusMessage.observe(this,  {
            notificator.setText(it)
            notificator.show()
        })
    }

    private fun setButtonsEvents() {

        this.main_act_btn_play.setOnClickListener {
            model.setIsPlayingValue()
        }

        this.main_act_btn_recording.setOnClickListener {
            model.setIsRecordingValue()
        }
    }

    private fun setPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PERMISSION_GRANTED
        ) {

            val permissions = arrayOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            ActivityCompat.requestPermissions(this, permissions,0)

        } else {
            model.setIsPlayingValue()
            model.setIsRecordingValue()
        }
    }




    @RequiresApi(Build.VERSION_CODES.N)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == 0 && grantResults.asSequence().asStream().allMatch { it ->
                it == PERMISSION_GRANTED
            }) {
            this.model.setIsPlayingValue()
            this.model.setIsRecordingValue()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }





}

