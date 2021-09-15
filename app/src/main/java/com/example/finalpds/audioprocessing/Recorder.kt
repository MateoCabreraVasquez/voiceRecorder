package com.example.finalpds.audioprocessing

import android.content.res.Resources
import android.media.MediaRecorder
import com.example.finalpds.R
import java.io.IOException
import javax.annotation.Resource


class Recorder : IRecorder  {

    private  var soundRecorder: MediaRecorder? = null

    override fun createRecordingInstance(path: String, name: String): String {
        soundRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile("$path$name")
            try {
                prepare()
                return Resources.getSystem().getString(R.string.record_success)
            } catch (e: IOException) {
                return Resources.getSystem().getString(R.string.record_error)
            }
        }
    }


    override fun starRecording(){
        soundRecorder?.start()
    }

    override fun stopRecording(){
        soundRecorder?.stop()
        soundRecorder = null
    }


}