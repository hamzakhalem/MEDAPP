package com.example.medapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.medapp.Adapter.AdapterMed;
import com.example.medapp.entity.Medicament;
import com.example.medapp.viewmodel.MedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MedList extends AppCompatActivity implements AdapterMed.OnItemClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),AddMedActivity.class);

                startActivity(intent);
            }
        });


        RecyclerView rc = findViewById(R.id.med_rc);
        rc.setLayoutManager(new LinearLayoutManager(this));
        final AdapterMed ca = new AdapterMed(this,this);
        rc.setAdapter(ca);

        MainActivity.myViewModel.getMedList().observe(this, new Observer<List<Medicament>>() {
            @Override
            public void onChanged(List<Medicament> med) {
                ca.setListMed(med);
            }
        });

    }

    @Override
    public void OnClickItem(int position) {
        Intent intent=new Intent(this,Consulter2Activity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }


}