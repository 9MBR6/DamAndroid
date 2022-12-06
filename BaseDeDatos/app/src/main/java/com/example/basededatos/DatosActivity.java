package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DatosActivity extends AppCompatActivity {
    TextView nombre,apellido,usuario,contra,correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        nombre=findViewById(R.id.txtNombre);
        apellido= findViewById(R.id.txtApellido);
        usuario = findViewById(R.id.txtUser);
        contra = findViewById(R.id.txtpass);
        correo = findViewById(R.id.txtCorreo);

        usuario.setText(getIntent().getStringExtra("user"));
        contra.setText(getIntent().getStringExtra("pass"));
        apellido.setText(getIntent().getStringExtra("lastName"));
        nombre.setText(getIntent().getStringExtra("firstName"));
        correo.setText(getIntent().getStringExtra("email"));

    }
}