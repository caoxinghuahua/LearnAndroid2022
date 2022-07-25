package com.example.myapplication2.reflect;

import android.os.Parcelable;

import java.io.Serializable;

public class Student<K extends Comparable & Serializable,V extends Parcelable> {
    K key;
    V value;
}
