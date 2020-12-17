package com.example.project_easy_recipe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_easy_recipe.R;
import com.example.project_easy_recipe.models.Ingredients;

import java.util.ArrayList;

public class ListIngredientAdapter extends RecyclerView.Adapter<ListIngredientAdapter.ViewHolder> {
    private ArrayList<Ingredients> dataset;
    private Context context;

    public ListIngredientAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListIngredientAdapter.ViewHolder holder, int position) {
        Ingredients i = dataset.get(position);
        holder.txtIngredient.setText(i.getOriginal());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarIngredientes(ArrayList<Ingredients> listaIngredientes) {
        dataset.addAll(listaIngredientes);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtIngredient;

        public ViewHolder(View itemView) {
            super(itemView);
            txtIngredient = itemView.findViewById(R.id.txtIngredient);
        }
    }
}
