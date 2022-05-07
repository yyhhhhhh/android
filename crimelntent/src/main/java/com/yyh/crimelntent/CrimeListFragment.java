package com.yyh.crimelntent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yyh.crimelntent.databinding.FragmentCrimeListBinding;
import com.yyh.crimelntent.entity.Crime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrimeListFragment extends Fragment {

    private FragmentCrimeListBinding binding;
    private CrimeAdapter adapter;
    private List<Crime> crimes;
    private int currentPos;

    public CrimeListFragment() {
    }

    public static CrimeListFragment newInstance() {
        CrimeListFragment fragment = new CrimeListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCrimeListBinding.inflate(inflater,container,false);

        initData();
        // 初始化视图
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.recyclerview.addItemDecoration(new DividerItemDecoration(binding.recyclerview.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new CrimeAdapter(crimes);
        adapter.setOnClickListener(view -> {
            CrimeAdapter.ViewHolder viewHolder = (CrimeAdapter.ViewHolder) view.getTag();
            currentPos = viewHolder.getAdapterPosition();
            adapter.setCurrentIndex(currentPos);
            CrimeFragment crimeFragment = CrimeFragment.newInstance();
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.list_fragment,crimeFragment)
                    .addToBackStack(null)
                    .commit();
            Bundle bundle = new Bundle();
            bundle.putSerializable("result",crimes.get(currentPos));
            getParentFragmentManager().setFragmentResult("bundle",bundle);
        });
        getParentFragmentManager().setFragmentResultListener("bundle", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Crime crime = (Crime) result.getSerializable("result");
                crimes.set(currentPos,crime);
            }
        });
        binding.recyclerview.setAdapter(adapter);
        return binding.getRoot();
    }


    private void initData(){
        crimes = new ArrayList<>();
        for(int i = 0 ; i < 5 ; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setDate(String.valueOf(new Date()));
            crime.setSolved(false);
            crimes.add(crime);
        }
    }
}