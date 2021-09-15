package com.example.finalpds.audioprocessing

import android.content.res.Resources
import android.media.MediaRecorder
import com.example.finalpds.R
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import javax.inject.Inject

class Recorder @Inject constructor(  var recorderDependencies:RecorderDependencies ) : IRecorder  {
    private  var soundRecorder:MediaRecorder?=null


    override fun createRecordingInstance(path: String, name: String): String {
        try {
            soundRecorder=recorderDependencies.getMediaRecordInstance(path,name)
            soundRecorder?.prepare()
            return Resources.getSystem().getString(R.string.record_success)
        } catch (e: IOException) {
            return Resources.getSystem().getString(R.string.record_error)
        }
    }

    override fun starRecording(){
        soundRecorder?.start()
    }

    override fun stopRecording(){
        soundRecorder?.stop()
        soundRecorder?.release()
        soundRecorder = null
    }


}