package com.ricopollo.lnieto.listviewwithcheckboxes;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
        android.widget.CompoundButton.OnCheckedChangeListener {

    ListView lv;
    ArrayList<Producto> planetList;
    ProductoAdaptador plAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        lv = (ListView) findViewById(R.id.listview);
        displayPlanetList();
    }

    private void displayPlanetList() {

        planetList = new ArrayList<Producto>();
        planetList.add(new Producto("Mercury", "57000000", false));
        planetList.add(new Producto("Venus", "23700000", false));
        planetList.add(new Producto("Mars", "35000000", false));
        planetList.add(new Producto("Jupiter", "5000000", false));
        planetList.add(new Producto("Saturn", "746000000", false));

        plAdapter = new ProductoAdaptador(planetList, this);
        lv.setAdapter(plAdapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int pos = lv.getPositionForView(buttonView);
        if (pos != ListView.INVALID_POSITION) {
            Producto p = planetList.get(pos);
            p.setPlEscogido(isChecked);

            Toast.makeText(
                    this,
                    "Clicked on Planet: " + p.getPcNombre() + ". State: is "
                            + isChecked, Toast.LENGTH_SHORT).show();
        }
    }
}
