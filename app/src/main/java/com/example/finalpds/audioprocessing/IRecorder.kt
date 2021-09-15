package com.example.finalpds.audioprocessing

interface IRecorder {

    fun createRecordingInstance(path:String,name:String):String
    fun starRecording()
    fun stopRecording()


}