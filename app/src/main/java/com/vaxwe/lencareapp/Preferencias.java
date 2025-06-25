package com.vaxwe.lencareapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferencias {


    private static final String DATA_LOGIN = "status_login",
            DATA_ROLL = "ROLL";

    private static SharedPreferences getSharedReferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);

    }
    public static void setDataRoll(Context context, String data){
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.putString(DATA_ROLL,data);
        editor.apply();
    }
    public static String getDataRoll(Context context){
        return getSharedReferences(context).getString(DATA_ROLL,"");
    }

    public static void setDataLogin(Context context,boolean status){
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.putBoolean(DATA_LOGIN,status);
        editor.apply();
    }

    public static boolean getDataLogin (Context context){
        return getSharedReferences(context).getBoolean(DATA_LOGIN,false);
    }

    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.remove(DATA_ROLL);
        editor.remove(DATA_LOGIN);
        editor.apply();
    }



}
