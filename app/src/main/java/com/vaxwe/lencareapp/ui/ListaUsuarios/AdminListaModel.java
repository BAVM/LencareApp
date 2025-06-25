package com.vaxwe.lencareapp.ui.ListaUsuarios;

public class AdminListaModel {


    String NOMBRE,APELLIDO,UID,EMAIL,PASSWORD,ROLL,IMAGEN;

    AdminListaModel(){

    }

    public AdminListaModel(String NOMBRE, String APELLIDO, String UID, String EMAIL, String PASSWORD, String ROLL, String IMAGEN) {
        this.NOMBRE = NOMBRE;
        this.APELLIDO = APELLIDO;
        this.UID = UID;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.ROLL = ROLL;
        this.IMAGEN = IMAGEN;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDO() {
        return APELLIDO;
    }

    public void setAPELLIDO(String APELLIDO) {
        this.APELLIDO = APELLIDO;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getROLL() {
        return ROLL;
    }

    public void setROLL(String ROLL) {
        this.ROLL = ROLL;
    }

    public String getIMAGEN() {
        return IMAGEN;
    }

    public void setIMAGEN(String IMAGEN) {
        this.IMAGEN = IMAGEN;
    }


}
