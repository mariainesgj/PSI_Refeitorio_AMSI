package pt.ipleiria.estg.dei.refeitorio.activities;

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
        User user = SharedPref.getItem(SharedPref.KEY_USER, User.class);

        if (user == null){
            Intent intent = new Intent(this, ChooseActivity.class);
            startActivity(intent);
        } else{
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        finish(); //garante que não dê para voltar a trás
    }
}