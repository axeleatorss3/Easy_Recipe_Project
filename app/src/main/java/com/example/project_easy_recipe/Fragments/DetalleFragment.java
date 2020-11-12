package com.example.project_easy_recipe.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project_easy_recipe.R;
import com.example.project_easy_recipe.models.RecipeDetail;
import com.example.project_easy_recipe.spoonApi.SpoontacularApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleFragment extends Fragment {
    public static String newId;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "recetaDetalle";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleFragment newInstance(String param1, String param2) {
        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TextView txtTitulo;
    TextView txtTiempo;
    TextView txtPorciones;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    public void setId(String id){
        newId = id;
        Log.wtf("Id","Id es:"+id);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtTiempo = view.findViewById(R.id.txtTiempo);
        txtPorciones = view.findViewById(R.id.txtPorciones);

        return view;

    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            find(newId);

    }


    private void find(String codigo){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        SpoontacularApiService spoontacularApiService = retrofit.create(SpoontacularApiService.class);
        Call<RecipeDetail> call = spoontacularApiService.find(codigo);
        call.enqueue(new Callback<RecipeDetail>() {
            @Override
            public void onResponse(Call<RecipeDetail> call, Response<RecipeDetail> response) {
                try{
                    if(response.isSuccessful()){
                        RecipeDetail r = response.body();
                        txtTitulo.setText(r.getTitle());
                        txtTiempo.setText(String.valueOf(r.getReadyInMinutes()));
                        txtPorciones.setText(String.valueOf(r.getServings()));


                    }
                }catch (Exception ex){
                    Log.e(TAG,"onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RecipeDetail> call, Throwable t) {
                Log.e(TAG,"on failure: "+ t.getMessage());
            }
        });
    }



}