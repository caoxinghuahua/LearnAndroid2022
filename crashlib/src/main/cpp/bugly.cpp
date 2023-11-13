//
// Created by hua on 2022/7/17.
//

#include <jni.h>
#include <android/log.h>

extern "C"
JNIEXPORT void JNICALL
Java_com_example_crashlib_crash_CrashReport_testNativeCrash(JNIEnv * env,jobject thiz) {
    __android_log_print(ANDROID_LOG_INFO,"native","xxxxxxxx");
    int *p = NULL;
    *p = 10;
}