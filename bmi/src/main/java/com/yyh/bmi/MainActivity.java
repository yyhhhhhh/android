package com.yyh.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.yyh.bmi.databinding.ActivityMainBinding;
import com.yyh.bmi.utils.BMIUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.complete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String weight = binding.weight.getText().toString().trim();
        String height = binding.height.getText().toString().trim();
        binding.bmi.setText(BMIUtils.getBMI(weight,height));
        int id = binding.sex.getCheckedRadioButtonId();
        if(id == R.id.male)
            binding.result.setText(BMIUtils.getBMIAndMale(BMIUtils.getBMI(weight,height)));
        else
            binding.result.setText(BMIUtils.getBMIAndFemale(BMIUtils.getBMI(weight,height)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.clear){
            binding.weight.setText("");
            binding.height.setText("");
            binding.bmi.setText("");
            binding.result.setText("");
        }
        return super.onOptionsItemSelected(item);
    }
}