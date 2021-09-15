package com.example.finalpds.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalpds.audioprocessing.Recorder

import dagger.hilt.android.*
import javax.inject.Inject


class MainActivityViewModel   @Inject constructor(val recorder:Recorder) : ViewModel(){
    val isRecording :MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean> ()}
    val isPlaying : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean> ()}
    val statusMessage:MutableLiveData<String?> by lazy { MutableLiveData<String?> ()}


    init {
        isRecording.value=true
        isPlaying.value=true
    }


    fun setIsRecordingValue(){this.isRecording.value=!isRecording.value!!
                            if (isRecording.value!!){
                                statusMessage.value=recorder.createRecordingInstance("","hola mundo")
                                recorder.starRecording()}
                            else{
                                recorder.starRecording()
                            }

    }

    fun setIsPlayingValue(){this.isPlaying.value=!isPlaying.value!!}

}