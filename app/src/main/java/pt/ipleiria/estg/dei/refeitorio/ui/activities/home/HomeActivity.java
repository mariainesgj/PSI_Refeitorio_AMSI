package pt.ipleiria.estg.dei.refeitorio.ui.activities.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Initial Page
        replaceFragment(HomeFragment.newInstance());
        binding.navigation.setSelectedItemId(R.id.action_home);

        binding.navigation.setOnItemSelectedListener( item -> {
            if(item.getItemId() == R.id.action_menu) {
                replaceFragment(MenuFragment.newInstance());
            }else if(item.getItemId() == R.id.action_home){
                replaceFragment(HomeFragment.newInstance());
            } else if(item.getItemId() == R.id.action_profile){
                replaceFragment(ProfileFragment.newInstance());
            } else {
                replaceFragment(HomeFragment.newInstance());
            }

            return true;
        } );
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }



}