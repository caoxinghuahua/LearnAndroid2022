package com.example.kotlinleran.kotlin

class Single {
    //单例的几种实现
    //https://blog.csdn.net/tgvincent/article/details/118383204?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-118383204-blog-122409567.pc_relevant_multi_platform_whitelistv3&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-118383204-blog-122409567.pc_relevant_multi_platform_whitelistv3&utm_relevant_index=1

    //object关键字,相当于饿汉模式，线程安全
    object SingleInstance {
        fun show() {
            println("object方式实现单例")
        }

    }

    //懒汉
    class SingleA private constructor() {
        companion object {
            private var instance: SingleA? = null
                get() {
                    if (field == null) {
                        field = SingleA()
                    }
                    return field
                }

            //不用getInstance,因为在半生对象里面默认有（instance变量）getInstance方法
            fun get(): SingleA {
                return instance!!
            }

        }

        fun showA() {
            println("懒汉实现单列")
        }

    }

    //线程安全懒汉模式

    class SingleB private constructor() {
        companion object {
            private var instance: SingleB? = null
                get() {
                    if (field == null) {
                        field = SingleB()
                    }
                    return field
                }

            @Synchronized
            fun get(): SingleB {
                return instance!!
            }
        }

        fun showB() {
            println("懒汉线程安全模式实现")
        }
    }

    //double check 线程安全
    class SingleC private constructor() {
        companion object {
            val instance: SingleC by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
                SingleC()
            }
        }

        fun showC() {
            println("双重校验")
        }
    }
    //静态内部类

    class SingleD private constructor() {
        companion object {
            val instance = SingleHolder.holder
        }

        private object SingleHolder {
            val holder = SingleD()
        }

        fun showD() {
            println("静态内部类")
        }
    }

}


fun main() {
    Single.SingleInstance.show()
    Single.SingleA.get().showA()
    Single.SingleB.get().showB()
    Single.SingleC.instance.showC()
    Single.SingleD.instance.showD()
}