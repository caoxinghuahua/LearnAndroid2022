package test.hua

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPluginGroovy implements Plugin<Project> {

    @Override
    void apply(Project target) {
        target.task("showPlugin") {
            doLast {
                println("task in  MyPluginGroovy")
            }
        }
        //注册自定义的transform
        def appExtension = target.extensions.findByType(AppExtension.class)
        appExtension.registerTransform(new TransformTest())
    }
}