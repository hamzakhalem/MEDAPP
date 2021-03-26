package com.example.medapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.medapp.R;
import com.example.medapp.entity.Medicament;

import java.util.List;
import java.util.Random;

import static com.example.medapp.R.id.rembo;

public class AdapterMed extends RecyclerView.Adapter<AdapterMed.viewholder> {

    List<Medicament> ListMed;
    Context context;
    OnItemClick onItemClick;
    public AdapterMed(Context context, OnItemClick onItemClick) {
        this.context = context;
        this.onItemClick=onItemClick;
    }

    public void setListMed(List<Medicament> listMed) {
        this.ListMed = listMed;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterMed.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(context).inflate(R.layout.meditem,parent,false);

        return new viewholder(itemView,onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMed.viewholder holder, int position) {
        holder.nameMED.setText("Nom:"+ListMed.get(position).getNameMed());
        holder.codeMed.setText("CodeBar:"+String.valueOf(ListMed.get(position).getCodeBarMed()));
        holder.Datedeb.setText("Fabrication:"+ListMed.get(position).getDateFabricationMed());
        holder.Datefin.setText("Péremption:"+ListMed.get(position).getDatePremptionMed());
        holder.QteMed.setText("Quantité:"+String.valueOf(ListMed.get(position).getQteMed()));
        holder.ClassMEd.setText("Classe:"+ListMed.get(position).getTherapiqueClassMed());

        //remboursable
        if(ListMed.get(position).getRemboursable())
            holder.rembo.setText("Remboursable");
        else
            holder.rembo.setText("Non Remboursable");

        int random=new Random().nextInt(10);
        String ResourceName="pic"+random;
        int resId=context.getResources().getIdentifier(ResourceName,"drawable",context.getPackageName());

        holder.image.setImageResource(resId);

    }

    @Override
    public int getItemCount() {
        if(ListMed!=null)
            return ListMed.size();
        return 0;
    }



    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameMED;
        TextView codeMed;
        TextView Datedeb;
        TextView Datefin;
        TextView QteMed;
        TextView ClassMEd;
        TextView rembo;
        ImageView image;
        OnItemClick onItemClick;

        public viewholder(@NonNull View itemView,OnItemClick onItemClick) {
            super(itemView);
            nameMED=itemView.findViewById(R.id.nameMED);
            codeMed=itemView.findViewById(R.id.codeMed);
            Datedeb=itemView.findViewById(R.id. Datedeb);
            Datefin=itemView.findViewById(R.id. Datefin);
            QteMed=itemView.findViewById(R.id.QteMed);
            ClassMEd=itemView.findViewById(R.id.ClassMEd);
            rembo=itemView.findViewById(R.id.rembo);
            image=itemView.findViewById(R.id.item_icon);
            this.onItemClick=onItemClick;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onItemClick.OnClickItem(getAdapterPosition());
        }
    }
    public  interface OnItemClick{
        void OnClickItem(int position);
    }
}
