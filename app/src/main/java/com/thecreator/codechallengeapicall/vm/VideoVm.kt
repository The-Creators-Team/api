package com.thecreator.codechallengeapicall.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thecreator.codechallengeapicall.api.Result
import com.thecreator.codechallengeapicall.api.VideoDetails
import com.thecreator.codechallengeapicall.api.VideoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoVm @Inject constructor(private val videoRepo: VideoRepo):ViewModel(){
    private val _videoList = MutableStateFlow<Result<List<VideoDetails>>>(Result.Loading())
    val videList = _videoList.asStateFlow()

    init {
        loadVideos()
    }

    private fun loadVideos() {
        viewModelScope.launch(Dispatchers.IO) {
            videoRepo.getVideoDetails().collectLatest { result ->
                _videoList.update { result }
            }
        }
    }
}