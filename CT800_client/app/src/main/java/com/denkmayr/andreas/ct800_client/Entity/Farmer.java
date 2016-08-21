package com.denkmayr.andreas.ct800_client.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Farmer implements Parcelable{
    private String name;
    private String email;
    private String zip;
    private String residence;
    private String street;
    private String streetNumber;

    public Farmer() {}

    public Farmer(String name) {
        this.name = name;
    }

    public Farmer(String name, String email) {
        this(name);
        this.email = email;
    }

    public Farmer(String name, String email, String residence, String zip, String street, String streetNumber) {
        this(name,email);
        this.zip = zip;
        this.residence = residence;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeString(getEmail());
        dest.writeString(getResidence());
        dest.writeString(getZip());
        dest.writeString(getStreet());
        dest.writeString(getStreetNumber());
    }

    public  static final Parcelable.Creator<Farmer> CREATOR =
            new Parcelable.Creator<Farmer>(){
                @Override
                public Farmer createFromParcel(Parcel source) {
                    return new Farmer(
                            source.readString(), //Name
                            source.readString(), //Email
                            source.readString(), //Residence
                            source.readString(), //Zip
                            source.readString(), //Street
                            source.readString()); //StreetNumber
                }

                @Override
                public Farmer[] newArray(int size) {
                    return new Farmer[size];
                }
            };
}
