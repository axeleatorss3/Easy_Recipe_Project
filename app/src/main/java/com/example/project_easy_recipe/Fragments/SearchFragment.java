package com.example.project_easy_recipe.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_easy_recipe.Adapter.ListaRecetasAdapter;
import com.example.project_easy_recipe.R;
import com.example.project_easy_recipe.models.Recipe;
import com.example.project_easy_recipe.models.SpoontacularRespuesta;
import com.example.project_easy_recipe.spoonApi.SpoontacularApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Retrofit retrofit;
    RecyclerView recyclerView;
    ArrayList<Recipe> list;
    private static final String TAG ="receta";
    private ListaRecetasAdapter listaRecetasAdapter;
    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        listaRecetasAdapter = new ListaRecetasAdapter(getContext());
        recyclerView.setAdapter(listaRecetasAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager  gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerdatos("apple");
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Recipes");
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("", "hoal");
                listaRecetasAdapter.removeList();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                obtenerdatos(query);
                Log.wtf("query", "" + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //listaRecetasAdapter.getFilter().filter(newText);

                return false;
            }


        });

    }

    public void obtenerdatos(String receta){
        SpoontacularApiService service = retrofit.create(SpoontacularApiService.class);
        Call<SpoontacularRespuesta> spoontacularRespuestaCall= service.obtenerReceta("ce5dc91a6b264334bfbb5bb20ba93b3c",receta);
        spoontacularRespuestaCall.enqueue(new Callback<SpoontacularRespuesta>() {

            @Override
            public void onResponse(Call<SpoontacularRespuesta> call, Response<SpoontacularRespuesta> response) {
                if(response.isSuccessful()){
                    SpoontacularRespuesta spoontacularRespuesta = response.body();
                    ArrayList<Recipe> listRecipe = spoontacularRespuesta.getResults();
                    listaRecetasAdapter.adicionarListaRecetas(listRecipe);

                }else{
                    Log.e(TAG,"onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SpoontacularRespuesta> call, Throwable t) {
                Log.e(TAG,"on failure: "+ t.getMessage());
            }
        });

    }




}