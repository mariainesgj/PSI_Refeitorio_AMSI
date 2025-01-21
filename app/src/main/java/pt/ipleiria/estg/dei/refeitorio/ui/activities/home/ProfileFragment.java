package pt.ipleiria.estg.dei.refeitorio.ui.activities.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.data.models.User;
import pt.ipleiria.estg.dei.refeitorio.helpers.SharedPref;
import pt.ipleiria.estg.dei.refeitorio.ui.viewmodel.ProfileViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private ProfileViewModel viewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = SharedPref.getItem(SharedPref.KEY_USER, User.class);

        if (user != null) {
            viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

            viewModel.setUsername(user.getUsername());
            viewModel.setEmail(user.getEmail());
            viewModel.setName(user.profile.getName());
            viewModel.setPhone(user.profile.getMobile());
            viewModel.setAddress(user.profile.getStreet());
            viewModel.setLocale(user.profile.getLocale());
            viewModel.setPostalCode(user.profile.getPostalCode());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        EditText inputUsername = rootView.findViewById(R.id.inputUsername);
        EditText inputEmail = rootView.findViewById(R.id.inputEmail);
        EditText inputName = rootView.findViewById(R.id.inputName);
        EditText inputPhone = rootView.findViewById(R.id.inputPhone);
        EditText inputStreet = rootView.findViewById(R.id.inputStreet);
        EditText inputLocal = rootView.findViewById(R.id.inputLocal);
        EditText inputPostalCode = rootView.findViewById(R.id.inputPostalCode);

        if (viewModel != null) {
            viewModel.getUsername().observe(getViewLifecycleOwner(), inputUsername::setText);
            viewModel.getEmail().observe(getViewLifecycleOwner(), inputEmail::setText);
            viewModel.getName().observe(getViewLifecycleOwner(), inputName::setText);
            viewModel.getPhone().observe(getViewLifecycleOwner(), inputPhone::setText);
            viewModel.getAddress().observe(getViewLifecycleOwner(), inputStreet::setText);
            viewModel.getLocale().observe(getViewLifecycleOwner(), inputLocal::setText);
            viewModel.getPostalCode().observe(getViewLifecycleOwner(), inputPostalCode::setText);
        }

        return rootView;
    }

    public void doLogout(){

    }
}
