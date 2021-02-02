package com.example.a36_fragments;

import android.os.Parcel;
import android.os.Parcelable;

public class Dog implements Parcelable {

    int age;
    String name;
    double[] doubleArray;

    public Dog() {

    }

    protected Dog(Parcel in) {
        age = in.readInt();
        name = in.readString();
        doubleArray = in.createDoubleArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeDoubleArray(doubleArray);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Dog> CREATOR = new Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel in) {
            return new Dog(in);
        }

        @Override
        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };
}
