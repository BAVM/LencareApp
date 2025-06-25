package com.vaxwe.lencareapp.ui.FavoritosC;

public class FavoritosModo {


    String PAIS,SLOC,PN,FAMILIA,CPU,GPU,HDD,SSD,RAM,TECLADO,PANTALLA,HUELLA,BIOS,OS;

    public FavoritosModo(){
    }

    public FavoritosModo(String PAIS, String SLOC, String PN, String FAMILIA, String CPU,
                         String GPU, String HDD, String SSD, String RAM, String TECLADO, String PANTALLA,
                         String HUELLA, String BIOS, String OS) {
        this.PAIS = PAIS;
        this.SLOC = SLOC;
        this.PN = PN;
        this.FAMILIA = FAMILIA;
        this.CPU = CPU;
        this.GPU = GPU;
        this.HDD = HDD;
        this.SSD = SSD;
        this.RAM = RAM;
        this.TECLADO = TECLADO;
        this.PANTALLA = PANTALLA;
        this.HUELLA = HUELLA;
        this.BIOS = BIOS;
        this.OS = OS;
    }

    public String getPAIS() {
        return PAIS;
    }

    public void setPAIS(String PAIS) {
        this.PAIS = PAIS;
    }

    public String getSLOC() {
        return SLOC;
    }

    public void setSLOC(String SLOC) {
        this.SLOC = SLOC;
    }

    public String getPN() {
        return PN;
    }

    public void setPN(String PN) {
        this.PN = PN;
    }

    public String getFAMILIA() {
        return FAMILIA;
    }

    public void setFAMILIA(String FAMILIA) {
        this.FAMILIA = FAMILIA;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String getHDD() {
        return HDD;
    }

    public void setHDD(String HDD) {
        this.HDD = HDD;
    }

    public String getSSD() {
        return SSD;
    }

    public void setSSD(String SSD) {
        this.SSD = SSD;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getTECLADO() {
        return TECLADO;
    }

    public void setTECLADO(String TECLADO) {
        this.TECLADO = TECLADO;
    }

    public String getPANTALLA() {
        return PANTALLA;
    }

    public void setPANTALLA(String PANTALLA) {
        this.PANTALLA = PANTALLA;
    }

    public String getHUELLA() {
        return HUELLA;
    }

    public void setHUELLA(String HUELLA) {
        this.HUELLA = HUELLA;
    }

    public String getBIOS() {
        return BIOS;
    }

    public void setBIOS(String BIOS) {
        this.BIOS = BIOS;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }



}
