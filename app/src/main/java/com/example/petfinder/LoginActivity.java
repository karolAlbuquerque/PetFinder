package com.example.petfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnLogin, btnRegister;
    private TextView textRecover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editTextEmail);
        editSenha = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        textRecover = findViewById(R.id.textRecover);

        btnLogin.setOnClickListener(v -> {
            // Simulação de login
            String email = editEmail.getText().toString();
            String senha = editSenha.getText().toString();
            if(email.equals("teste@email.com") && senha.equals("123456")) {
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                // Ir para próxima activity
            } else {
                Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class))
        );

        textRecover.setOnClickListener(v ->
                startActivity(new Intent(this, RecoverPassword.class))
        );
    }
}
