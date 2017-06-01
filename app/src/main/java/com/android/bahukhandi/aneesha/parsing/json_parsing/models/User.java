package com.android.bahukhandi.aneesha.parsing.json_parsing.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public String username;
    public String profile_picture;
    public String id;
    public String full_name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.profile_picture);
        dest.writeString(this.id);
        dest.writeString(this.full_name);
    }

    private User(Parcel in){
        this.username = in.readString();
        this.profile_picture = in.readString();
        this.id = in.readString();
        this.full_name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
