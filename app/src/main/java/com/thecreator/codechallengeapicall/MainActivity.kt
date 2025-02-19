package com.thecreator.codechallengeapicall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thecreator.codechallengeapicall.api.Result
import com.thecreator.codechallengeapicall.ui.composables.ErrorScreen
import com.thecreator.codechallengeapicall.ui.composables.LoadingScreen
import com.thecreator.codechallengeapicall.ui.composables.VideoScreen
import com.thecreator.codechallengeapicall.ui.theme.CodeChallengeAPICallTheme
import com.thecreator.codechallengeapicall.vm.VideoVm
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm = hiltViewModel<VideoVm>()
            val videoList = vm.videList.collectAsStateWithLifecycle().value
            CodeChallengeAPICallTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when(videoList){
                        is Result.Error -> videoList.message?.let { ErrorScreen(it) }
                        is Result.Loading -> LoadingScreen()
                        is Result.Success -> videoList.data?.let { VideoScreen(it) }
                    }
                }
            }
        }
    }
}
