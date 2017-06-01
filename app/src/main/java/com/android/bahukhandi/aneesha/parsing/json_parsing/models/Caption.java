package com.android.bahukhandi.aneesha.parsing.json_parsing.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Caption implements Parcelable {

    public String created_time;
    public String text;
    public User from;
    public String id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(created_time);
        dest.writeString(text);
        dest.writeParcelable(from, flags);
        dest.writeString(id);
    }

    private Caption(Parcel in){
        this.created_time = in.readString();
        this.text = in.readString();
        this.from = in.readParcelable(User.class.getClassLoader());
        this.id = in.readString();
    }

    public static final Creator<Caption> CREATOR = new Creator<Caption>() {
        @Override
        public Caption createFromParcel(Parcel source) {
            return new Caption(source);
        }

        @Override
        public Caption[] newArray(int size) {
            return new Caption[size];
        }
    };
}
