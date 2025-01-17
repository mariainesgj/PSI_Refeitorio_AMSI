package pt.ipleiria.estg.dei.refeitorio.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.data.repository.UserRepository;
import pt.ipleiria.estg.dei.refeitorio.databinding.ActivityLoginBinding;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.GenericViewModelFactory;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.LoginViewModel;

public class LoginActivity  extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.content);
        setSupportActionBar(binding.appBarHome.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        UserRepository repository =  new UserRepository(this);

        GenericViewModelFactory<LoginViewModel, UserRepository> factory =
                new GenericViewModelFactory<>(LoginViewModel.class, repository);

        viewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);

        binding.setViewModel(viewModel);


        viewModel.getDoSuccess().observe(this, value -> {
            if(value){
                //TODO redirect to HomePage
                Log.i( "Login","Login foi sucesso");
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
