package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText campUser, campPassw;
    TextView txtContraOlvidada;
    ImageView imagenLogo;
    Button btnRegister, btnLogin;

   // int contador=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarOnCreate();

    }

    public void iniciarOnCreate(){
        campUser = findViewById(R.id.campUser);
        campPassw = findViewById(R.id.campPassword);
        txtContraOlvidada = findViewById(R.id.txtContraOlvidada);
        imagenLogo = findViewById(R.id.imgLogo);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        animacionLogo();
        animacioncampos();
        animacionBotones();
    }

    public void comprobarUsuario(View v) {
        AdminSQLITE admin = new AdminSQLITE(this, "base", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String user = campUser.getText().toString();
        String pass = campPassw.getText().toString();

        if (!user.isEmpty() && !pass.isEmpty()) {
            String consulta = "select password,userName,firstName,lastName,email from usuarios where userName like " + "'" + user + "'";
            Cursor fila = db.rawQuery(consulta, null);

            if (fila.moveToFirst()) {
                //funciona!!!!!!!!
                if (pass.equals(fila.getString(0))) {
                    campPassw.setText("");
                    campUser.setText("");
                    txtContraOlvidada.setVisibility(View.INVISIBLE);
                    //colocar aqui el indent y mandar la informacion para otra ventana
                    Intent intent = new Intent(this, DatosActivity.class);
                    intent.putExtra("user",fila.getString(1));
                    intent.putExtra("pass",fila.getString(0));
                    intent.putExtra("lastName",fila.getString(3));
                    intent.putExtra("firstName",fila.getString(2));
                    intent.putExtra("email",fila.getString(4));
                    startActivity(intent);

                }
            } else {
                Toast.makeText(this, "El usuario o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
                txtContraOlvidada.setVisibility(View.VISIBLE);
            }
            db.close();
        } else {
            Toast.makeText(this, "Revisa que los campos no esten sin rellenar", Toast.LENGTH_SHORT).show();
            txtContraOlvidada.setVisibility(View.VISIBLE);
        }
    }

    public void contrasenaOlvidada(View v){
        AdminSQLITE admin = new AdminSQLITE(this, "base", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String user = campUser.getText().toString();
        if (!user.isEmpty()) {

            String consulta = "select password from usuarios where userName like " + "'" + user + "'";
            Cursor fila = db.rawQuery(consulta, null);

            if (fila.moveToFirst()) {

                Toast.makeText(this, "Contraseña: "+fila.getString(0), Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "El nombre de usuario no existe en la base de datos", Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(this, "Escribe un nombre de usuario", Toast.LENGTH_SHORT).show();
        }


    }

    public void accederRegistro(View v) {
        campPassw.setText("");
        campUser.setText("");
        txtContraOlvidada.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
    public void animacionLogo(){
        ObjectAnimator logoAnimacion = ObjectAnimator.ofFloat(imagenLogo, View.ALPHA, 0.0f, 1.0f);
        logoAnimacion.setDuration(2000);
        AnimatorSet animationSetAlpha = new AnimatorSet();
        animationSetAlpha.play(logoAnimacion);
        animationSetAlpha.start();
    }
    public void animacionBotones(){
        ObjectAnimator logoAnimacion = ObjectAnimator.ofFloat(btnLogin, View.ALPHA, 0.0f, 1.0f);
        logoAnimacion.setDuration(2000);
        AnimatorSet animationSetAlpha = new AnimatorSet();
        animationSetAlpha.play(logoAnimacion);
        animationSetAlpha.start();

        ObjectAnimator btnregis = ObjectAnimator.ofFloat(btnRegister, View.ALPHA, 0.0f, 1.0f);
        btnregis.setDuration(2000);
        AnimatorSet animatorRegister = new AnimatorSet();
        animatorRegister.play(btnregis);
        animatorRegister.start();
    }

    public void animacioncampos(){
        //en pc 350 en movil 260
        ObjectAnimator campoUser = ObjectAnimator.ofFloat(campUser, "x", 260f);
        campoUser.setDuration(2000);
        AnimatorSet animatorUser = new AnimatorSet();
        animatorUser.play(campoUser);
        animatorUser.start();

        ObjectAnimator campopassw = ObjectAnimator.ofFloat(campPassw, "x", 260f);
        campopassw.setDuration(2000);
        AnimatorSet animatorpass = new AnimatorSet();
        animatorpass.play(campopassw);
        animatorpass.start();
    }
}