package com.android.bahukhandi.aneesha.parsing.jsonparsing.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ComplexJavaObject implements Parcelable{

    public Metadata meta;
    public List<FeedData> dataList;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(meta, flags);
        dest.writeTypedList(dataList);
    }

    private ComplexJavaObject(Parcel in){
        this.meta = in.readParcelable(Metadata.class.getClassLoader());
        in.readTypedList(this.dataList, FeedData.CREATOR);
    }

    public static final Creator<ComplexJavaObject> CREATOR = new Creator<ComplexJavaObject>() {
        @Override
        public ComplexJavaObject createFromParcel(Parcel source) {
            return new ComplexJavaObject(source);
        }

        @Override
        public ComplexJavaObject[] newArray(int size) {
            return new ComplexJavaObject[size];
        }
    };

    private static class Metadata implements Parcelable{
        int code;
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(code);
        }

        private Metadata(Parcel in){
            this.code = in.readInt();
        }

        public static final Creator<Metadata> CREATOR = new Creator<Metadata>() {
            @Override
            public Metadata createFromParcel(Parcel source) {
                return new Metadata(source);
            }

            @Override
            public Metadata[] newArray(int size) {
                return new Metadata[size];
            }
        };
    }
}
