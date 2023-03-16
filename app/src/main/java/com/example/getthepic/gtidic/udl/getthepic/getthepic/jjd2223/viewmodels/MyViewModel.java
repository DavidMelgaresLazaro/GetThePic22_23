package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class MyViewModel extends ViewModel {
    private MutableLiveData<String> myLiveData = new MutableLiveData<>();

    public MyViewModel() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                myLiveData.postValue("Valor actualizado");
            }
        }, 0, 3000);
    }

    public LiveData<String> getMyLiveData() {
        return myLiveData;
    }
}
