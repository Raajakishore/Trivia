package com.example.trivia.Controller;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {
    private static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;
    private RequestQueue queue;
    public  synchronized static AppController  getInstance(){
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;

    }
public RequestQueue getRequestqueue() {
if(queue==null)
{
 queue= Volley.newRequestQueue(getApplicationContext());
}
return  queue;
    }
public <T> void addToRequestQueue(Request<T>  req, String tag){
        req.setTag(TextUtils.isEmpty(tag)? tag : TAG);
        getRequestqueue().add(req);
}
    public <T> void addToRequestQueue(Request<T>  req){
        req.setTag(TextUtils.isEmpty(TAG));
        getRequestqueue().add(req);}
}

