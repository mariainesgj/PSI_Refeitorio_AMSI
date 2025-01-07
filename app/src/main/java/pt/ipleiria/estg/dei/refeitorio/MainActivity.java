package pt.ipleiria.estg.dei.refeitorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import pt.ipleiria.estg.dei.refeitorio.data.models.User;
import pt.ipleiria.estg.dei.refeitorio.helpers.SharedPref;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_page);
    }

    @Override
    protected void onResume() {
        super.onResume();

        User t = SharedPref.getItem(SharedPref.KEY_USER, User.class);

    }
}