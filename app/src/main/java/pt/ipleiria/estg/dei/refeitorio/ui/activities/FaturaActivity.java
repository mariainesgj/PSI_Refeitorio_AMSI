package pt.ipleiria.estg.dei.refeitorio.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;

import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.data.repository.CompraRepository;
import pt.ipleiria.estg.dei.refeitorio.data.repository.UserRepository;
import pt.ipleiria.estg.dei.refeitorio.databinding.ActivityFaturaBinding;
import pt.ipleiria.estg.dei.refeitorio.databinding.ActivityLoginBinding;
import pt.ipleiria.estg.dei.refeitorio.ui.adapters.FaturaAdapter;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.FaturaViewModel;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.GenericViewModelFactory;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.LoginViewModel;

public class FaturaActivity extends AppCompatActivity {

    private FaturaViewModel viewModel;

    ActivityFaturaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFaturaBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarHome.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(FaturaViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.getResult().observe(this, result -> {
            binding.recyclerView.setAdapter(new FaturaAdapter(result));
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

        viewModel.fetchFaturas();
    }
}