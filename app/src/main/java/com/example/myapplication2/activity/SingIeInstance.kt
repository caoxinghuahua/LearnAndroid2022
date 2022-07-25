package com.example.myapplication2.activity

class SingIeInstance {
    object Holder{
        val instance:SingIeInstance=SingIeInstance()
    }
    companion object{
        val getInstance:SingIeInstance=Holder.instance
    }
}