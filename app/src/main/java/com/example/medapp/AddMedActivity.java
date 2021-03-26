package com.example.medapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medapp.entity.Medicament;
import com.example.medapp.viewmodel.MedViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.util.Calendar;
import java.util.Random;

public class AddMedActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private long code_bar;
    private static int dateindex=0;



    private static String DateFab;
    private int dayf,monthf,yearf;

    private static String DatePer;
    private int dayp,monthp,yearp;




    private static Boolean Remboursable;

    private EditText classe_therapeutique;
    private EditText nom_commercial;
    private EditText laboratoire;
    private EditText denomination_du_medicament;
    private EditText forme_pharmaceutique;
    private EditText duree_de_conservation;
    private RadioButton remborsableoui;
    private RadioButton remborsablenon;
    private Button date_de_fabrication;
    private Button date_de_peremption;
    private EditText description_de_composant;
    private EditText prix;
    private EditText quantite_en_stock;
    private EditText lot;
    private Button qr_code;
    private Button ajouter;
    private TextView remborsableText;


    String classe_ther ;
    String nom_commerc;
    String labor ;
    String denom ;
    String forme ;
    String duree ;
    String desc ;
    String price ;
    String quantite ;
    String lott ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);
        DatePer="";
      DateFab="";
        code_bar=-1;///

        classe_therapeutique = findViewById(R.id.classe_therapeutique);
        nom_commercial = findViewById(R.id.nom_commercial);
        laboratoire = findViewById(R.id.laboratoire);
        denomination_du_medicament = findViewById(R.id.denomination_du_medicament);
        forme_pharmaceutique = findViewById(R.id.forme_pharmaceutique);
        duree_de_conservation = findViewById(R.id.duree_de_conservation);
        remborsableoui = findViewById(R.id.Addremborsableoui);
        remborsablenon = findViewById(R.id.Addremborsablenon);
        date_de_fabrication = findViewById(R.id.date_de_fabrication);
        date_de_peremption = findViewById(R.id.date_de_peremption);
        description_de_composant = findViewById(R.id.description_de_composant);
        prix = findViewById(R.id.prix);
        quantite_en_stock = findViewById(R.id.quantite_en_stock);
        lot = findViewById(R.id.lot);
        qr_code = findViewById(R.id.qr_code);
        ajouter= findViewById(R.id.ajouter);
        remborsableText = findViewById(R.id.remborsableText);

        //action de date de fabrication  picker button
        date_de_fabrication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateindex=1;
                DialogFragment datepicker;
                if(DateFab.equals(""))
                {
                    datepicker=new DatePickerFragment();
                }
                else
                {
                    datepicker=new DatePickerFragment(yearf,monthf,dayf);
                }
                datepicker.show(getSupportFragmentManager()," data picker");
                date_de_fabrication.setError(null);

            }
        });


        //action de date de_peremption picker button
        date_de_peremption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateindex=2;
                DialogFragment datepicker;
                if(DatePer.equals(""))
                {
                    datepicker=new DatePickerFragment();
                }
                else
                {
                    datepicker=new DatePickerFragment(yearp,monthp,dayp);
                }

                datepicker.show(getSupportFragmentManager()," data picker");
                date_de_peremption.setError(null);
            }
        });
        qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scanCode();
                qr_code.setError(null);
            }
        });

        remborsableoui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remborsableText.setError(null);
            }
        });

        remborsablenon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remborsableText.setError(null);
            }
        });

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                classe_ther = classe_therapeutique.getText().toString().trim();
                nom_commerc = nom_commercial.getText().toString().trim();
                labor = laboratoire.getText().toString().trim();
                denom = denomination_du_medicament.getText().toString().trim();
                forme = forme_pharmaceutique.getText().toString().trim();

                //check rembousable
                if(remborsableoui.isChecked())
                    Remboursable=true;
                if(remborsablenon.isChecked())
                    Remboursable=false;


                duree = duree_de_conservation.getText().toString().trim();
                desc = description_de_composant.getText().toString().trim();
                price = prix.getText().toString().trim();
                quantite = quantite_en_stock.getText().toString().trim();
                lott = lot.getText().toString().trim();

                if(TextUtils.isEmpty(classe_ther) || TextUtils.isEmpty(nom_commerc)
                || TextUtils.isEmpty(labor) || TextUtils.isEmpty(denom) || TextUtils.isEmpty(forme)
                || TextUtils.isEmpty(duree) || DateFab.equals("") || DatePer.equals("") || TextUtils.isEmpty(lott) ||
                        TextUtils.isEmpty(desc) || TextUtils.isEmpty(price) || TextUtils.isEmpty(quantite) ||
                        code_bar==-1 || (remborsablenon.isChecked()==false && remborsableoui.isChecked()==false))
                {
                    if (TextUtils.isEmpty(classe_ther)) {
                        classe_therapeutique.setError("Merci de remplir ce champ");
                        classe_therapeutique.setFocusable(true);

                    }

                    if (TextUtils.isEmpty(nom_commerc)) {
                        nom_commercial.setError("Merci de remplir ce champ");
                        nom_commercial.setFocusable(true);

                    }

                    if (TextUtils.isEmpty(labor)) {
                        laboratoire.setError("Merci de remplir ce champ");
                        laboratoire.setFocusable(true);

                    }

                    if (TextUtils.isEmpty(denom)) {
                        denomination_du_medicament.setError("Merci de remplir ce champ");
                        denomination_du_medicament.setFocusable(true);

                    }

                    if (TextUtils.isEmpty(forme)) {
                        forme_pharmaceutique.setError("Merci de remplir ce champ");
                        forme_pharmaceutique.setFocusable(true);

                    }

                    if (TextUtils.isEmpty(duree)) {
                        duree_de_conservation.setError("Merci de remplir ce champ");
                        duree_de_conservation.setFocusable(true);

                    }


                    if (DateFab.equals("")) {
                        date_de_fabrication.setError("Merci de remplir ce champ");
                        date_de_fabrication.setFocusable(true);

                    }

                    if (DatePer.equals("")) {
                        date_de_peremption.setError("Merci de remplir ce champ");
                        date_de_peremption.setFocusable(true);

                    }

                    if (TextUtils.isEmpty(lott)) {
                        lot.setError("Merci de remplir ce champ");
                        lot.setFocusable(true);

                    }

                    if (TextUtils.isEmpty(desc)) {
                        description_de_composant.setError("Merci de remplir ce champ");
                        description_de_composant.setFocusable(true);

                    }

                    if (TextUtils.isEmpty(price)) {
                        prix.setError("Merci de remplir ce champ");
                        prix.setFocusable(true);

                    }

                    if (TextUtils.isEmpty(quantite)) {
                        quantite_en_stock.setError("Merci de remplir ce champ");
                        quantite_en_stock.setFocusable(true);

                    }


                    if(code_bar==-1) {
                        qr_code.setError("scanner le codebar");
                        qr_code.setFocusable(true);

                    }

                    if(remborsablenon.isChecked()==false && remborsableoui.isChecked()==false)
                    {
                        remborsableText.setError("cocher oui ou non");
                        remborsableText.setFocusable(true);
                    }
                }
                else
                {
                    Medicament med = new Medicament(code_bar, nom_commerc, Double.parseDouble(price), labor, Integer.parseInt(quantite), DateFab, DatePer, Remboursable, lott, desc, forme, Integer.parseInt(duree), classe_ther, denom);
                    MainActivity.myViewModel.ajouter(med);

                    Intent intent=new Intent(getApplicationContext(),MedList.class);

                    startActivity(intent);
                }


            }
        });


    }

    private void scanCode() {
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
                builder.setPositiveButton("done",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        code_bar=Long.parseLong(intentResult.getContents());
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        if (dateindex == 1)
        {
            DateFab = year + "-" + month + "-" + dayOfMonth;
            yearf=year;
            monthf=month;
            dayf=dayOfMonth;
            date_de_fabrication.setText("Date de fabrication : "+DateFab);
        }


        if (dateindex == 2)
        {
            DatePer = year + "-" + month + "-" + dayOfMonth;
            yearp=year;
            monthp=month;
            dayp=dayOfMonth;
            date_de_peremption.setText("Date de peremption : "+DatePer);
        }

    }

}
