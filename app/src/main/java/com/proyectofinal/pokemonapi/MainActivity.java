package com.proyectofinal.pokemonapi;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.proyectofinal.pokemonapi.Common.Common;
import com.proyectofinal.pokemonapi.Model.Pokemon;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    BroadcastReceiver showDetail = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().toString().equals(Common.KEY_ENEABLE_HOME))
            {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);// Habilitar el botón Atrás en la Barra de Herramientas
                getSupportActionBar().setDisplayShowHomeEnabled(true);

                //Reemplaza el Fragment

                Fragment detailFragment = PokemonDetail.getInstance();
                int position = intent.getIntExtra("position", -1 );
                        Bundle bundle = new Bundle();
                        bundle.putInt("position", position);
                        detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listPokemon_fragment,detailFragment);
                fragmentTransaction.addToBackStack("detail");
                fragmentTransaction.commit();

                //Establecer el nombre del Pokémon para la barra de herramientas

                Pokemon pokemon = Common.commonPokemonList.get(position);
                toolbar.setTitle(pokemon.getName());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("POKEMON LIST");
        setSupportActionBar(toolbar);

        //Registrar BroadCast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetail, new IntentFilter(Common.KEY_ENEABLE_HOME));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                toolbar.setTitle("POKEMON LIST");
                //Limpiar todos los detalles del fragment y volver al fragment de lista
                getSupportFragmentManager().popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);

                break;
            default:
                break;
        }
        return true;
    }
}