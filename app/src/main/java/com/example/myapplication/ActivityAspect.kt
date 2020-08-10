package com.example.myapplication

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

@Aspect
class ActivityAspect {
    val tag = "ActivityAspect"

    @Pointcut("execution(void android.app.Activity.onResume()) && target(activity)")
    fun resume(activity: AppCompatActivity) {}

    @Before("resume(activity)")
    fun beforeOnResume(activity: AppCompatActivity) {
        Log.e(tag, "before onResume")
    }
}