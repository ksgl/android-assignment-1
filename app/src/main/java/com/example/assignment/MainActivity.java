package com.example.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public final void ShowNum(Bundle bundle) {
        SingleNumberFragment singleNumFrag = (SingleNumberFragment) getSupportFragmentManager().findFragmentById(R.id.single_number);
        singleNumFrag.showNum(bundle);
    }
}

