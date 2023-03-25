package com.example.kotlinleran.kotlin

class SingleTest {
    // object
    object SingleIstance {
        val name = "object"
    }

    class SingelInstance1 private constructor() {
        companion object {
            private var instance: SingelInstance1? = null
                get() {
                    if (field == null) {
                        field = SingelInstance1()
                        instance = field
                    }
                    return field
                }

            fun getS(): SingelInstance1 {
                return instance!!
            }
        }

    }

    class SingelInstance2 private constructor() {
        companion object {
            var instance: SingelInstance2? = null
                get() {
                    if (field == null) {
                        field = SingelInstance2()
                        instance = field
                    }
                    return instance
                }

            @Synchronized
            fun getB(): SingelInstance2 {
                return instance!!
            }

        }
    }

    class SingelInstance3 private constructor() {

        companion object {
            val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
                SingelInstance3()
            }

        }
    }

    class SingelInstance4 private constructor() {
        companion object {
            val instance = SingelHolder4.holder
        }

        private object SingelHolder4 {
            val holder = SingelInstance4()
        }
    }
}