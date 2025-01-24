package pt.ipleiria.estg.dei.refeitorio.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import java.util.Arrays;
import java.util.Objects;

import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.data.models.LinhasSimple;
import pt.ipleiria.estg.dei.refeitorio.databinding.ActivityCarrinhoBinding;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.CarrinhoViewModel;

public class CarrinhoActivity extends AppCompatActivity {


    ActivityCarrinhoBinding binding;
    CarrinhoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCarrinhoBinding.inflate(LayoutInflater.from(this));
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(CarrinhoViewModel.class);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.appBarHome.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setContentView(binding.getRoot());


        viewModel.getResult().observe(this, result -> {
            binding.txtId.setText(String.valueOf(result.id));
            binding.txtNumero.setText(String.valueOf(result.numero));

            double subTotal = result.getLinhas().length * 3.5;

            binding.txtSubTotal.setText(String.format("%s €", subTotal));
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
        viewModel.fetchCarrinho();
    }
}