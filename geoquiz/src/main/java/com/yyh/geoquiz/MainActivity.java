package com.yyh.geoquiz;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.yyh.geoquiz.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private List<Question> questions = Arrays.asList(
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,true),
            new Question(R.string.question_americas,false),
            new Question(R.string.question_asia,true)
    );

    private int currentIndex = 0;

    private int status = 0;

    private boolean cheatAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            status = 0;
            currentIndex = (currentIndex + 1) % questions.size();
            updateQuestion();
        });

        updateQuestion();

        binding.cheatBtn.setOnClickListener( view -> {
            status = 1;
            Intent intent = new Intent(MainActivity.this,CheatActivity.class);
            intent.putExtra("title",questions.get(currentIndex).getTextId());
            intent.putExtra("answer",questions.get(currentIndex).getAnswer());
            launcher.launch(intent);
        });
    }

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Intent returnData = result.getData();
                        if(null != returnData) {
                            cheatAnswer = returnData.getBooleanExtra("cheatAnswer",true);
                            System.out.println(cheatAnswer);
                        }
                    }
                }
            }
    );

    public void updateQuestion(){
        binding.title.setText(questions.get(currentIndex).getTextId());
    }

    public void checkAnswer(boolean answer){
        int msgId = 0;
        if(status == 1){
            msgId = answer == cheatAnswer ? R.string.cheat_correct : R.string.cheat_wrong;
        }else{
            msgId = questions.get(currentIndex).getAnswer() == answer ? R.string.correct_toast : R.string.incorrect_toast;
        }
        Toast.makeText(this,msgId,Toast.LENGTH_SHORT).show();
    }
}