package com.example.permissionchecker

import android.graphics.drawable.Drawable

data class AppInfo(
    val appName: String,
    val packageName: String,
    val appIcon: Drawable?,
    val permissions: List<String>
)
