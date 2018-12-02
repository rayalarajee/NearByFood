package com.rajeshrayalasolutions.nearbyresturants;

import com.rajeshrayalasolutions.nearbyresturants.Modal.PlacesDetails_Modal;

import java.util.ArrayList;

public class PersistanceForData {
    private static final PersistanceForData ourInstance = new PersistanceForData();

    public static PersistanceForData getInstance() {
        return ourInstance;
    }

    private PersistanceForData() {
    }

    public ArrayList<PlacesDetails_Modal> getPlacesDetails_modals() {
        return placesDetails_modals;
    }

    public void setPlacesDetails_modals(ArrayList<PlacesDetails_Modal> placesDetails_modals) {
        this.placesDetails_modals = placesDetails_modals;
    }

    ArrayList<PlacesDetails_Modal> placesDetails_modals=new ArrayList<>();
}
