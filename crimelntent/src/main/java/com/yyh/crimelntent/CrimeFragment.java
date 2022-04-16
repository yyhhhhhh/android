package com.yyh.crimelntent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yyh.crimelntent.databinding.FragmentCrimeBinding;

public class CrimeFragment extends Fragment {

    private FragmentCrimeBinding binding;

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;


    public static CrimeFragment newInstance(String param1) {
        CrimeFragment fragment = new CrimeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCrimeBinding.inflate(inflater,container,false);
        if(null != mParam1){
            binding.btnDate.setText(mParam1);
        }
        binding.btnDate.setOnClickListener(view ->{
            DatePickerFragment pickerFragment = new DatePickerFragment();
            pickerFragment.show(getFragmentManager(),"date");
        });
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int year = result.getInt("year");
                int month = result.getInt("month");
                int day = result.getInt("day");
                binding.btnDate.setText(year+"年"+month+"月"+day+"日");
            }
        });
        return binding.getRoot();
    }
}