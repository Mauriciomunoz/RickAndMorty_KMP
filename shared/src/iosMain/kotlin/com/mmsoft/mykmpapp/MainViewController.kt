package com.mmsoft.mykmpapp

import androidx.compose.ui.window.ComposeUIViewController
import com.mmsoft.mykmpapp.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        App()
    }
}