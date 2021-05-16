package com.example.trivia.model;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.Controller.AppController;
import com.example.trivia.MainActivity;
import com.example.trivia.R;
import com.example.trivia.data.Answerlistasyncresponse;
import com.example.trivia.data.QUESTION;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.os.Build.ID;

public class QUESTIONBANK {
    public Activity p;
   public QUESTIONBANK(Activity p){
       this.p=p;
    }
    ArrayList<QUESTION> questionlist = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements.json";
    static Activity pp;
public static  void pr(Activity p){

    pp = p;
}
    public QUESTIONBANK() {

    }

    public List<QUESTION> getQuestions(final Answerlistasyncresponse callback) {
    JsonArrayRequest jsonarray = new JsonArrayRequest(
            Request.Method.GET,
            url , null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {


                    for (int i = 0; i < response.length(); i++) {
                        QUESTION question;
                        question = new QUESTION();
                        try {
                            String a = (response.getJSONArray(i).getString(0).toString());

                            question.setq(a);

                            question.setanswer(response.getJSONArray(i).getBoolean(1));
                            Log.d("objectString", "onResponse: " + question.toString());
                            question.getStrin(question.toString());
                            question.setPr(pp);

                            questionlist.add(question);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    callback.processfinished(questionlist);

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("NOT DOWNLOADED", "onResponse: " + "ERROR");
        }
    }
    );
    AppController.getInstance().addToRequestQueue(jsonarray);
return questionlist;
}}


