package com.example.project_easy_recipe.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_easy_recipe.Adapter.ListaRecetasAdapter;
import com.example.project_easy_recipe.Adapter.MyRecipeAdapter;
import com.example.project_easy_recipe.Database.RecipeDB;
import com.example.project_easy_recipe.MainActivity;
import com.example.project_easy_recipe.R;
import com.example.project_easy_recipe.models.Recipe;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyRecipesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyRecipesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    MyRecipeAdapter myRecipeAdapter;
    MainActivity mainActivity;
    ArrayList<Recipe> list;
    public static Context context;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyRecipesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyRecipesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyRecipesFragment newInstance(String param1, String param2) {
        MyRecipesFragment fragment = new MyRecipesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_recipes, container, false);
        recyclerView = view.findViewById(R.id.recyclerMR);
        RecipeDB recipeDB = new RecipeDB(getContext());
        myRecipeAdapter = new MyRecipeAdapter(recipeDB.mostrarDatos());
        recyclerView.setAdapter(myRecipeAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        myRecipeAdapter.setOnItemClickListener(new ListaRecetasAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.wtf("onitme","ci"+position);
                myRecipeAdapter.getId(position);
            }
        });
        return view;
    }
}