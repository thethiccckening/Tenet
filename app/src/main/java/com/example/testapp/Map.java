package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapsSdkInitializedCallback;

import java.util.ArrayList;
import java.util.List;

public class Map extends AppCompatActivity implements OnMapReadyCallback, OnMapsSdkInitializedCallback {

    private GoogleMap mMap;
    private LatLng user_loc, tmp_loc;
    private ArrayList<LatLng> locationArrayList;
    private ArrayList<String> locationArrayListtitle;
    private boolean dummydataadded =false;


    DatabaseHelper dataDBhelper = new DatabaseHelper(this);;
    SQLiteDatabase housingdb;

    List<Cursor> Housing_list = new ArrayList<>();
    protected static final String ACTIVITY_NAME = "MapActivity"; //debugging message

    private ProgressBar simpleProgressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapsInitializer.initialize(getApplicationContext(), MapsInitializer.Renderer.LATEST, this);

        setContentView(R.layout.activity_map);
        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        simpleProgressBar.setVisibility(View.VISIBLE);


        if (!dummydataadded){
            insertDummydata();
            dummydataadded = true;
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

        locationArrayList = new ArrayList<>();
        locationArrayListtitle = new ArrayList<>();


        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.navigation_bar,ToolbarFragment.class,bundle)
                .commit();
    }

    @Override
    public void onMapsSdkInitialized(MapsInitializer.Renderer renderer) {
        switch (renderer) {
            case LATEST:
                Log.d("MapsDemo", "The latest version of the renderer is used.");
                break;
            case LEGACY:
                Log.d("MapsDemo", "The legacy version of the renderer is used.");
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        user_loc = new LatLng(43.475123, -80.527557);
        mMap.addMarker(new MarkerOptions()
                .position(user_loc)
                .title("User Location"));
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(14.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(user_loc));

        storeHousingDataInArrays();

        for (int i = 0; i < locationArrayList.size(); i++) {

            // below line is use to add marker to each location of our array list.
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title(locationArrayListtitle.get(i)));

            // below line is use to move our camera to the specific location.
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
            Log.i(ACTIVITY_NAME, locationArrayListtitle.get(i).toString());

        }

    }


    //Get data from database and store in arrays
    @SuppressLint("Range")
    void storeHousingDataInArrays() {
        Log.i(ACTIVITY_NAME, "getting all house thread");
        Cursor cursor = readHousingData();
        LatLng current;


        if (cursor == null) {
            Log.i(ACTIVITY_NAME, "getting all houses, no data");
        } else {
            while (cursor.moveToNext()) {
                Housing_list.add(cursor);
                double lat, longe;
                lat = Double.parseDouble(cursor.getString(cursor.getColumnIndex(dataDBhelper.HOUSING_LAT)));
                longe = Double.parseDouble(cursor.getString(cursor.getColumnIndex(dataDBhelper.HOUSING_LONG)));
                Log.i(ACTIVITY_NAME, "lat" +String.valueOf(lat));
                Log.i(ACTIVITY_NAME, "lat" +String.valueOf(longe));

                locationArrayList.add( new LatLng(lat, longe));
                locationArrayListtitle.add(cursor.getString(cursor.getColumnIndex(dataDBhelper.HOUSING_NAME)));
                //mMap.addMarker(new MarkerOptions().position(tmp_loc).title(cursor.getString(cursor.getColumnIndex(dataDBhelper.HOUSING_NAME))));

            }
        }
    }

    @SuppressLint("Range")
    public Cursor readHousingData(){
        Log.i(ACTIVITY_NAME, "getting all houses via query");

        housingdb  = dataDBhelper.getReadableDatabase();
        Cursor cursorhousing = housingdb.rawQuery("SELECT * FROM " + dataDBhelper.HOUSING_TABLE_NAME, null);
        if (cursorhousing.getCount() == 0) {
            Log.i(ACTIVITY_NAME, "getting all houses, no data");
            return null;
        } else {
            return cursorhousing;
        }
    }

    public void insertDummydata(){
        Log.i(ACTIVITY_NAME, "Inserting dummy data");

         housingdb = dataDBhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(dataDBhelper.HOUSING_NAME, "Apartment");
        contentValues.put(dataDBhelper.HOUSING_DESCRIPTION, "La Bella Condo");
        contentValues.put(dataDBhelper.HOUSING_CITY, "Waterloo");
        contentValues.put(dataDBhelper.HOUSING_STATE, "ON");
        contentValues.put(dataDBhelper.HOUSING_COUNTRY, "Canada");
        contentValues.put(dataDBhelper.HOUSING_LAT, "43.478760");
        contentValues.put(dataDBhelper.HOUSING_LONG, "-80.523430");
        contentValues.put(dataDBhelper.HOUSING_Address, "Waterloo");
        contentValues.put(dataDBhelper.HOUSING_PRICE, 1500);
        contentValues.put(dataDBhelper.HOUSING_BEDROOM, 2);
        contentValues.put(dataDBhelper.HOUSING_BATHROOM, 2);
        contentValues.put(dataDBhelper.HOUSING_PARKING, 1);
        contentValues.put(dataDBhelper.HOUSING_UTILITIES, "hydro,heating,water,internet");
        contentValues.put(dataDBhelper.HOUSING_AMENITIES, "fitness,laundry");
        contentValues.put(dataDBhelper.HOUSING_TYPE, "Apartment");
        long result = housingdb.insert(dataDBhelper.HOUSING_TABLE_NAME, null, contentValues);
        if(result == -1){
            Log.i(ACTIVITY_NAME, "Inserting dummy data failed");
        }
        else{
            Log.i(ACTIVITY_NAME, "Inserting dummy data success");
        }

        contentValues = new ContentValues();
        contentValues.put(dataDBhelper.HOUSING_NAME, "Claire House");
        contentValues.put(dataDBhelper.HOUSING_DESCRIPTION, "Claire's House");
        contentValues.put(dataDBhelper.HOUSING_CITY, "Waterloo");
        contentValues.put(dataDBhelper.HOUSING_STATE, "ON");
        contentValues.put(dataDBhelper.HOUSING_COUNTRY, "Canada");
        contentValues.put(dataDBhelper.HOUSING_LAT, "43.471290");
        contentValues.put(dataDBhelper.HOUSING_LONG, "-80.523070");
        contentValues.put(dataDBhelper.HOUSING_Address, "Waterloo");
        contentValues.put(dataDBhelper.HOUSING_PRICE, 600);
        contentValues.put(dataDBhelper.HOUSING_BEDROOM, 5);
        contentValues.put(dataDBhelper.HOUSING_BATHROOM, 2);
        contentValues.put(dataDBhelper.HOUSING_PARKING, 2);
        contentValues.put(dataDBhelper.HOUSING_UTILITIES, "hydro,heating,water");
        contentValues.put(dataDBhelper.HOUSING_AMENITIES, "laundry,dishwasher");
        contentValues.put(dataDBhelper.HOUSING_TYPE, "Room");
        result = housingdb.insert(dataDBhelper.HOUSING_TABLE_NAME, null, contentValues);
        if(result == -1){
            Log.i(ACTIVITY_NAME, "Inserting dummy data failed");
        }
        else{
            Log.i(ACTIVITY_NAME, "Inserting dummy data success");
        }

        contentValues = new ContentValues();
        contentValues.put(dataDBhelper.HOUSING_NAME, "Bob's House");
        contentValues.put(dataDBhelper.HOUSING_DESCRIPTION, "Bob's House");
        contentValues.put(dataDBhelper.HOUSING_CITY, "Waterloo");
        contentValues.put(dataDBhelper.HOUSING_STATE, "ON");
        contentValues.put(dataDBhelper.HOUSING_COUNTRY, "Canada");
        contentValues.put(dataDBhelper.HOUSING_LAT, "43.468500");
        contentValues.put(dataDBhelper.HOUSING_LONG, "-80.521970");
        contentValues.put(dataDBhelper.HOUSING_Address, "Waterloo");
        contentValues.put(dataDBhelper.HOUSING_PRICE, 600);
        contentValues.put(dataDBhelper.HOUSING_BEDROOM, 5);
        contentValues.put(dataDBhelper.HOUSING_BATHROOM, 2);
        contentValues.put(dataDBhelper.HOUSING_PARKING, 2);
        contentValues.put(dataDBhelper.HOUSING_UTILITIES, "hydro,heating,water");
        contentValues.put(dataDBhelper.HOUSING_AMENITIES, "laundry,dishwasher");
        contentValues.put(dataDBhelper.HOUSING_TYPE, "Room");

        result = housingdb.insert(dataDBhelper.HOUSING_TABLE_NAME, null, contentValues);
        if(result == -1){
            Log.i(ACTIVITY_NAME, "Inserting dummy data failed");
        }
        else{
            Log.i(ACTIVITY_NAME, "Inserting dummy data success");
        }

        contentValues = new ContentValues();
        contentValues.put(dataDBhelper.HOUSING_NAME, "Apartment");
        contentValues.put(dataDBhelper.HOUSING_DESCRIPTION, "The Towers of Lamb");
        contentValues.put(dataDBhelper.HOUSING_CITY, "Waterloo");
        contentValues.put(dataDBhelper.HOUSING_STATE, "ON");
        contentValues.put(dataDBhelper.HOUSING_COUNTRY, "Canada");
        contentValues.put(dataDBhelper.HOUSING_LAT, "43.466912");
        contentValues.put(dataDBhelper.HOUSING_LONG, "-80.521627");
        contentValues.put(dataDBhelper.HOUSING_Address, "Waterloo");
        contentValues.put(dataDBhelper.HOUSING_PRICE, 2500);
        contentValues.put(dataDBhelper.HOUSING_BEDROOM, 2);
        contentValues.put(dataDBhelper.HOUSING_BATHROOM, 2);
        contentValues.put(dataDBhelper.HOUSING_PARKING, 1);
        contentValues.put(dataDBhelper.HOUSING_UTILITIES, "hydro,heating,water,internet");
        contentValues.put(dataDBhelper.HOUSING_AMENITIES, "fitness,laundry,pool,ac");
        contentValues.put(dataDBhelper.HOUSING_TYPE, "Apartment");
        result = housingdb.insert(dataDBhelper.HOUSING_TABLE_NAME, null, contentValues);
        if(result == -1){
            Log.i(ACTIVITY_NAME, "Inserting dummy data failed");
        }
        else{
            Log.i(ACTIVITY_NAME, "Inserting dummy data success");
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void gethelpmenu(View view) {
        Map.AsyncTaskRunner runner = new Map.AsyncTaskRunner();
        runner.execute("menu");
    }


    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                //read data from DB
                if (params.equals("menu")) {

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }


        @Override
        protected void onPostExecute(String result) {
            //populate the map
            // Create the object of AlertDialog Builder class
            AlertDialog.Builder builder = new AlertDialog.Builder(Map.this);
            builder.setMessage("Patrick Maldonado, Sekou Diabila, Josh Gill, Daniel Faseyi. Activity Version 1.0. \n " +
                    "To use the map, simply click on the markers to populate more information about the property");
            builder.setTitle("Help Menu");
            // Set Cancelable true for when the user clicks on the outside the Dialog Box then it will not show
            builder.setCancelable(true);
            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setPositiveButton("close", (DialogInterface.OnClickListener) (dialog, which) -> {
                // When the user click yes button then app will close
                dialog.cancel();
            });
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();

        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(String... text) {

        }
    }

}