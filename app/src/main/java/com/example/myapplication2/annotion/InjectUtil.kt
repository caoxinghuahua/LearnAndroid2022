package com.example.myapplication2.annotion

import android.app.Activity
import android.view.View
import java.lang.reflect.Field

class InjectUtil {

    companion object {
        fun inject(activity: Activity) {
            var cls: Class<out Activity> = activity.javaClass
            var fields = cls.declaredFields
            fields.forEach {
                if (it.isAnnotationPresent(InjectView::class.java)) {
                    val injectView = it.getAnnotation(InjectView::class.java)
                    val id=injectView.value
                    val view=activity.findViewById<View>(id)
                    it.isAccessible=true
                    it.set(activity,view)
                } else {
                }
            }
        }
    }

}