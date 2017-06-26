package com.example.slins.sleepbus;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.util.List;
import java.util.Locale;

/**
 * Created by sidarta on 20/06/2017.
 */

public class BuscarLocalTask  extends AsyncTaskLoader<List<Address>> {
    Context mContext;
    String mLocal;
    List<Address> mEnderecoEncontrado;

    public BuscarLocalTask(Context activity, String local){
        super(activity);
        mContext = activity;
        mLocal = local;
    }

    @Override
    protected void onStartLoading(){
        if(mEnderecoEncontrado == null){
            forceLoad();
        }else{
            deliverResult(mEnderecoEncontrado);
        }
    }
    @Override
    public List<Address> loadInBackground() {
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            mEnderecoEncontrado = geocoder.getFromLocationName(mLocal, 10);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mEnderecoEncontrado;
    }
}
