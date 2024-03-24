package com.example.myapplication2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.myapplication2.blockCanary.BlockManager
import com.example.myapplication2.databinding.ActivityTestBlockBinding
import com.github.moduth.blockcanary.BlockCanary
import com.github.moduth.blockcanary.BlockCanaryContext

class TestBlockActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestBlockBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBlockBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startMyBlock.setOnClickListener {
            BlockManager.register()
        }
        binding.startThirdBlock.setOnClickListener {
            BlockCanary.install(this, BlockCanaryContext()).start()
        }
        binding.createBlock.setOnClickListener {
            createBlock()
        }
    }

    private fun createBlock() {
        Thread.sleep(1200)
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}