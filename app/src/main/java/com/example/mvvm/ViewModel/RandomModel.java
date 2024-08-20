package com.example.mvvm.ViewModel;

import androidx.lifecycle.ViewModel;

import java.util.Random;

public class RandomModel extends ViewModel {
    Random random = new Random();
    public int i ;
    public RandomModel(){
        GetData();
    }
    public void GetData(){
        i= random.nextInt(100) -1;
    }
}
