package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.content.ContextWrapper
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.request.SingleRequest
import com.bumptech.glide.request.target.ImageViewTarget
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect

@Aspect
class GlideAspect {
    val tag = "GlideAspect"

    @After("execution(* com.bumptech.glide.request.target.ImageViewTarget.setResource(..))")
    fun beforeSetResource(joinPoint: JoinPoint) {

        Log.e(tag, "before setDrawableResource ${joinPoint.args.joinToString()}")
        val resource = joinPoint.args[0]
        val imageViewTarget = joinPoint.`this`

        var requestWidth = -1
        var requestHeight = -1
        var actualResourceWidth = -1
        var actualResourceHeight = -1
        var viewWidth = -1
        var viewHeight = -1

        if (imageViewTarget is ImageViewTarget<*>) {
            val request = imageViewTarget.request
            val view = imageViewTarget.view

            // 通过 request 获取到请求的图片 URL
            if (request is SingleRequest<*>) {
                // region 通过反射获取原始请求数据
                val clazz = request.javaClass
                val modelField = clazz.getDeclaredField("model")
                modelField.isAccessible = true
                val model = modelField.get(request)
                // endregion
                Log.e(tag, "model: $model")
                if (model is String) {
                    if (OSSUtil.isAddScale(model)) {
                        val sizeArray = OSSUtil.getScaleWidthAndHeight(model)
                        if (sizeArray != null) {
                            requestWidth = sizeArray[0]
                            requestHeight = sizeArray[1]
                        }
                    } else {
                        // warning no resize on image
                        val context = view.context
                        if (context is Activity) {
                            AlertDialog.Builder(context)
                                    // You can put more info in the dialog
                                .setMessage("No size attach to picUrl, please add it before release")
                                .show()
                        } else {
                            // you can add log in here
                        }
                    }
                }
            }

            viewWidth = view.measuredWidth
            viewHeight = view.measuredHeight

            if (resource is Drawable) {
                actualResourceWidth = resource.intrinsicWidth
                actualResourceHeight = resource.intrinsicHeight
            }
        }


        Log.e(tag, "requestUrl width: ${requestWidth}, $requestHeight")
        Log.e(
            tag,
            "实际 Drawable width: ${actualResourceWidth}, height: $actualResourceHeight"
        )
        Log.e(tag, "实际 View width: ${viewWidth}, height: $viewHeight")
    }
}