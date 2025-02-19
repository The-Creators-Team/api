package com.thecreator.codechallengeapicall.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application()

//Step 3 add DI for app and put in manifest and add internet permission