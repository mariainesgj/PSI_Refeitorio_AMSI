package pt.ipleiria.estg.dei.refeitorio.ui.activities.home;

import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.WriterException;

import java.util.Locale;
import java.util.Objects;

import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.data.models.PratoDia;
import pt.ipleiria.estg.dei.refeitorio.data.network.ApiClient;
import pt.ipleiria.estg.dei.refeitorio.databinding.FragmentHomeBinding;
import pt.ipleiria.estg.dei.refeitorio.helpers.DateUtils;
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
        binding.setLifecycleOwner(getViewLifecycleOwner());
        viewModel = new ViewModelProvider(this).get(PratoDiaViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.getResult().observe(getViewLifecycleOwner(), result -> {
            try {
                binding.txtInfo.setVisibility(View.GONE);
                binding.ementaContent.getRoot().setVisibility(View.VISIBLE);
                if(result.lido == null){
                    binding.qrCode.setImageBitmap(QRCodeUtils.generateQRCode(String.format("{\"id\":\"%s\"}", result.id)));
                }else {
                    binding.qrCode.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.senha_ementa_used));
                    binding.txtInfo.setText(R.string.senha_used_info);
                    binding.txtInfo.setVisibility(View.VISIBLE);
                }

                    binding.ementaContent.txtDate.setText(DateUtils.formatToddMMyyyy(result.data));
                    binding.ementaContent.txtSopa.setText(result.nomeSopa);
                    binding.ementaContent.txtPrato.setText(result.nomePrato);


                    int color = result.tipoPrato.equals(PratoDia.PRATO_NORMAL) ? R.color.azul : com.sahana.horizontalcalendar.R.color.green;
                    binding.ementaContent.cardContent.setCardBackgroundColor(AppCompatResources.getColorStateList(requireContext(), color));


            } catch (WriterException e) {
                throw new RuntimeException(e);
            }
        });

        viewModel.loading.observe(getViewLifecycleOwner(), isLoading -> {
            if(isLoading){
                binding.ementaContent.getRoot().setVisibility(View.GONE);
                binding.txtInfo.setVisibility(View.GONE);
            }
        });

        viewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if(Objects.equals(error.second, ApiClient.RESULT_EMPTY)){
                binding.qrCode.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.no_menu_selected));
                binding.txtInfo.setText(R.string.no_senha_info);
            }else{
                binding.txtInfo.setText(error.first);
                binding.qrCode.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.error_cloud_icon));
            }
            binding.txtInfo.setVisibility(View.VISIBLE);
        });

        binding.horizontalCalendar.setOnDateSelectListener( newDate -> {
            viewModel.fetchPratoDia(String.format(
                    Locale.getDefault(),
                    "%d-%02d-%02d",
                    newDate.year,
                    newDate.monthNumber,
                    newDate.day
            ));
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}