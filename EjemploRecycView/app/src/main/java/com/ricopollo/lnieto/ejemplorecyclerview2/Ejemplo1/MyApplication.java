package com.ricopollo.lnieto.ejemplorecyclerview2.Ejemplo1;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();

    private RequestQueue poRequestQueue;
    private static MyApplication poInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        poInstance = this;
    }

    public static synchronized MyApplication getInstance(){
        return poInstance;
    }

    public RequestQueue getRequestQueue(){
        if(poRequestQueue == null)
            poRequestQueue = Volley.newRequestQueue(getApplicationContext());

        return poRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag){
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request){
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag){
        if(poRequestQueue != null)
            poRequestQueue.cancelAll(tag);
    }
}
