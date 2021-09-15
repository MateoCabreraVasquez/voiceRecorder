package com.example.finalpds.audioprocessing

import android.content.res.Resources
import android.media.MediaRecorder
import com.example.finalpds.R
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.io.IOException
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object RecorderDependencies{

   // @Singleton

    @Provides
    fun getMediaRecordInstance(path:String,name:String)= MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile("$path$name")
        }


}