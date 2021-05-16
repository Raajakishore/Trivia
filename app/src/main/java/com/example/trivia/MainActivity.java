package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.trivia.data.Answerlistasyncresponse;
import com.example.trivia.data.QUESTION;
import com.example.trivia.model.QUESTIONBANK;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private TextView tt;
    private TextView tt1;
    private int Scoring=0;
    private int HighScoring ;
    private TextView tt2;
    private ImageButton ib1;
    private ImageButton ib2;
    private Button bb1;
    private Button bb2;
    String ID="1";
    int questionindex=1;
    private CardView card;
    private TextView HighScore;
    private TextView Score;
    int e = 0;
    int i;
    int a = 0;
    List<QUESTION> questionlist;
    SharedPreferences pre;
    SharedPreferences pr;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity p = MainActivity.this;
        QUESTIONBANK.pr(MainActivity.this);
        tt1 = findViewById(R.id.tt1);
        tt2 = findViewById(R.id.tt2);
        ib1 = findViewById(R.id.previousbutton);
        ib2 = findViewById(R.id.nextbutton);
        bb1 = findViewById(R.id.truebutton);
        bb2 = findViewById(R.id.falsebutton);
        HighScore=findViewById(R.id.HighScore);
        Score=findViewById(R.id.Score);
        card = findViewById(R.id.angle);
        ib1.setOnClickListener(this);
        ib2.setOnClickListener(this);
        bb1.setOnClickListener(this);
        bb2.setOnClickListener(this);

        pre =  getSharedPreferences(ID,MODE_PRIVATE);
        edit = pre.edit();
        pr= getSharedPreferences(ID,MODE_PRIVATE);
      HighScorin();
     getremember();
      Score.setText("Score:" + Scoring);
      tt1.setText(questionindex+"/900");
        questionlist = new QUESTIONBANK().getQuestions(new Answerlistasyncresponse() {
            @Override
            public void processfinished(List<QUESTION> questionList) {
                     tt2.setText(questionList.get(e).getq());
            }
        });
        Log.d("tag", "processfinished: " + questionlist);
    }
void HighScorin(){

        i=pr.getInt("HighScore",0);

        HighScoring=i;
        HighScore.setText("HighScore:" + HighScoring);
    }
    @Override
    public void onClick(View v) {
     switch (v.getId())
     {
         case R.id.previousbutton:

             if (questionindex >1) {

                 e = (e - 1) % 900;
                 questionindex = (questionindex - 1) % 900;
                 updatequestion();

             }
             break;
         case R.id.nextbutton:
             gonext();

             break;
         case R.id.truebutton:
             questionlist.get(e).setA();
             checkAnswer(true);
             Log.d("aaa", "onClick: " + questionlist.get(e).getA());
             updatequestion();
             break;
         case R.id.falsebutton:
             questionlist.get(e).setA();
             checkAnswer(false);
             updatequestion();

             break;
         default:
             break;
     }
    }
    void updatequestion(){
        String question = questionlist.get(e).getq();
        tt2.setText(question);
        tt1.setText(questionindex + "/900");


    }
    void Score(){
        Log.d("aa", "Score: " +  questionlist.get(e).getA());
        if(questionlist.get(e).getA()==1){
        Scoring++;}


        if(HighScoring<Scoring ){
            HighScoring++;
           HighScore.setText("HighScore:" + HighScoring);
        }
            Score.setText("Score:" + Scoring);
        Log.d("score", "Score: " + Scoring);
        edit.putInt("HighScore",HighScoring);
        edit.apply();






    }
    void checkAnswer(boolean answer) {
        if (questionlist.get(e).getAnswer() == answer) {
            fadeview();
            Score();


        } else {
            animation();

        }


    }
    void fadeview(){
        AlphaAnimation alpha = new AlphaAnimation(1.0f,0.0f);
        alpha.setDuration(350);
        alpha.setRepeatCount(1);
        alpha.setRepeatMode(alpha.REVERSE);
        card.setAnimation(alpha);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                card.setCardBackgroundColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                card.setCardBackgroundColor(Color.WHITE);
                gonext();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    void animation(){
    Animation shake = AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake_animation);

        card.setAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation){
                card.setCardBackgroundColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                card.setCardBackgroundColor(Color.WHITE);
                gonext();;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
}
void getremember(){
       e= pre.getInt("counter",1);
        questionindex = pre.getInt("questionindex",1);
        Scoring  = pre.getInt("score",0);
}
void setremember(){
        edit.putInt("counter",e).apply();
        edit.putInt("questionindex",questionindex).apply();
        edit.putInt("score",Scoring).apply();
}
void gonext(){
    e = (e +1 ) % 900 ;
    questionindex = (questionindex + 1) % 900;
    tt1.setText(questionindex+"/900");
    updatequestion();
}

    @Override
    protected void onPause() {
        Log.d("a12", "onPause: ");
       setremember();
        super.onPause();
    }
}
