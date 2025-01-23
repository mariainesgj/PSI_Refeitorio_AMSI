package pt.ipleiria.estg.dei.refeitorio.ui.activities.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.databinding.FragmentMenuBinding;
import pt.ipleiria.estg.dei.refeitorio.ui.adapters.MenuEmentaAdapter;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.MenuViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {


    FragmentMenuBinding binding;
    MenuViewModel viewModel;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment MenuFragment.
     */
    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentMenuBinding.inflate(inflater);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        binding.setViewModel(viewModel);

        ((AppCompatActivity)requireActivity()).setSupportActionBar(binding.appBarHome.toolbar);


        viewModel.getResult().observe(getViewLifecycleOwner(), result-> {
            binding.recyclerView.setAdapter(new MenuEmentaAdapter(result, (item, option) -> {
                switch (option){
                    case PRINCIPAL:
                        //TODO Mostrar dialog perguntando se quer ad
                        break;
                    case VEGETARIANO:

                        //TODO Mostrar dialog perguntando se quer add
                        break;
                    case NAO_MARCADO:
                        //TODO Mostrar dialog perguntando se quer remover
                        break;
                }
            }));
        });


        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.ementa_menu_options, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        }, getViewLifecycleOwner());


        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchEmentaMenu();
    }
}