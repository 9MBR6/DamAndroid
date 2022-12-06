package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DatosActivity extends AppCompatActivity {
    TextView nombre, apellido, usuario, contra, correo;
    ImageView imagenUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        iniciarOnCreate();

    }

    public void iniciarOnCreate() {
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        usuario = findViewById(R.id.txtUser);
        contra = findViewById(R.id.txtpass);
        correo = findViewById(R.id.txtCorreo);
        imagenUsuario = findViewById(R.id.imagenUsuario);

        usuario.setText(getIntent().getStringExtra("user"));
        contra.setText(getIntent().getStringExtra("pass"));
        apellido.setText(getIntent().getStringExtra("lastName"));
        nombre.setText(getIntent().getStringExtra("firstName"));
        correo.setText(getIntent().getStringExtra("email"));
        animacionLogo();
    }

    public void animacionLogo() {
        ObjectAnimator logoAnimacion = ObjectAnimator.ofFloat(imagenUsuario, "rotation", 0, 360);
        logoAnimacion.setDuration(2000);
        AnimatorSet animationSetAlpha = new AnimatorSet();
        animationSetAlpha.play(logoAnimacion);
        animationSetAlpha.start();
    }

}