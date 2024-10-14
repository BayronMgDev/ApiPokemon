package com.proyectofinal.pokemonapi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.proyectofinal.pokemonapi.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.TypeViewHolder> {
    Context context;
    List<String> types;
    public PokemonTypeAdapter(Context context, List<String> types) {
        this.context = context;
        this.types = types != null ? types : new ArrayList<>();
    }
    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false);
        return new TypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TypeViewHolder holder, int position) {
        String type = types.get(position);
        holder.chipTextView.setText(type);
    }

    @Override
    public int getItemCount() {
        return types.size();
    }
    public class TypeViewHolder extends RecyclerView.ViewHolder {
        public TextView chipTextView;

        public TypeViewHolder(View itemView) {
            super(itemView);
            chipTextView = (TextView) itemView.findViewById(R.id.chip);
        }
    }
}
