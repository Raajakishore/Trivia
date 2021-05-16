package com.example.trivia.data;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class QUESTION {
    private  String q;
    private boolean answer;
String ID="1";
     int a = 0;
     SharedPreferences pr;
String objectValue;

    public void setPr(Activity p) {
        pr = p.getPreferences(p.MODE_PRIVATE);
    }

    SharedPreferences.Editor edit;
    public void setA() {

      a=   pr.getInt(objectValue,0);

        this.a++;
        pr.edit().putInt(objectValue,this.a).apply();

  }
public void  getStrin(String aa){
objectValue = aa;
}


    public int getA() {
        return this.a;
    }

    public QUESTION() {
        q=null;
        answer= false ;
    }

    public QUESTION(String q , Boolean answer){
     this.q = q;
     this.answer = answer;
    }

    public boolean isanswer() {
        return answer;
    }

    public boolean getAnswer() {
        return answer;
    }

    public String getq() {
        return q;
    }

    public void setq(String q) {
        this.q = q;
    }


    public void setanswer(boolean answer) {
        this.answer = answer;
     }

    @Override
    public String toString() {
        return "QUESTION{" +
                "q='" + q + '\'' +
                ", answer=" + answer +
                '}';
    }
}
