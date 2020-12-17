package com.example.project_easy_recipe.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.project_easy_recipe.MainActivity;
import com.example.project_easy_recipe.R;

/**
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private ImageButton btnSalds, btnBreakfast, btnDinner, btnLunch, btnVegan, btnRandom, btnDessert, btnLowCarbs;
    private String recipeType;
    private SearchFragment searchFragment;
    MainActivity mainActivity;

    public HomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnSalds = view.findViewById(R.id.btnSalad);
        btnBreakfast = view.findViewById(R.id.btnBreakFast);
        btnDessert = view.findViewById(R.id.btnDessert);
        btnDinner = view.findViewById(R.id.btnDinner);
        btnLunch = view.findViewById(R.id.btnLunch);
        btnVegan = view.findViewById(R.id.btnVegan);
        btnRandom = view.findViewById(R.id.btnRandom);
        btnLowCarbs = view.findViewById(R.id.btnLow);
        btnBreakfast.setOnClickListener(this);
        btnSalds.setOnClickListener(this);
        btnDessert.setOnClickListener(this);
        btnLunch.setOnClickListener(this);
        btnDinner.setOnClickListener(this);
        btnLunch.setOnClickListener(this);
        btnLowCarbs.setOnClickListener(this);
        btnVegan.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
        return view;
    }

    public void FragmentsTrans(String s) {
        SearchFragment searchFragment = new SearchFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frm_layout, searchFragment);
        fragmentTransaction.commit();
        mainActivity.onMessageFromFragToMain("lista", s);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSalad:
                FragmentsTrans("salad");
                break;
            case R.id.btnBreakFast:
                FragmentsTrans("breakfast");
                break;
            case R.id.btnDessert:
                FragmentsTrans("desserts");
                break;
            case R.id.btnLow:
                FragmentsTrans("healthy");
                break;
            case R.id.btnLunch:
                FragmentsTrans("Meal");
                break;
            case R.id.btnVegan:
                FragmentsTrans("Vegan");
                break;
            case R.id.btnDinner:
                FragmentsTrans("Dinner");
                break;
            case R.id.btnRandom:
                FragmentsTrans("");
                break;
        }
    }
}