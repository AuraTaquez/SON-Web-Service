package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre, txtPASS;

    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtPASS= (EditText)findViewById(R.id.txtPass);
        b = this.getIntent().getExtras();
    }
    public void btEnviar(View view){
        Intent intent = new Intent(MainActivity.this, Saludo.class);
        b = new Bundle();
        if (txtNombre.getText().length()==0||txtPASS.getText().length()==0){
            Toast.makeText(getApplicationContext(), "Debe ingresar datos en ambos campos",Toast.LENGTH_LONG).show();
        }
        else   {
            b.putString("usr",   txtNombre.getText().toString());
            b.putString("pass", txtPASS.getText().toString());
            intent.putExtras(b);
            startActivity(intent);
        }
    }
}