package test.hua

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager

class TransformTest extends Transform {

    @Override
    String getName() {
        return "MyTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        println '--------------- MyCustomTransform visit start --------------- '
        def startTime = System.currentTimeMillis()
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider
        // 1、删除之前的输出
        if (outputProvider != null) {
            outputProvider.deleteAll()
        }

        // Transform 的 inputs 有两种类型，一种是目录，一种是 jar包，要分开遍历
        inputs.each { TransformInput input ->
            // 2、遍历 directoryInputs（本地 project 编译成的多个 class⽂件存放的目录）
            input.directoryInputs.each { DirectoryInput directoryInput ->
//                handleDirectory(directoryInput, outputProvider)
            }
            // 3、遍历 jarInputs（各个依赖所编译成的 jar 文件）
            input.jarInputs.each { JarInput jarInput ->
//                handleJar(jarInput, outputProvider)
            }
        }
        def cost = (System.currentTimeMillis() - startTime) / 1000
        println '--------------- MyCustomTransform visit end --------------- '
        println "MyCustomTransform cost ： $cost s"


    }
}