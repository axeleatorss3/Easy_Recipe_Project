package com.example.project_easy_recipe.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.project_easy_recipe.Fragments.DetalleFragment;
import com.example.project_easy_recipe.Fragments.MyRecipesFragment;
import com.example.project_easy_recipe.R;
import com.example.project_easy_recipe.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class MyRecipeAdapter extends RecyclerView.Adapter<MyRecipeAdapter.ViewHolder> {
    private ArrayList<Recipe> dataset;
    private ArrayList<Recipe> datasetAll;
    private Context context;
    private DetalleFragment detalleFragment;
    private ListaRecetasAdapter.onItemClickListener mlistener;

    public interface onItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(ListaRecetasAdapter.onItemClickListener listener) {
        mlistener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mlistener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listaItems.get(position).getTitle());

        Glide.with(MyRecipesFragment.context).load(listaItems.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
    }

    public MyRecipeAdapter(Context c) {
        this.context = c;
        dataset = new ArrayList<>();
        datasetAll = dataset;
    }


    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public void getId(int position) {

        int id = listaItems.get(position).getId();
        Log.wtf("getId2", "Id2: " + id);
        DetalleFragment detalleFragment = new DetalleFragment();
        detalleFragment.setId(String.valueOf(id));


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, id;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView, final ListaRecetasAdapter.onItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.nombreTxtView);
            id = itemView.findViewById(R.id.recetaID);
            imageView = itemView.findViewById(R.id.fotoImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();


                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);

                        }
                    }
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    DetalleFragment detalleFragment = new DetalleFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout, detalleFragment).addToBackStack(null).commit();
                }
            });
        }
    }

    public List<Recipe> listaItems;

    public MyRecipeAdapter(List<Recipe> listaItems) {
        this.listaItems = listaItems;
    }
}
