package com.rajeshrayalasolutions.nearbyresturants.Modal;

import com.rajeshrayalasolutions.nearbyresturants.Response.PlacesResponse;

/**
 * Created by Rajesh Rayala
 */

public class PlacesDetails_Modal {

    public String address,phone_no,distance,name,photourl;
    public float rating;
    PlacesResponse.LocationA locationA;
    byte[] imageFromBb;

    public byte[] getImageFromBb() {
        return imageFromBb;
    }

    public void setImageFromBb(byte[] imageFromBb) {
        this.imageFromBb = imageFromBb;
    }

    public PlacesResponse.LocationA getLocationA() {
        return locationA;
    }

    public void setLocationA(PlacesResponse.LocationA locationA) {
        this.locationA = locationA;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


}
