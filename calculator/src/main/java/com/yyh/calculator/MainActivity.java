package com.yyh.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.googlecode.aviator.AviatorEvaluator;
import com.yyh.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;
    private StringBuilder str = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnZero.setOnClickListener(this);
        binding.btnOne.setOnClickListener(this);
        binding.btnTwo.setOnClickListener(this);
        binding.btnThree.setOnClickListener(this);
        binding.btnFour.setOnClickListener(this);
        binding.btnFive.setOnClickListener(this);
        binding.btnSix.setOnClickListener(this);
        binding.btnSeven.setOnClickListener(this);
        binding.btnEight.setOnClickListener(this);
        binding.btnNine.setOnClickListener(this);
        binding.btnAdd.setOnClickListener(this);
        binding.btnSub.setOnClickListener(this);
        binding.btnMultipy.setOnClickListener(this);
        binding.btnDivide.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);
        binding.btnMo.setOnClickListener(this);
        binding.btnPoint.setOnClickListener(this);
        binding.btnC.setOnClickListener(this);
        binding.btnEqual.setOnClickListener(this);
        binding.tvResult.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Button button = (Button)view;
        switch (view.getId()){
            case R.id.btn_one : case R.id.btn_two: case R.id.btn_three: case R.id.btn_four:
            case R.id.btn_five: case R.id.btn_six: case R.id.btn_seven: case R.id.btn_eight:
            case R.id.btn_nine: case R.id.btn_point: case R.id.btn_add: case R.id.btn_sub:
            case R.id.btn_multipy: case R.id.btn_divide: {
                str.append(button.getText().toString());
                break;
            }
            case R.id.btn_c: {
                str.delete(0,str.length());
                break;
            }
            case R.id.btn_back: {
                if(binding.tvResult.getText().toString().length() > 0){
                    binding.tvResult.setText(str.substring(0,str.length()-1));
                    str = new StringBuilder(str.substring(0,str.length()-1));
                    return;
                }
                break;
            }
            case R.id.btn_mo: {
                if(binding.tvResult.getText().toString().length() > 0){
                    long result = (long)AviatorEvaluator.execute(binding.tvResult.getText().toString());
                    binding.tvResult.setText(String.valueOf(result * 0.01));
                    return;
                }
                break;
            }
            case R.id.btn_equal: {
                if(str.length() > 0){
                   Object result = AviatorEvaluator.execute(str.toString());
                   binding.tvResult.setText(String.valueOf(result));
                   return;
                }
                break;
            }
        }
        binding.tvResult.setText(str);
    }
}