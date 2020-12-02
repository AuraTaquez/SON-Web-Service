package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica3.Retrofit.ResponseRetrofit;
import com.example.practica3.Retrofit.ServicesMain;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.json.JSONException;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Saludo extends AppCompatActivity implements Asynchtask {
    private TextView txtBancos;
    private TextView txtSaludo;
    private  Bundle bundle;
    private RequestQueue queue ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo2);
        bundle = this.getIntent().getExtras();
        queue = Volley.newRequestQueue(this);
        LgVolley();
        getService();

    }
    private void getService() {
        String url ="https://api-uat.kushkipagos.com/transfer-subscriptions/v1/";
        txtBancos = (TextView)findViewById(R.id.txtListaBancos);
        Retrofit retrofit= new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        ServicesMain servicios = retrofit.create(ServicesMain.class);
        Call<List<ResponseRetrofit>> response= servicios.getNamesBanks("583a25839fbe467abbe5d1a1c5556428");
        response.enqueue(new Callback<List<ResponseRetrofit>>() {
            @Override
            public void onResponse(Call<List<ResponseRetrofit>> call, retrofit2.Response<List<ResponseRetrofit>> response) {
                if (!response.isSuccessful()){
                    txtBancos.setText("Código: "+ response.code());
                    return;}
                String contenido="";
                for (ResponseRetrofit lista:response.body()) {
                    contenido =contenido+ lista.getName()+"\n";
                }
                txtBancos.setText("Con Retrofit\n"+ contenido);
            }

            @Override
            public void onFailure(Call<List<ResponseRetrofit>> call, Throwable t) {
                txtBancos.setText(t.getMessage());
            }
        });
    }

    @Override
    public void processFinish(String result) throws JSONException {

    }
    private void LgVolley(){
        String urllg="http://uealecpeterson.net/ws/login.php?usr="+bundle.getString("usr")+"&pass="+bundle.getString("pass");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urllg, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txtSaludo=(TextView)findViewById(R.id.lblMensaje);
                txtSaludo.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                txtSaludo=(TextView)findViewById(R.id.lblMensaje);
                txtSaludo.setText("Error: " + error.toString());
            }
        });
        queue.add(stringRequest);
    }
}