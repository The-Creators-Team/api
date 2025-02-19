package com.thecreator.codechallengeapicall.api


data class VideoDetails(
     val id: String,
     val title: String,
     val thumbnailUrl: String,
     val duration: String,
     val uploadTime: String,
     val views: String,
     val author: String,
     val videoUrl: String,
     val description: String,
     val subscriber: String,
     val isLive: Boolean
    //Step 1 make model of Api class object
)

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T?):Result<T>(data)
    class Loading<T> : Result<T>()
    class Error<T>(data: T?, message:String) : Result<T>(data, message)
}
