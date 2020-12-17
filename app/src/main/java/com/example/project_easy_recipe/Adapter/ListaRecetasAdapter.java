package com.example.project_easy_recipe.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.project_easy_recipe.Fragments.DetalleFragment;
import com.example.project_easy_recipe.MainActivity;
import com.example.project_easy_recipe.R;
import com.example.project_easy_recipe.models.Recipe;


import java.util.ArrayList;
import java.util.Collection;

public class ListaRecetasAdapter extends RecyclerView.Adapter<com.example.project_easy_recipe.Adapter.ListaRecetasAdapter.ViewHolders> implements Filterable {

    private ArrayList<Recipe> dataset;
    private ArrayList<Recipe> datasetAll;
    private onItemClickListener mlistener;
    private Context context;
    private DetalleFragment detalleFragment;
    MainActivity mainActivity;

    public interface onItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mlistener = listener;
    }


    public ListaRecetasAdapter(Context c) {
        this.context = c;
        dataset = new ArrayList<>();
        datasetAll = dataset;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);
        ViewHolders evh = new ViewHolders(view, mlistener);
        return evh;
    }


    @Override
    public void onBindViewHolder(@NonNull com.example.project_easy_recipe.Adapter.ListaRecetasAdapter.ViewHolders holder, int position) {

        Recipe r = dataset.get(position);
        holder.nombreTextView.setText(r.getTitle());
        holder.ID.setText(String.valueOf(r.getId()));
        Glide.with(context).load(r.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.fotoImageView);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaRecetas(ArrayList<Recipe> listRecipe) {
        dataset.addAll(listRecipe);
        notifyDataSetChanged();
    }

    public void removeList() {
        dataset.clear();

    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Recipe> filteredList = new ArrayList<>();
            if (constraint.toString().isEmpty()) {
                filteredList.addAll(datasetAll);
            } else {
                for (Recipe recipe : dataset) {
                    if (recipe.getTitle().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(recipe);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataset.clear();
            dataset.addAll((Collection<? extends Recipe>) results.values);
            notifyDataSetChanged();
        }
    };

    public void getId(int position) {
        Recipe r = dataset.get(position);
        int id = r.getId();
        Log.wtf("getId", "Id: " + id);
        DetalleFragment detalleFragment = new DetalleFragment();
        detalleFragment.setId(String.valueOf(id));
    }

    public static class ViewHolders extends RecyclerView.ViewHolder {
        private ImageView fotoImageView;
        private TextView nombreTextView;
        private TextView ID;


        public ViewHolders(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            ID = itemView.findViewById(R.id.recetaID);
            fotoImageView = itemView.findViewById(R.id.fotoImageView);
            nombreTextView = itemView.findViewById(R.id.nombreTxtView);
            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();


                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);

                        }
                    }
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    DetalleFragment detalleFragment = new DetalleFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout, detalleFragment).addToBackStack(null).commit();

                    Log.d("LogClickViewHolder", "Fondo clicked" + ID.getText());

                }
            });
        }
    }


}
