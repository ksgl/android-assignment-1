package com.example.assignment;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<NumbersAdapter.ColoredNumber> cns = new ArrayList<>();
        fillNumbers(cns);

        RecyclerView rvNumbers = findViewById(R.id.rvNumbers);

        final RecyclerView.LayoutManager layout = new GridLayoutManager(this,2);
        rvNumbers.setLayoutManager(layout);
        final NumbersAdapter numAdapter = new NumbersAdapter(cns);
        rvNumbers.setAdapter(numAdapter);

        Button incrementBtn = findViewById(R.id.btn);

        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementNumbers(cns);
                numAdapter.notifyItemChanged(cns.size() - 1);
            }
        });
    }

    private void fillNumbers(ArrayList<NumbersAdapter.ColoredNumber> cns_) {
        for (int i = 0; i < 100; i++) {
            NumbersAdapter.ColoredNumber cur = new NumbersAdapter.ColoredNumber();
            cur.num = i + 1;
            if ((cur.num + 1) % 2 == 0) {
                cur.color = Color.BLUE;
            } else {
                cur.color = Color.RED;
            }
            cns_.add(cur);
        }
    }

    public void incrementNumbers(ArrayList<NumbersAdapter.ColoredNumber> cns_) {
        if (cns_.size() == 0) {
            fillNumbers(cns_);
        }
        NumbersAdapter.ColoredNumber prevNum = cns_.get(cns_.size() - 1);
        NumbersAdapter.ColoredNumber cur = new NumbersAdapter.ColoredNumber();
        cur.num = prevNum.num + 1;
        if ((cur.num + 1) % 2 == 0) {
            cur.color = Color.BLUE;
        } else {
            cur.color = Color.RED;
        }
        cns_.add(cur);
    }
}

