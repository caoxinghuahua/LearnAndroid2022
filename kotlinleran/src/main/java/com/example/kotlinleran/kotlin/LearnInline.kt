package com.example.kotlinleran.kotlin

class LearnInline {

    /**
     * https://www.bilibili.com/video/BV1kz4y1f7sf/?spm_id_from=pageDriver&vd_source=1478e2aca5cba331d35a8f546df40692
     * 以函数作为参数,以函数作为返回值
     * 研究inline和noline的使用
     * */
    inline fun hello(preAction: () -> Unit, noinline postAction: () -> Unit, crossinline middleAction: () -> Unit): () -> Unit {
        preAction()
        println("****hello*****")

        var runnable = Runnable {
            //被间接调用，用crossinline增强布局内联
            run {
                middleAction()
            }
        }
        runnable.run()
        postAction()
        return postAction
    }

//    /


}

fun main() {

    LearnInline().hello({
        println("preAction")
    }, {
        println("postAction")
    }, {
        println("middleAction")
    })
}

