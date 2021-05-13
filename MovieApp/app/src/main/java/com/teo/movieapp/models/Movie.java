package com.teo.movieapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.teo.movieapp.constants.Crew;
import com.teo.movieapp.constants.Grade;
import com.teo.movieapp.constants.Review;

public class Movie implements Parcelable {

    private final String name;
    private final int id;
    private final int yearOfPublish;
    private final Review review;
    private final Grade grade;
    private final Crew crew;
    private final int imageRes;

    protected Movie(Parcel in) {
        name = in.readString();
        id = in.readInt();
        yearOfPublish = in.readInt();
        review = (Review) in.readSerializable();
        grade = (Grade) in.readSerializable();
        crew = (Crew) in.readSerializable();
        imageRes = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getYearOfPublish() {
        return yearOfPublish;
    }

    public Review getReview() {
        return review;
    }

    public Grade getGrade() {
        return grade;
    }

    public Crew getCrew() {
        return crew;
    }

    public int getImageRes() {
        return imageRes;
    }

    public Movie(String name, int yearOfPublish, int id, Review review, Grade grade, Crew crew, int imageRes) {
        this.name = name;
        this.yearOfPublish = yearOfPublish;
        this.review = review;
        this.grade = grade;
        this.crew = crew;
        this.id = id;
        this.imageRes = imageRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(id);
        parcel.writeInt(yearOfPublish);
        parcel.writeSerializable(review);
        parcel.writeSerializable(grade);
        parcel.writeSerializable(crew);
        parcel.writeInt(imageRes);
    }
}
