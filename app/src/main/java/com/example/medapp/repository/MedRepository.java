package com.example.medapp.repository;

import android.app.Application;
import android.os.AsyncTask;


import androidx.lifecycle.LiveData;


import com.example.medapp.DAO.MedcamentDao;
import com.example.medapp.db.MedRoomDB;
import com.example.medapp.entity.Medicament;

import java.util.List;

import android.app.Application;
import android.os.AsyncTask;


import androidx.lifecycle.LiveData;


import com.example.medapp.DAO.MedcamentDao;
import com.example.medapp.db.MedRoomDB;
import com.example.medapp.entity.Medicament;

import java.util.List;

public  class MedRepository {
    private MedcamentDao MedDao;
    private LiveData<List<Medicament>> MedList ;

    public MedRepository(Application application) {
        MedRoomDB medRoomDB= MedRoomDB.getDataBase(application); // DATABASE CREATION
        MedDao= medRoomDB.medDAO(); // GET THE DAO
        MedList = MedDao.getMedcamentsList(); // GET THE LIST OF MEDCAMENTS
    }

    public LiveData<List<Medicament>> getMedList() {
        return MedList;
    }


    public void addMedicament (Medicament medicament){
        new addMedicamentTask(MedDao).execute(medicament);
    }
    public void UpdateMedicament(Medicament medicament){
        new updateMedcamentTask(MedDao).execute( medicament);
    }

    public void deleteMedicament(long CodeBare)
    {
        new deleteMed(MedDao).execute(CodeBare);
    }

    private class addMedicamentTask extends AsyncTask<Medicament, Void, Void> {
        private MedcamentDao MedDao;
        public addMedicamentTask(MedcamentDao medDao) {
            this.MedDao=medDao;
        }

        @Override
        protected Void doInBackground(Medicament... medicaments) {
            MedDao.addMed(medicaments[0]);
            return null;
        }
    }

    private class updateMedcamentTask extends AsyncTask<Medicament,Void,Void> {
        private final MedcamentDao MedDao;

        public updateMedcamentTask(MedcamentDao medDao) {
            this.MedDao=medDao;
        }
        protected Void doInBackground(Medicament... medicaments){
            MedDao.updateMed(medicaments[0]);
            return null;
        }
    }

    private class deleteMed extends AsyncTask<Long,Void,Void> {

        private final MedcamentDao medDao;

        public deleteMed(MedcamentDao medDao) {
            this.medDao=medDao;
        }


        @Override
        protected Void doInBackground(Long... integers) {
            MedDao.deleteMed(integers[0]);
            return null;
        }
    }
}