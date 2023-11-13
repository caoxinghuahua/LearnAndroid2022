package test.hua

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.Action
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
        //创建扩展
        target.getExtensions().create("PluginExt",PluginBean.class)
        //配置阶段解析完build.gradle后执行
        target.afterEvaluate(new Action<Project>() {
            @Override
            void execute(Project project) {
               final pluginExt=project.getExtensions().findByType(PluginBean.class)
                //获取android扩展
                AppExtension android=project.getExtensions().findByType(AppExtension.class)
                android.applicationVariants.all(new Action<ApplicationVariant>() {
                    @Override
                    void execute(ApplicationVariant applicationVariant) {

                    }
                })
            }
        })
    }
}