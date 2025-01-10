package pt.ipleiria.estg.dei.refeitorio.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import pt.ipleiria.estg.dei.refeitorio.databinding.ActivityChooseBinding;

public class ChooseActivity extends AppCompatActivity {

    private ActivityChooseBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.btnEntrar.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        });

        binding.btnCriar.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }
}
