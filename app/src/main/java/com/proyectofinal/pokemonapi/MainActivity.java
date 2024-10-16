package com.proyectofinal.pokemonapi;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.view.MenuItem;
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

    BroadcastReceiver showEvolution = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().toString().equals(Common.KEY_NUM_EVOLUTION))
            {
                //Reemplaza el Fragment

                Fragment detailFragment = PokemonDetail.getInstance();
                Bundle bundle = new Bundle();
                String num = intent.getStringExtra("num");
                bundle.putString("num",num);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.remove(detailFragment);//Eliminar el fragmento actual cuando presiona en la evolucion
                fragmentTransaction.replace(R.id.listPokemon_fragment,detailFragment);
                fragmentTransaction.addToBackStack("detail");
                fragmentTransaction.commit();

                //Establecer el nombre del Pokémon para la barra de herramientas

                Pokemon pokemon = Common.findPokemonByNum(num);
                toolbar.setTitle(pokemon.getName());
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("LISTA DE POKEMONES");
        setSupportActionBar(toolbar);

        //Registrar BroadCast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetail, new IntentFilter(Common.KEY_ENEABLE_HOME));

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showEvolution, new IntentFilter(Common.KEY_NUM_EVOLUTION));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                toolbar.setTitle("LISTA DE POKEMONES");
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