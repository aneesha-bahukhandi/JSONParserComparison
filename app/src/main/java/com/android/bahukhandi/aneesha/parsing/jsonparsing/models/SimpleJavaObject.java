package com.android.bahukhandi.aneesha.parsing.jsonparsing.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SimpleJavaObject implements Parcelable {

    public int id;
    public String name;
    public String link;
    public String date;
    public List<String> neighbours;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(link);
        dest.writeString(date);
        dest.writeStringList(neighbours);
    }

    protected SimpleJavaObject(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        this.link = in.readString();
        this.date = in.readString();
        in.readStringList(this.neighbours);
    }

    public static final Creator<SimpleJavaObject> CREATOR = new Creator<SimpleJavaObject>() {
        @Override
        public SimpleJavaObject createFromParcel(Parcel source) {
            return new SimpleJavaObject(source);
        }

        @Override
        public SimpleJavaObject[] newArray(int size) {
            return new SimpleJavaObject[size];
        }
    };
}
