package com.example.project_easy_recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.project_easy_recipe.Adapter.ListaRecetasAdapter;
import com.example.project_easy_recipe.Fragments.DetalleFragment;
import com.example.project_easy_recipe.Fragments.HomeFragment;
import com.example.project_easy_recipe.Fragments.SearchFragment;
import com.example.project_easy_recipe.models.Recipe;
import com.example.project_easy_recipe.models.SpoontacularRespuesta;
import com.example.project_easy_recipe.spoonApi.SpoontacularApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "imageURL";
    public static final String EXTRA_TITLE= "titleURL";
    public static final String EXTRA_ID= "numId";
    private Retrofit retrofit;
    private static final String TAG ="receta";
    private RecyclerView recyclerView;
    private ListaRecetasAdapter listaRecetasAdapter;
    private ArrayList<Recipe> listReci;
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private DetalleFragment detalleFragment;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        bottomNavigationView = findViewById(R.id.bottom_navi);
        frameLayout = findViewById(R.id.frm_layout);
        setFrag(homeFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home: setFrag(homeFragment);
                        return true;
                    case R.id.search: setFrag(searchFragment);
                        return true;
                }
                return false;
            }
        });
       /* recyclerView = findViewById(R.id.recyclerView);
        buildRecylcerView();
        listReci = new ArrayList<>();*/
    }
    private void setFrag(Fragment frag){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frm_layout, frag);
        fragmentTransaction.commit();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment.getClass() == HomeFragment.class){
            homeFragment = (HomeFragment) fragment;
        }
        else if(fragment.getClass() == SearchFragment.class){
            searchFragment = (SearchFragment) fragment;
        }else if(fragment.getClass() == DetalleFragment.class){
            detalleFragment = (DetalleFragment) fragment;
        }
    }
    public void onMessageFromFragToMain(String sender, String param){
        if(sender.equals("lista")){
            searchFragment.onMessageFromMaintoFrag(param);

        }

    }

}