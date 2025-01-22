package pt.ipleiria.estg.dei.refeitorio.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.Objects;

import pt.ipleiria.estg.dei.refeitorio.data.repository.UserRepository;
import pt.ipleiria.estg.dei.refeitorio.databinding.ActivityRegisterBinding;
import pt.ipleiria.estg.dei.refeitorio.ui.activities.home.HomeActivity;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.GenericViewModelFactory;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarRegister.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.getDoSuccess().observe(this, value -> {
            if(value){
                Log.i( "Registo","Registo efetuado com sucesso");
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }

        });

        viewModel.getErrors().observe(this, value -> {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(value)
                    .setPositiveButton("OK", (dialog, which) -> {
                        // Action for OK button
                        dialog.dismiss();
                    })
                    .show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}