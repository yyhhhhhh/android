package com.yyh.crimelntent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.yyh.crimelntent.databinding.ActivityMainBinding;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CrimeFragment crimeFragment = CrimeFragment.newInstance(String.valueOf(new Date()));

        replaceFragment(R.id.fragment_tag,crimeFragment);

    }

    final FragmentManager manager = getSupportFragmentManager();

    private void replaceFragment(int containId, Fragment fragment){
        manager.beginTransaction()
                .replace(containId,fragment)
                .addToBackStack(null)
                .commit();
    }
}