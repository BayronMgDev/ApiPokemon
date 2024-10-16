package com.proyectofinal.pokemonapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proyectofinal.pokemonapi.Common.Common;
import com.proyectofinal.pokemonapi.Interface.IItemClickListener;
import com.proyectofinal.pokemonapi.Model.Evolution;
import com.proyectofinal.pokemonapi.R;
import com.robertlevonyan.views.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class PokemonEvolutionAdapter extends RecyclerView.Adapter<PokemonEvolutionAdapter.MyViewHolder> {

    Context context;
    List<Evolution> evolutions;

    public PokemonEvolutionAdapter(Context context, List<Evolution> evolutions) {
        this.context = context;
        if (evolutions != null)
            this.evolutions = evolutions;
        else
            this.evolutions = new ArrayList<>();//Corregir fallo si el Pokémon no tiene evolución previa o siguiente
    }

    @NonNull
    @Override
    public PokemonEvolutionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false);
        return new PokemonEvolutionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonEvolutionAdapter.MyViewHolder holder, int position) {
        holder.chip.setText(evolutions.get(position).getName());
        holder.chip.setChipBackgroundColor(
                Common.getColorByType(
                        Common.findPokemonByNum(
                                evolutions.get(position).getNum()
                        ).getType()
                                .get(0)
                )
        );

    }
    @Override
    public int getItemCount() {
        return evolutions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        Chip chip;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            chip = (Chip)itemView.findViewById(R.id.chip);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LocalBroadcastManager.getInstance(context)
                            .sendBroadcast(new Intent(Common.KEY_NUM_EVOLUTION).putExtra("num",evolutions.get(getAdapterPosition()).getNum()));
                }
            });
        }
    }
}

