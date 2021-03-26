package com.example.medapp.repository;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.medapp.MainActivity;
import com.example.medapp.MedList;
import com.example.medapp.MyFileRequete;
import com.example.medapp.entity.Medicament;
import com.example.medapp.viewmodel.MedViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WebServiceRepository {

    MutableLiveData<List<Medicament>> myList;
    Application application;
    public WebServiceRepository(Application application)
    {
        myList=new MutableLiveData<>();
        this.application=application;
        getListFromServer();


    }

    private void getListFromServer() {

        String url ="http://192.168.1.5/GetListFromMedApp.php";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Medicament> MedList=new ArrayList<Medicament>();

                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);

                        String nameMed=jsonObject.getString("nameMed");
                        long codeBarMed=jsonObject.getLong("codeBarMed");
                        double priceMed=jsonObject.getDouble("priceMed");
                        String laboratoryMed=jsonObject.getString("laboratoryMed");

                        boolean Remboursable;
                        int RemboursableVal=jsonObject.getInt("Remboursable");
                        if (RemboursableVal==0)
                            Remboursable=false;
                        else
                            Remboursable=true;

                        String lotMed=jsonObject.getString("lotMed");
                        String DescrptionMed=jsonObject.getString("DescrptionMed");
                        String formePharmMed=jsonObject.getString("formePharmMed");
                        int conversationDurationMed=jsonObject.getInt("conversationDurationMed");
                        String dateFabricationMed=jsonObject.getString("dateFabricationMed");
                        String datePremptionMed=jsonObject.getString("datePremptionMed");
                        int QteMed=jsonObject.getInt("QteMed");
                        String TherapiqueClassMed=jsonObject.getString("TherapiqueClassMed");
                        String denominationMed=jsonObject.getString("denominationMed");


                        Medicament med=new Medicament(codeBarMed,nameMed,priceMed,laboratoryMed,QteMed,dateFabricationMed,datePremptionMed,Remboursable,lotMed,DescrptionMed,formePharmMed,conversationDurationMed,TherapiqueClassMed,denominationMed);
                        MedList.add(med);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                myList.setValue(MedList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                
            }
        }
        );
        MyFileRequete.getInstance(application).addToRequestQueue(jsonArrayRequest);

    }

    public MutableLiveData<List<Medicament>> getMedList() {

        return myList;
    }




    public void addMedicament (Medicament medicament){
        String url ="http://192.168.1.5/AjouterMedApp.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(response);
                    String success=jsonObject.getString("success");

                    if(success.equals("1"))
                        Toast.makeText(application, "Médicament ajouté", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(application, "érreur dans insertion de médicament ", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(application, "add error :"+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String>med=new HashMap<String, String>();


                med.put("codeBarMed",String.valueOf(medicament.getCodeBarMed()));
                med.put("nameMed",medicament.getNameMed());

                med.put("priceMed",String.valueOf(medicament.getPriceMed()));
                med.put("laboratoryMed",medicament.getLaboratoryMed());



                int val;
                if (medicament.getRemboursable()==true)
                    val=1;
                else
                    val=0;
                med.put("Remboursable",String.valueOf(val));



                med.put("lotMed",medicament.getLotMed());
                med.put("DescrptionMed",medicament.getDescrptionMed());
                med.put("formePharmMed",medicament.getFormePharmMed());
                med.put("conversationDurationMed",String.valueOf(medicament.getConversationDurationMed()));
                med.put("dateFabricationMed",medicament.getDateFabricationMed());
                med.put("datePremptionMed",medicament.getDatePremptionMed());
                med.put("QteMed",String.valueOf(medicament.getQteMed()));
                med.put("TherapiqueClassMed",medicament.getTherapiqueClassMed());
                med.put("denominationMed",medicament.getDenominationMed());


                return med;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(application);
        requestQueue.add(request);

    }

    public void deleteMedicament (long codeBarVal){
        String url ="http://192.168.1.5/DeleteMedApp.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(response);
                    String success=jsonObject.getString("success");

                    if(success.equals("1"))
                        Toast.makeText(application, "Médicament supprimé ", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(application, "érreur dans la suppression de médicament ", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(application, "delete error :"+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> codeBar = new HashMap<String, String>();

                codeBar.put("codeBarMed", String.valueOf(codeBarVal));

                return codeBar;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(application);
        requestQueue.add(stringRequest);

    }

    public void UpdateMedicament (Medicament medicament){
        String url ="http://192.168.1.5/MajMedApp.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(response);
                    String success=jsonObject.getString("success");

                    if(success.equals("1"))
                        Toast.makeText(application, "Médicament est modifié ", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(application, "érreur dans la modification de médicament ", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(application, "Update error :"+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map <String, String>med=new HashMap<String, String>();


                med.put("codeBarMed",String.valueOf(medicament.getCodeBarMed()));
                med.put("nameMed",medicament.getNameMed());

                med.put("priceMed",String.valueOf(medicament.getPriceMed()));
                med.put("laboratoryMed",medicament.getLaboratoryMed());



                int val;
                if (medicament.getRemboursable()==true)
                    val=1;
                else
                    val=0;
                med.put("Remboursable",String.valueOf(val));



                med.put("lotMed",medicament.getLotMed());
                med.put("DescrptionMed",medicament.getDescrptionMed());
                med.put("formePharmMed",medicament.getFormePharmMed());
                med.put("conversationDurationMed",String.valueOf(medicament.getConversationDurationMed()));
                med.put("dateFabricationMed",medicament.getDateFabricationMed());
                med.put("datePremptionMed",medicament.getDatePremptionMed());
                med.put("QteMed",String.valueOf(medicament.getQteMed()));
                med.put("TherapiqueClassMed",medicament.getTherapiqueClassMed());
                med.put("denominationMed",medicament.getDenominationMed());


                return med;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(application);
        requestQueue.add(request);

    }

}
