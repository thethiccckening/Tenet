package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ToolbarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.toolbar_fragment, container, false);
        //Layout Inflated

        //Instantiate ImageViews to be used as buttons
        ImageView map = view.findViewById(R.id.map_button);
        ImageView search = view.findViewById(R.id.search_button);
        ImageView chat = view.findViewById(R.id.chat_button);
        ImageView settings = view.findViewById(R.id.settings_button);
        //ImageViews instantiated

        //Set onClickListeners for all ImageView buttons
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map1 = new Intent(getActivity(),Map.class);
                startActivity(map1);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search1 = new Intent(getActivity(),Search.class);
                startActivity(search1);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chat1 = new Intent(getActivity(),Chat.class);
                startActivity(chat1);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings1 = new Intent(getActivity(),Settings.class);
                startActivity(settings1);
            }
        });
        //Done setting onClickListeners
        return view;
        //return view to be used as a fragment


    }
}