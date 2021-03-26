package com.example.medapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medapp.Adapter.AdapterMed;
import com.example.medapp.entity.Medicament;
import com.example.medapp.viewmodel.MedViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    int position;
    long code_bar;
    int supmod=-1;
    public static MedViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //modelview
        if(isConnexion()==false)
            Toast.makeText(getApplicationContext(),"vous êtes connecté au serveur local",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"vous êtes connecté au serveur web",Toast.LENGTH_LONG).show();



        myViewModel.connexion=isConnexion();

        myViewModel = ViewModelProviders.of(this).get(MedViewModel.class);

        myViewModel.getMedList().observe(this, new Observer<List<Medicament>>() {
            @Override
            public void onChanged(List<Medicament> med) {

            }
        });
    }

    public void ajouter(View view) {
        Intent intent =new Intent(this,AddMedActivity.class);
        startActivity(intent);
    }


    private void   scanCode() {
        IntentIntegrator intentIntegrator;
        intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setCaptureActivity(CaptureAct.class);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("scannig");
        intentIntegrator.initiateScan();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (intentResult != null)
        {
            if (intentResult.getContents() != null){
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage(intentResult.getContents());
                builder.setTitle("scan result");
                builder.setPositiveButton("approver",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        code_bar=Long.parseLong(intentResult.getContents());
                      if (supmod ==0) {continuerMod();
                      }
                      else myViewModel.delete(code_bar);
                    }
                });
                if (supmod ==1) builder.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog =builder.create();
                dialog.show();
            }
            else {
                Toast.makeText(this,"no result",Toast.LENGTH_LONG).show();

            }
        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    private int findCodeBar(long code_bar) {
        int size=myViewModel.getMedList().getValue().size();

        for (int i=0;i<size;i++)
        {
            if(myViewModel.getMedList().getValue().get(i).getCodeBarMed()==code_bar)
            {
                return i;
            }
        }
        return -1;
    }
    public void modfier(View view) {
        supmod=0;
        scanCode();



    }
    private void continuerMod(){
        if(code_bar==-1)
        {
            Toast.makeText(getApplicationContext(),"Scan again",Toast.LENGTH_LONG).show();
            return;
        }

        position=findCodeBar(code_bar);

       if(position==-1)
        {
            Toast.makeText(getApplicationContext(),"ce médicament n'existe pas",Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            Toast.makeText(getApplicationContext(),"ce médicament existe",Toast.LENGTH_LONG).show();
        }

        Intent intent=new Intent(this,ConsulterActvity.class);
        intent.putExtra("position",position);
        startActivity(intent);

    }

    public void info(View view) {
        Intent intent=new Intent(this,Info.class);
        startActivity(intent);
    }

    public void LisctConsult(View view) {
        Intent intent=new Intent(this,MedList.class);
        startActivity(intent);
    }

    public void delete(View view) {
       supmod=1;
         scanCode();
    }


    private boolean isConnexion()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {
            //we are connected to a network
            return true;
        }
        else
            return false;

    }

}
