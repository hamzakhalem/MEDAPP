package com.example.medapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.medapp.entity.Medicament;

import java.util.List;

@Dao
public interface MedcamentDao {

    @Insert
    void addMed(Medicament ObMed);// adding a MEDICAMENT to database

    @Update
    void updateMed(Medicament ObMed);// update a MEDICAMENT

    @Query("select * from medicament")
    LiveData<List<Medicament>> getMedcamentsList();// get the list of database
    @Query("DELETE From medicament Where codeBarMed= :code")
    void deleteMed(Long code);
    @Query("SELECT * FROM medicament WHERE codeBarMed =:id")
    LiveData<Medicament> getPlaceById(Long id);
}
