package com.example.medapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.medapp.entity.Medicament;
import com.example.medapp.repository.MedRepository;
import com.example.medapp.repository.WebServiceRepository;

import java.io.Serializable;
import java.util.List;

public class MedViewModel extends AndroidViewModel {
    public static boolean connexion;
    private MedRepository medRepository;
    private WebServiceRepository webMedRepository;
    private LiveData<List<Medicament>> medList;

    public MedViewModel(@NonNull Application application) {
        super(application);
        if(connexion==true)
        {
            webMedRepository=new WebServiceRepository(application);
            medList=webMedRepository.getMedList();

        }

        else
        {
            medRepository= new MedRepository(application);
            medList=medRepository.getMedList();
        }

    }

    public MedViewModel(@NonNull Application application,boolean connexion) {
        super(application);
        if(connexion==true)
        {
            webMedRepository=new WebServiceRepository(application);
            medList=webMedRepository.getMedList();

        }

        else
        {
            medRepository= new MedRepository(application);
            medList=medRepository.getMedList();
        }

    }

    public void ajouter(Medicament med) {
        if(connexion==true)
            webMedRepository.addMedicament(med);
        else
            medRepository.addMedicament(med);
    }
    public LiveData<List<Medicament>> getMedList() {
        return medList;
    }


    public void delete(Long codeBar) {
        if(connexion==true)
            webMedRepository.deleteMedicament(codeBar);
        else
            medRepository.deleteMedicament(codeBar);
    }
    public void update(Medicament medicament){
        if(connexion==true)
            webMedRepository.UpdateMedicament(medicament);
        else
            medRepository.UpdateMedicament(medicament);
    }


}
