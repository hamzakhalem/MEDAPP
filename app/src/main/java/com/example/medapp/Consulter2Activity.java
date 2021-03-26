package com.example.medapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;

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

import com.example.medapp.entity.Medicament;

import java.util.Calendar;
import java.util.List;

public class Consulter2Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private EditText classe_therapeutique;
    private EditText nom_commercial;
    private EditText laboratoire;
    private EditText denomination_du_medicament;
    private EditText forme_pharmaceutique;
    private EditText duree_de_conservation;
    private RadioButton remborsableOui;
    private RadioButton remborsableNon;
    private Button date_de_fabrication;
    private Button date_de_peremption;
    private EditText description_de_composant;
    private EditText prix;
    private EditText quantite_en_stock;
    private EditText lot;

    private boolean change=false;

    private static int dateindex=0;

    private static String DateFab="";
    private int dayf,monthf,yearf;

    private static String DatePer="";
    private int dayp,monthp,yearp;


    private Button modifier;
    private Button supprimer;
    Medicament med;

    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View _mw = getLayoutInflater().inflate(R.layout.activity_consulter2, null);
        setContentView(_mw);

        Intent mIntent = getIntent();
        position = mIntent.getIntExtra("position", 0);

        med=MainActivity.myViewModel.getMedList().getValue().get(position);


        classe_therapeutique = findViewById(R.id.classe_therapeutique2);
        nom_commercial = findViewById(R.id.nom_commercial2);
        laboratoire = findViewById(R.id.laboratoire2);
        denomination_du_medicament = findViewById(R.id.denomination_du_medicament2);
        forme_pharmaceutique = findViewById(R.id.forme_pharmaceutique2);
        duree_de_conservation = findViewById(R.id.duree_de_conservation2);
        remborsableOui = findViewById(R.id.remborsableoui2);
        remborsableNon= findViewById(R.id.remborsablenon2);
        date_de_fabrication= findViewById(R.id.date_de_fabrication2);
        date_de_peremption = findViewById(R.id.date_de_peremption2);
        description_de_composant = findViewById(R.id.description_de_composant2);
        prix = findViewById(R.id.prix2);
        quantite_en_stock = findViewById(R.id.quantite_en_stock2);
        lot = findViewById(R.id.lot2);

        modifier=findViewById(R.id.modifcation2);
        supprimer=findViewById(R.id.supprimer2);


        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(change==false)
                {

                    change=true;
                    modifier.setText("Annuler");
                    supprimer.setText("Confirmer");

                }

                else
                {
                    change=false;
                    modifier.setText("Modifier");
                    supprimer.setText("Supprimer");
                    reset();
                }


                classe_therapeutique.setEnabled(change);
                nom_commercial.setEnabled(change);
                laboratoire.setEnabled(change);
                denomination_du_medicament.setEnabled(change);
                forme_pharmaceutique.setEnabled(change);
                duree_de_conservation.setEnabled(change);
                remborsableOui.setEnabled(change);
                remborsableNon.setEnabled(change);
                description_de_composant.setEnabled(change);
                prix.setEnabled(change);
                quantite_en_stock.setEnabled(change);
                lot.setEnabled(change);


            }
        });


        date_de_fabrication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(change==true)
                {
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
                }

            }
        });

        date_de_peremption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(change==true)
                {
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
                }

            }
        });

        reset();

    }

    private void reset() {

        DateFab=med.getDateFabricationMed();
        String []tmp=DateFab.split("-");
        yearf=Integer.parseInt(tmp[0]);
        monthf=Integer.parseInt(tmp[1]);
        dayf=Integer.parseInt(tmp[2]);

        DatePer=med.getDatePremptionMed();
        tmp=DatePer.split("-");
        yearp=Integer.parseInt(tmp[0]);
        monthp=Integer.parseInt(tmp[1]);
        dayp=Integer.parseInt(tmp[2]);


        classe_therapeutique.setText(med.getTherapiqueClassMed());
        nom_commercial.setText(med.getNameMed());
        laboratoire.setText(med.getLaboratoryMed());
        denomination_du_medicament.setText(med.getDenominationMed());
        forme_pharmaceutique.setText(med.getFormePharmMed());
        duree_de_conservation.setText(String.valueOf(med.getConversationDurationMed()));
        date_de_fabrication.setText(med.getDateFabricationMed());
        date_de_peremption.setText(med.getDatePremptionMed());
        description_de_composant.setText(med.getDescrptionMed());
        prix.setText(String.valueOf( med.getPriceMed()));
        quantite_en_stock.setText(String.valueOf(med.getQteMed()));
        lot.setText(String.valueOf(med.getLotMed()));

        //RadioButton
        if(med.getRemboursable()==true)
            remborsableOui.setChecked(true);
        else
            remborsableNon.setChecked(true);
    }

    public void supprimer(View view) {

        if(change==false)
        {
            Medicament med=MainActivity.myViewModel.getMedList().getValue().get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Delete entry")
                    .setMessage("Vous Voullez vraiment supprimez l'article?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.myViewModel.delete(med.getCodeBarMed());
                            finish();
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else
        {
            String classe_ther = classe_therapeutique.getText().toString().trim();
            String nom_commerc = nom_commercial.getText().toString().trim();
            String labor = laboratoire.getText().toString().trim();
            String denom = denomination_du_medicament.getText().toString().trim();
            String forme = forme_pharmaceutique.getText().toString().trim();



            String duree = duree_de_conservation.getText().toString().trim();
            String desc = description_de_composant.getText().toString().trim();
            String price = prix.getText().toString().trim();
            String quantite = quantite_en_stock.getText().toString().trim();
            String lott = lot.getText().toString().trim();
            if(TextUtils.isEmpty(classe_ther) || TextUtils.isEmpty(nom_commerc)
                    || TextUtils.isEmpty(labor) || TextUtils.isEmpty(denom) || TextUtils.isEmpty(forme)
                    || TextUtils.isEmpty(duree) || DateFab.equals("") || DatePer.equals("") || TextUtils.isEmpty(lott) ||
                    TextUtils.isEmpty(desc) || TextUtils.isEmpty(price) || TextUtils.isEmpty(quantite))
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
            }
            else
            {
                boolean rem;
                if(remborsableOui.isChecked()==true)
                    rem=true;
                else
                    rem=false;

                Medicament Newmed = new Medicament(med.getCodeBarMed(), nom_commerc, Double.parseDouble(price), labor, Integer.parseInt(quantite), DateFab, DatePer, rem, lott, desc, forme, Integer.parseInt(duree), classe_ther, denom);
                MainActivity.myViewModel.update(Newmed);

                Intent intent=new Intent(getApplicationContext(),MedList.class);
                startActivity(intent);
            }
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
            date_de_fabrication.setText(DateFab);
        }


        if (dateindex == 2)
        {
            DatePer = year + "-" + month + "-" + dayOfMonth;
            yearp=year;
            monthp=month;
            dayp=dayOfMonth;
            date_de_peremption.setText(DatePer);
        }

    }
}