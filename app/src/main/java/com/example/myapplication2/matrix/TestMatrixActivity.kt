package com.example.myapplication2.matrix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.databinding.ActivityTestMatrixBinding
import com.tencent.matrix.Matrix
import com.tencent.matrix.trace.TracePlugin
import com.tencent.matrix.trace.config.TraceConfig

//import com.tencent.matrix.iocanary.IOCanaryPlugin
//import com.tencent.matrix.iocanary.config.IOConfig


class TestMatrixActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestMatrixBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestMatrixBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startMatrix.setOnClickListener {
            startMatrix()
        }
    }

    fun startMatrix() {
        val builder: Matrix.Builder = Matrix.Builder(application) // build matrix

        builder.pluginListener(TestPluginListener(this)) // add general pluginListener

        val dynamicConfig = DynamicConfigImplDemo() // dynamic config


        // init plugin
//        val ioCanaryPlugin = IOCanaryPlugin(
//            IOConfig.Builder()
//                .dynamicConfig(dynamicConfig)
//                .build()
//        )
        //add to matrix
//        builder.plugin(ioCanaryPlugin)
        // Configure trace canary.
        val  tracePlugin = TracePlugin(TraceConfig.Builder().dynamicConfig(dynamicConfig).build())
        builder.plugin(tracePlugin);

        //init matrix
        Matrix.init(builder.build())

        // start plugin
        tracePlugin.start()
    }
}