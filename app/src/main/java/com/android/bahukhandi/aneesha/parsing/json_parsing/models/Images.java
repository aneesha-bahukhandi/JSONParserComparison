package com.android.bahukhandi.aneesha.parsing.json_parsing.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Images implements Parcelable{

    public Image low_resolution;
    public Image thumbnail;
    public Image standard_resolution;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.low_resolution, flags);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeParcelable(this.standard_resolution, flags);
    }

    private Images(Parcel in){
        this.low_resolution = in.readParcelable(Image.class.getClassLoader());
        this.thumbnail = in.readParcelable(Image.class.getClassLoader());
        this.standard_resolution = in.readParcelable(Image.class.getClassLoader());
    }

    public static final Creator<Images> CREATOR = new Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel source) {
            return new Images(source);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };

    private static class Image implements Parcelable{

        public String url;
        public int width;
        public int height;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeInt(this.width);
            dest.writeInt(this.height);
        }

        private Image(Parcel in){
            this.url = in.readString();
            this.width = in.readInt();
            this.height = in.readInt();
        }

        public static final Creator<Image> CREATOR = new Creator<Image>() {
            @Override
            public Image createFromParcel(Parcel source) {
                return new Image(source);
            }

            @Override
            public Image[] newArray(int size) {
                return new Image[size];
            }
        };
    }

}