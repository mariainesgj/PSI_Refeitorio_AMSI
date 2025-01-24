package pt.ipleiria.estg.dei.refeitorio.ui.activities.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
                        showConfirmationDialog(
                                "Adicionar Menu Principal",
                                "Pretende adicionar esta senha de Menu Principal ao seu carrinho?",
                                () -> {
                                    viewModel.addCarrinhoLinha(item.id, item.pratoNormalId);
                                }
                        );
                        break;
                    case VEGETARIANO:
                        showConfirmationDialog(
                                "Adiconar Menu Vegetariano",
                                "Pretende adicionar esta senha de Menu Vegetariano ao seu carrinho?",
                                () -> {
                                    viewModel.addCarrinhoLinha(item.id, item.pratoVegetarianoId);
                                }
                        );
                        break;
                    case NAO_MARCADO:
                        if(item.linhaCarrinhoNormalId == null && item.linhaCarrinhoVegetarianoId == null){
                            return;
                        }
                        showConfirmationDialog(
                                "Remover Menu",
                                "Pretende remover esta senha do seu carrinho?",
                                () -> {
                                    viewModel.removeCarrinhoLinha(
                                        item.linhaCarrinhoNormalId != null ? item.linhaCarrinhoNormalId : item.linhaCarrinhoVegetarianoId
                                    );
                                }
                        );
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

    private void showConfirmationDialog(String title, String message, Runnable onConfirm) {
        new AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Sim", (dialog, which) -> {
                    if (onConfirm != null) {
                        onConfirm.run();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
}