package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences preferencia = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencia.edit();

        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        TextView tvUsuario = findViewById(R.id.tvUsuario);
        TextView tvFecha = findViewById(R.id.tvFecha);

        tvUsuario.setText(preferencia.getString("usuario","Tedoro"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String fecha = preferencia.getString("fecha","PrimerIngreso");
        tvFecha.setText(fecha);
        editor.putString("fecha",sdf.format(new Date()));
        editor.apply();


        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("usuario","");
                editor.putString("sesion","no");
                editor.apply();
                Intent pantalla = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(pantalla);
            }
        });
    }
}