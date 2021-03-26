package com.example.medapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medapp.DAO.MedcamentDao;
import com.example.medapp.entity.Medicament;


@Database(entities = Medicament.class,version = 4, exportSchema = false)
public abstract class MedRoomDB extends RoomDatabase {
public abstract MedcamentDao medDAO();
private static MedRoomDB instance;
public static MedRoomDB getDataBase(final Context context){
    if(instance==null)
    {
        synchronized (MedRoomDB.class) {

            if (instance == null)
                instance = Room.databaseBuilder(context.getApplicationContext(), MedRoomDB.class, "medicamentDB")
                        .fallbackToDestructiveMigration()
                        .build();
        }
    }
    return instance;
}
}
