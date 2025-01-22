package pt.ipleiria.estg.dei.refeitorio.helpers;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import pt.ipleiria.estg.dei.refeitorio.MainApplication;

public class SharedPref{

    static final public String KEY_USER = "jfreUBGEVFEA";
    static final public String TOKEN = "TalakdjfA";
    static final private SharedPreferences prefs = MainApplication.getContext().getSharedPreferences("refeitorio_preferences", Context.MODE_PRIVATE);

    public static <T> T getItem(String key, Class<T> clazz){
        String json = prefs.getString(key, "");
        return new Gson().fromJson(json, clazz);
    }

    public static <T> void setItem( String key, T obj){
        String json = new Gson().toJson(obj);
        prefs.edit().putString(key, json).apply();
    }

    public static void logout(){
        prefs.edit().remove(KEY_USER).apply();
        prefs.edit().remove(TOKEN).apply();
    }
}