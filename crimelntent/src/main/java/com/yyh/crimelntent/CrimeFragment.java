package com.yyh.crimelntent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yyh.crimelntent.databinding.FragmentCrimeBinding;
import com.yyh.crimelntent.entity.Crime;

import java.io.Serializable;

public class CrimeFragment extends Fragment {

    private FragmentCrimeBinding binding;

    public CrimeFragment() {
    }

    public static CrimeFragment newInstance() {
        CrimeFragment fragment = new CrimeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCrimeBinding.inflate(inflater,container,false);

        binding.btnDate.setOnClickListener(view ->{
            DatePickerFragment pickerFragment = new DatePickerFragment();
            pickerFragment.show(getFragmentManager(),"date");
        });
        getParentFragmentManager().setFragmentResultListener("bundle", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Crime crime = (Crime) result.getSerializable("result");
                binding.crimeTit.setText(crime.getTitle());
                binding.btnDate.setText(crime.getDate());
                binding.ckIs.setChecked(crime.getSolved());
            }
        });
        binding.btnConfirm.setOnClickListener(view -> {
            Crime crime = new Crime();
            crime.setTitle(binding.crimeTit.getText().toString());
            crime.setDate(binding.btnDate.getText().toString());
            crime.setSolved(binding.ckIs.isChecked());
            Bundle bundle = new Bundle();
            bundle.putSerializable("result",crime);
            getParentFragmentManager().setFragmentResult("bundle",bundle);
        });
        return binding.getRoot();
    }
}