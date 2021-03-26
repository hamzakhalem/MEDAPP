package com.example.medapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyFileRequete {

    private static  MyFileRequete ourInstance;
    private RequestQueue myRQ;
    Context context;

    public static MyFileRequete getInstance(Context context)
    {
        if(ourInstance==null)
            ourInstance=new MyFileRequete(context);
        return ourInstance;
    }

    private MyFileRequete(Context context)
    {
        this.context=context;
        myRQ=getRequestQueue();
    }

    private RequestQueue getRequestQueue()
    {
        if(myRQ==null)
            myRQ= Volley.newRequestQueue(context);
        return myRQ;
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }

}
