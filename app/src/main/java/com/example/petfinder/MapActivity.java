package com.example.petfinder;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker currentMarker;
    private static final LatLng SAO_PAULO = new LatLng(-23.550520, -46.633308);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Configurar o mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Configurar botão de confirmar
        Button confirmButton = findViewById(R.id.confirmLocationButton);
        confirmButton.setOnClickListener(v -> {
            if (currentMarker != null) {
                returnLocation(currentMarker.getPosition());
            } else {
                Toast.makeText(this, "Selecione um local no mapa", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Configurar o mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SAO_PAULO, 12));

        // Configurar clique no mapa
        mMap.setOnMapClickListener(this::updateMarker);
    }

    private void updateMarker(LatLng position) {
        if (currentMarker != null) {
            currentMarker.remove();
        }
        currentMarker = mMap.addMarker(new MarkerOptions()
                .position(position)
                .title("Localização selecionada"));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(position));
    }

    private void returnLocation(LatLng position) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("latitude", position.latitude);
        resultIntent.putExtra("longitude", position.longitude);
        
        // Tentar obter o endereço usando Geocoding
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                StringBuilder addressText = new StringBuilder();
                
                // Adicionar os componentes do endereço disponíveis
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    if (i > 0) addressText.append(", ");
                    addressText.append(address.getAddressLine(i));
                }
                
                resultIntent.putExtra("address", addressText.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultIntent.putExtra("address", String.format(Locale.getDefault(),
                    "%.6f, %.6f", position.latitude, position.longitude));
        }

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
