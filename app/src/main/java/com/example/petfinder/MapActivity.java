package com.example.petfinder;

import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        Button btnAdicionarPet = findViewById(R.id.btnAdicionarPet);
        btnAdicionarPet.setOnClickListener(v -> {
            // Ação para abrir tela de adicionar pet
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Exemplo de marcador
        LatLng exemplo = new LatLng(-23.5505, -46.6333); // São Paulo
        mMap.addMarker(new MarkerOptions().position(exemplo).title("Pet visto aqui!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(exemplo, 12));
    }
}
