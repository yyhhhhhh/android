package com.yyh.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.yyh.geoquiz.databinding.ActivityCheatBinding;

public class CheatActivity extends AppCompatActivity {

    private ActivityCheatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        binding.showAnswerBtn.setOnClickListener(view -> {
            int titleId = intent.getIntExtra("title", 1);
            boolean answer = intent.getBooleanExtra("answer", true);

            binding.cheatText.setText(titleId);
            binding.cheatAnswer.setText(String.valueOf(answer));

            Intent returnIntent = new Intent();
            returnIntent.putExtra("cheatAnswer",answer);
            setResult(RESULT_OK,returnIntent);
            finish();
        });
    }
}