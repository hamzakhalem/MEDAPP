package com.example.medapp.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicament")
public class Medicament {
    // creation of medicament model
    @PrimaryKey
    long codeBarMed;

    String nameMed;
    double priceMed;
    String laboratoryMed;
    int QteMed;
    String dateFabricationMed;
    String datePremptionMed;
    Boolean Remboursable;
    String lotMed;
    String DescrptionMed;
    String formePharmMed;
    int conversationDurationMed;
    String TherapiqueClassMed;
    String denominationMed;

    public Medicament(long codeBarMed, String nameMed, double priceMed, String laboratoryMed, int qteMed, String dateFabricationMed, String datePremptionMed, Boolean remboursable, String lotMed, String descrptionMed, String formePharmMed, int conversationDurationMed, String therapiqueClassMed, String denominationMed) {
        this.codeBarMed = codeBarMed;
        this.nameMed = nameMed;
        this.priceMed = priceMed;
        this.laboratoryMed = laboratoryMed;
        QteMed = qteMed;
        this.dateFabricationMed = dateFabricationMed;
        this.datePremptionMed = datePremptionMed;
        Remboursable = remboursable;
        this.lotMed = lotMed;
        DescrptionMed = descrptionMed;
        this.formePharmMed = formePharmMed;
        this.conversationDurationMed = conversationDurationMed;
        TherapiqueClassMed = therapiqueClassMed;
        this.denominationMed = denominationMed;
    }

    public Medicament() {
    }


    public long getCodeBarMed() {
        return codeBarMed;
    }

    public String getNameMed() {
        return nameMed;
    }

    public double getPriceMed() {
        return priceMed;
    }

    public String getLaboratoryMed() {
        return laboratoryMed;
    }

    public int getQteMed() {
        return QteMed;
    }

    public String getDateFabricationMed() {
        return dateFabricationMed;
    }

    public String getDatePremptionMed() {
        return datePremptionMed;
    }

    public Boolean getRemboursable() {
        return Remboursable;
    }

    public String getLotMed() {
        return lotMed;
    }

    public String getDescrptionMed() {
        return DescrptionMed;
    }

    public String getFormePharmMed() {
        return formePharmMed;
    }

    public int getConversationDurationMed() {
        return conversationDurationMed;
    }

    public String getTherapiqueClassMed() {
        return TherapiqueClassMed;
    }

    public String getDenominationMed() {
        return denominationMed;
    }

    public void setCodeBarMed(long codeBarMed) {
        this.codeBarMed = codeBarMed;
    }

    public void setNameMed(String nameMed) {
        this.nameMed = nameMed;
    }

    public void setPriceMed(double priceMed) {
        this.priceMed = priceMed;
    }

    public void setLaboratoryMed(String laboratoryMed) {
        this.laboratoryMed = laboratoryMed;
    }

    public void setQteMed(int qteMed) {
        QteMed = qteMed;
    }

    public void setDateFabricationMed(String dateFabricationMed) {
        this.dateFabricationMed = dateFabricationMed;
    }

    public void setDatePremptionMed(String datePremptionMed) {
        this.datePremptionMed = datePremptionMed;
    }

    public void setRemboursable(Boolean remboursable) {
        Remboursable = remboursable;
    }

    public void setLotMed(String lotMed) {
        this.lotMed = lotMed;
    }

    public void setDescrptionMed(String descrptionMed) {
        DescrptionMed = descrptionMed;
    }

    public void setFormePharmMed(String formePharmMed) {
        this.formePharmMed = formePharmMed;
    }

    public void setConversationDurationMed(int conversationDurationMed) {
        this.conversationDurationMed = conversationDurationMed;
    }

    public void setTherapiqueClassMed(String therapiqueClassMed) {
        TherapiqueClassMed = therapiqueClassMed;
    }

    public void setDenominationMed(String denominationMed) {
        this.denominationMed = denominationMed;
    }
}