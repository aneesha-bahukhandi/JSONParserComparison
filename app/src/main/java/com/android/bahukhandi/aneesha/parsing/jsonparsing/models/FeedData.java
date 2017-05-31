package com.android.bahukhandi.aneesha.parsing.jsonparsing.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FeedData implements Parcelable {

    public String attribution;
    public List<String> tags;
    public String type;
    public Location location;
    public String filter;
    public String created_time;
    public String link;
    public Images images;
    public List<User> users_in_photo;
    public Caption caption;
    public String id;
    public User user;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.attribution);
        dest.writeStringList(this.tags);
        dest.writeString(this.type);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.filter);
        dest.writeString(this.created_time);
        dest.writeString(this.link);
        dest.writeParcelable(this.images, flags);
        dest.writeTypedList(this.users_in_photo);
        dest.writeParcelable(this.caption, flags);
        dest.writeString(this.id);
        dest.writeParcelable(this.user, flags);
    }

    private FeedData(Parcel in){
        this.attribution = in.readString();
        in.readStringList(this.tags);
        this.type = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.filter = in.readString();
        this.created_time = in.readString();
        this.link = in.readString();
        this.images = in.readParcelable(Images.class.getClassLoader());
        in.readTypedList(this.users_in_photo, User.CREATOR);
        this.caption = in.readParcelable(Caption.class.getClassLoader());
        this.id = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<FeedData> CREATOR = new Creator<FeedData>() {
        @Override
        public FeedData createFromParcel(Parcel source) {
            return new FeedData(source);
        }

        @Override
        public FeedData[] newArray(int size) {
            return new FeedData[size];
        }
    };

    private static class Location implements Parcelable{
        double lat;
        double lng;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.lat);
            dest.writeDouble(this.lng);
        }

        private Location(Parcel in){
            this.lat = in.readDouble();
            this.lng = in.readDouble();
        }

        public static final Creator<Location> CREATOR = new Creator<Location>() {
            @Override
            public Location createFromParcel(Parcel source) {
                return new Location(source);
            }

            @Override
            public Location[] newArray(int size) {
                return new Location[size];
            }
        };
    }
}
