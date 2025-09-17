package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etPassword = findViewById(R.id.etPassword);
        CheckBox cbSesion = findViewById(R.id.cbSesion);
        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        SharedPreferences preferencia = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencia.edit();

        if(preferencia.getString("sesion","no").equals("si")){
            etUsuario.setText(preferencia.getString("usuario",""));
            etPassword.setText("");
            etPassword.setFocusable(true);
            Intent pantalla = new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(pantalla);
        }
        else{
            etUsuario.setText("");
            etPassword.setText("");
            etUsuario.setFocusable(true);
        }

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPassword.getText().toString().equals("1234")&&etUsuario.getText().toString().equals("Android")){
                    //iniciar un activity
                    Intent pantalla = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(pantalla);

                    editor.putString("usuario",etUsuario.getText().toString());

                    if(cbSesion.isChecked()){
                        editor.putString("sesion","si");
                    }
                    else{
                        etUsuario.setText("");
                    }
                    etPassword.setText("");
                    editor.apply();
                }
                else{
                    etUsuario.setText("");
                    etPassword.setText("");
                    etUsuario.setFocusable(true);
                    Toast.makeText(getApplicationContext(),"DATOS INCORRECTOS",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etPassword = findViewById(R.id.etPassword);

        SharedPreferences preferencia = getSharedPreferences("datos", Context.MODE_PRIVATE);

        if(preferencia.getString("sesion","no").equals("si")){
            etUsuario.setText(preferencia.getString("usuario",""));
            etPassword.setText("");
            etPassword.setFocusable(true);
        }
        else{
            etUsuario.setText("");
            etPassword.setText("");
            etUsuario.setFocusable(true);
        }
    }
}