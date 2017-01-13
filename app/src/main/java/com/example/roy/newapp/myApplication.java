package com.example.roy.newapp;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy on 13/01/2017.
 */

public class myApplication extends Application {
    protected List<String> globalList;

    public myApplication(){
        globalList=new ArrayList<String>();
    }
}
