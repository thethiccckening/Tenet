package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Instantiate all views into usable java objects
            //EditTexts
            EditText city = findViewById(R.id.city);
            EditText rentLeft = findViewById(R.id.priceSliderleft);
            EditText rentRight = findViewById(R.id.priceSliderright);
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
    }
}