package com.example.myapplication

import android.content.Context
import android.util.Log
import android.widget.Toast
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

private const val pointCutMethod = "execution(@com.example.myapplication.annotation.CheckVIP * *(..))"

@Aspect
class CheckVIPAspect {
    @Pointcut(pointCutMethod)
    fun methodAnnotationWithCheckVIP() {}

    @Around("methodAnnotationWithCheckVIP()")
    fun weaveJoinPoint(joinPoint: ProceedingJoinPoint): Any? {
        val isVIP = MainActivity.isVIP
        return if (isVIP) {
            val result = joinPoint.proceed()
            result
        } else {
            // Activity's get method?
            val context = joinPoint.`this` as? Context
            if (context != null) {
                Toast.makeText(context, "你没有 VIP，滚吧", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("TESTTTFFF", "你没有 VIP")
            }
            null
        }
    }
}