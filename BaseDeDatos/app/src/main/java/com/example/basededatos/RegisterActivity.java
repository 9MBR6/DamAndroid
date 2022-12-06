package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    EditText email, userName, firstName, lastName, password, rePassword;
    Button btnRegister, btnReturn;
    ImageView imagenRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        iniciarOnCreate();
    }
    public void iniciarOnCreate(){
        email = findViewById(R.id.campEmail);
        userName = findViewById(R.id.campUserName);
        firstName = findViewById(R.id.campFirstName);
        lastName = findViewById(R.id.campLastName);
        password = findViewById(R.id.campRegisterPass);
        rePassword = findViewById(R.id.campRegisterPassRe);
        btnRegister = findViewById(R.id.btnRegister);
        btnReturn = findViewById(R.id.btnExit);
        imagenRegistro = findViewById(R.id.imagenRegistro);
        animacionBotones();
        animacioncampos();
        animacionLogo();
    }

    public void registrar(View v) {
        if (password.getText().toString().equals(rePassword.getText().toString())) {
            if (!password.getText().toString().equals("") && !rePassword.getText().toString().equals("")) {
                AdminSQLITE admindb = new AdminSQLITE(this, "base", null, 1);
                SQLiteDatabase db = admindb.getWritableDatabase();

                String cp_email = email.getText().toString();
                String cp_userName = userName.getText().toString();
                String cp_firstName = firstName.getText().toString();
                String cp_lastName = lastName.getText().toString();
                String cp_password = password.getText().toString();

                if (!cp_email.isEmpty() && !cp_firstName.isEmpty() && !cp_password.isEmpty()
                        && !cp_lastName.isEmpty() && !cp_userName.isEmpty()) {
                    ContentValues registro = new ContentValues();
                    registro.put("email", cp_email);
                    registro.put("userName", cp_userName);
                    registro.put("firstName", cp_firstName);
                    registro.put("lastName", cp_lastName);
                    registro.put("password", cp_password);
                    db.insert("usuarios", null, registro);
                    db.close();

                    Toast.makeText(this, "El registro se completo con exito", Toast.LENGTH_SHORT).show();


                    email.setText("");
                    userName.setText("");
                    firstName.setText("");
                    lastName.setText("");
                    password.setText("");
                    rePassword.setText("");

                    onBackPressed();

                } else {
                    Toast.makeText(this, "Alguno de los campos esta en blanco", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"Las contraseñas estan en blanco", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show();
        }
    }

    public void salir(View v){
        onBackPressed();
    }

    public void animacionLogo(){
        ObjectAnimator logoAnimacion = ObjectAnimator.ofFloat(imagenRegistro, View.ALPHA, 0.0f, 1.0f);
        logoAnimacion.setDuration(2000);
        AnimatorSet animationSetAlpha = new AnimatorSet();
        animationSetAlpha.play(logoAnimacion);
        animationSetAlpha.start();
    }

    public void animacioncampos(){
        //en pc 350 en movil 260
        ArrayList<EditText> editt = new ArrayList<>();
        editt.add(email);
        editt.add(userName);
        editt.add(firstName);
        editt.add(lastName);
        editt.add(password);
        editt.add(rePassword);

        for (int i = 0; i < editt.size(); i++) {
            ObjectAnimator campopassw = ObjectAnimator.ofFloat(editt.get(i), "x", 260f);
            campopassw.setDuration(2000);
            AnimatorSet animatorpass = new AnimatorSet();
            animatorpass.play(campopassw);
            animatorpass.start();
        }


    }

    public void animacionBotones(){
        ObjectAnimator logoAnimacion = ObjectAnimator.ofFloat(btnReturn, View.ALPHA, 0.0f, 1.0f);
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
}