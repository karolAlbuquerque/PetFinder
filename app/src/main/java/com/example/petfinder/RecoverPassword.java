package com.example.petfinder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RecoverPassword extends AppCompatActivity {

    private EditText editEmailRecover;
    private Button btnRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        editEmailRecover = findViewById(R.id.editTextEmailRecover);
        btnRecuperar = findViewById(R.id.btnRecuperar);

        btnRecuperar.setOnClickListener(v -> {
            String email = editEmailRecover.getText().toString();

            if(email.isEmpty()) {
                Toast.makeText(this, "Digite seu email", Toast.LENGTH_SHORT).show();
            } else {
                // Lógica de envio de email de recuperação
                Toast.makeText(this, "Email de recuperação enviado!", Toast.LENGTH_SHORT).show();
                finish(); // Volta para tela de login
            }
        });
    }
}
