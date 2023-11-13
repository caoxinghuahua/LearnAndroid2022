// IKtvController.aidl
package com.example.myapplication2;

// Declare any non-default types here with import statements

interface IKtvController {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void play(String play);
    void pause(String pause);
}