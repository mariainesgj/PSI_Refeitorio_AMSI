package pt.ipleiria.estg.dei.refeitorio.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.data.models.User;
import pt.ipleiria.estg.dei.refeitorio.helpers.SharedPref;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(() -> {
            try {
                Thread.sleep(3000); //timer
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    User user = SharedPref.getItem(SharedPref.KEY_USER, User.class);

                    Intent intent;
                    if (user == null) {
                        intent = new Intent(SplashActivity.this, ChooseActivity.class);
                    } else {
                        intent = new Intent(SplashActivity.this, HomeActivity.class);
                    }
                    startActivity(intent);
                    finish(); //garante que não permite voltar para trás
                }
            });
        }).start();
    }


}