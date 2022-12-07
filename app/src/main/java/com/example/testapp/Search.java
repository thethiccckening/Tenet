package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.slider.RangeSlider;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Perform fragment transaction to bring up toolbar
            Bundle bundle = new Bundle();
            getSupportFragmentManager().beginTransaction()
            .add(R.id.navigation_bar,ToolbarFragment.class,bundle)
            .commit();
        //Fragment transaction complete
        //Instantiate all views into usable java objects
            //EditTexts
            EditText city = findViewById(R.id.city);
            EditText priceLeft = findViewById(R.id.priceSliderleft);
            EditText priceRight = findViewById(R.id.priceSliderright);
            EditText bedroomLeft = findViewById(R.id.roomSliderleft);
            EditText bedroomRight = findViewById(R.id.roomSliderright);
            EditText bathLeft = findViewById(R.id.bathSliderleft);
            EditText bathRight = findViewById(R.id.bathSliderright);
            EditText parkLeft = findViewById(R.id.parkSliderleft);
            EditText parkRight = findViewById(R.id.parkSliderright);
            //Done EditTexts

            //Sliders
            RangeSlider price = findViewById(R.id.priceSlider);
            RangeSlider rooms = findViewById(R.id.roomSlider);
            RangeSlider baths = findViewById(R.id.bathSlider);
            RangeSlider park = findViewById(R.id.parkSlider);
            //Done Sliders

            //Checkboxes
            CheckBox hydro = findViewById(R.id.hydro_checkBox);
            CheckBox heating = findViewById(R.id.heating_checkBox);
            CheckBox water = findViewById(R.id.water_checkBox);
            CheckBox internet = findViewById(R.id.wifi_checkBox);
            CheckBox cable = findViewById(R.id.cable_checkBox);
            CheckBox ac = findViewById(R.id.ac_checkBox);
            CheckBox gym = findViewById(R.id.gym_checkBox);
            CheckBox laundry = findViewById(R.id.laundry_checkBox);
            CheckBox pool = findViewById(R.id.pool_checkBox);
            CheckBox dishwasher = findViewById(R.id.dishwasher_checkBox);
            //Done Checkboxes

            //Buttons
            View clear = findViewById(R.id.clear_button);
            View search = findViewById(R.id.search);
            //Done buttons
        //Done Instantiation
        //create SharedPreferences
        SharedPreferences pref = getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //done creation

        //Create SliderTouchListeners for all RangeSliders
            price.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
                @Override
                public void onStartTrackingTouch(@NonNull RangeSlider slider) {

                }

                @Override
                public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                    priceLeft.setText(price.getValues().get(0).toString(), TextView.BufferType.EDITABLE);
                    priceRight.setText(price.getValues().get(1).toString(), TextView.BufferType.EDITABLE);
                }
            });
            rooms.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull RangeSlider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                bedroomLeft.setText(rooms.getValues().get(0).toString(), TextView.BufferType.EDITABLE);
                bedroomRight.setText(rooms.getValues().get(1).toString(), TextView.BufferType.EDITABLE);
            }
            });
            baths.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull RangeSlider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                bathLeft.setText(baths.getValues().get(0).toString(), TextView.BufferType.EDITABLE);
                bathRight.setText(baths.getValues().get(1).toString(), TextView.BufferType.EDITABLE);
            }
            });
            park.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull RangeSlider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                parkLeft.setText(park.getValues().get(0).toString(), TextView.BufferType.EDITABLE);
                parkRight.setText(park.getValues().get(1).toString(), TextView.BufferType.EDITABLE);
            }
            });
        //Done SliderTouchListener creation
        //Add onCheckChanged for checkboxes, and database component
            hydro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int _hydro = 0;
                if(hydro.isChecked()){
                    _hydro = 1;
                }
                editor.putInt("hydro",_hydro);
                //place marker into the database/sharedPref
            }
            });
            heating.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int _heating = 0;
                if(heating.isChecked()){
                    _heating = 1;
                }
                editor.putInt("heating",_heating);
                //place marker into the database/sharedPref
            }

            });
            water.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int _water = 0;
                    if(water.isChecked()){
                        _water = 1;
                    }
                    editor.putInt("water",_water);
                    //place marker into the database/sharedPref
                }

            });
            internet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int _internet = 0;
                    if(internet.isChecked()) {
                        _internet = 1;
                    }
                    editor.putInt("internet",_internet);
                    //place marker into the database/sharedPref
                }

            });
            cable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int _cable = 0;
                    if(cable.isChecked()){
                        _cable = 1;
                    }
                    editor.putInt("cable",_cable);
                    //place marker into the database/sharedPref
                }

            });
            ac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int _ac = 0;
                    if(ac.isChecked()){
                        _ac = 1;
                    }
                    editor.putInt("ac",_ac);
                    //place marker into the database/sharedPref
                }

            });
            gym.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int _gym = 0;
                    if(gym.isChecked()){
                        _gym = 1;
                    }
                    editor.putInt("gym",_gym);
                    //place marker into the database/sharedPref
                }

            });
            laundry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int _laundry = 0;
                    if(laundry.isChecked()){
                        _laundry = 1;
                    }
                    editor.putInt("laundry",_laundry);
                    //place marker into the database/sharedPref
                }
            });
            pool.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int _pool = 0;
                    if(pool.isChecked()){
                        _pool = 1;
                    }
                    editor.putInt("pool",_pool);
                    //place marker into the database/sharedPref
                }
            });
            dishwasher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int _dishwasher = 0;
                    if(dishwasher.isChecked()){
                        _dishwasher = 1;
                    }
                    editor.putInt("dishwasher",_dishwasher);
                    //place marker into the database/sharedPref
                }

            });
        //Done onCheckChanges for CheckBoxes
        //Add onClicks for clear and proceed
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //put Price Preferences in SharedPref
                editor.putString("priceLeft",priceLeft.getText().toString());
                editor.putString("priceRight",priceRight.getText().toString());

                //put Rooms Preferences in SharedPref
                editor.putString("bedroomLeft",bedroomLeft.getText().toString());
                editor.putString("bedroomRight",bedroomRight.getText().toString());

                //put Bathrooms Preferences in SharedPref
                editor.putString("bathLeft",bathLeft.getText().toString());
                editor.putString("bathRight",bathRight.getText().toString());

                //put Parking Preferences in SharedPref
                editor.putString("parkLeft",parkLeft.getText().toString());
                editor.putString("parkLeft",parkLeft.getText().toString());

                editor.commit();

                //Done SharedPref manipulation


                Intent map = new Intent(getApplicationContext(),Map.class);
                startActivity(map);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hydro.setChecked(false);
                heating.setChecked(false);
                water.setChecked(false);
                internet.setChecked(false);
                cable.setChecked(false);
                ac.setChecked(false);
                gym.setChecked(false);
                laundry.setChecked(false);
                pool.setChecked(false);
                dishwasher.setChecked(false);
                priceLeft.setText(0+"");
                priceRight.setText(5000+"");
                bedroomLeft.setText(0+"");
                bedroomRight.setText(5+"");
                bathLeft.setText(0+"");
                bathRight.setText(5+"");
                parkLeft.setText(0+"");
                parkRight.setText(5+"");
                price.setValues((float)0,(float)5000);
                rooms.setValues((float)0,(float)5);
                baths.setValues((float)0,(float)5);
                park.setValues((float)0,(float)5);
                city.setText("");
            }
        });
        //Done onClicks
    }
}