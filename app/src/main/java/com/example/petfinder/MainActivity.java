package com.example.petfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button lostPetButton;
    private Button foundPetButton;
    private Button listingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Verificar se já está logado
        if (!isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        
        setContentView(R.layout.activity_menu_principal);
        initializeViews();
        setupClickListeners();
    }

    private boolean isLoggedIn() {
        // TODO: Implementar verificação de login real
        return getIntent().hasExtra("FROM_LOGIN");
    }

    private void initializeViews() {
        lostPetButton = findViewById(R.id.lostPetButton);
        foundPetButton = findViewById(R.id.foundPetButton);
        listingsButton = findViewById(R.id.listingsButton);
    }

    private void setupClickListeners() {
        lostPetButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, LostPetActivity.class);
            startActivity(intent);
        });

        foundPetButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, FoundPetActivity.class);
            startActivity(intent);
        });

        listingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PetsMapActivity.class);
            startActivity(intent);
        });
    }
}