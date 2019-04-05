package com.search.nearme.utils;

public class AppConst {

    public static final String SHARED_PREFS = "location_pref";

    public static final String LATTITUDE = "lattitude";

    public static final String LONGITUDE = "longitude";

    public static final String BASE_URL = "https://maps.googleapis.com/maps/api/";

    public static final String GOOGLE_PLACE_API_KEY = "AIzaSyCZPDcfCgmA0YWX8iqG5mXUuqpt_F2KNT8";

    public static final String DISTANCE = "distance";

    public static String LOCATION_VALUE;

    public static String getLocationValue() {
        return LOCATION_VALUE;
    }

    public static void setLocationValue(String locationValue) {
        LOCATION_VALUE = locationValue;
    }
}
