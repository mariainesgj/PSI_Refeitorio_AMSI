package pt.ipleiria.estg.dei.refeitorio;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    static private Context appContext;

    static public Context getContext(){
        return appContext;
    }
}
