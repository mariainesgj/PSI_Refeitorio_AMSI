package pt.ipleiria.estg.dei.refeitorio.ui.activities.home;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.WriterException;

import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.databinding.FragmentHomeBinding;
import pt.ipleiria.estg.dei.refeitorio.helpers.QRCodeUtils;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.FaturaViewModel;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.PratoDiaViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class HomeFragment extends Fragment {


    FragmentHomeBinding binding;
    PratoDiaViewModel viewModel;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(PratoDiaViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.getResult().observe(getViewLifecycleOwner(), result -> {
            try {
                binding.qrCode.setImageBitmap(QRCodeUtils.generateQRCode(String.format("{\"id\":\"%s\"}", result.id)));
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchPratoDia("2024-12-11");

    }
}