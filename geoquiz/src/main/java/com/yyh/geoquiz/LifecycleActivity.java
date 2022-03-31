package com.yyh.geoquiz;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yyh.geoquiz.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LifecycleActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private List<Question> questions = Arrays.asList(
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,true),
            new Question(R.string.question_africa,true),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true)
    );


    private int currentIndex = 0;

    public static final String TGA = "LifecycleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TGA,"onCreate()被调用");
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.trueBtn.setOnClickListener( view ->{
            checkAnswer(true);
        });

        binding.falseBtn.setOnClickListener( view ->{
            checkAnswer(false);
        });

        binding.nextBtn.setOnClickListener( view -> {
            currentIndex = (currentIndex + 1) % questions.size();
            updateQuestion();
        });

        updateQuestion();
    }



    public void updateQuestion(){
        binding.title.setText(questions.get(currentIndex).getTextId());
    }

    public void checkAnswer(boolean answer){
        int msgId = questions.get(currentIndex).getAnswer() == answer ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(this,msgId,Toast.LENGTH_SHORT).show();
    }
}