package com.example.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

class ColoredNumber  {
    public Integer num;
    public Integer color;

    public ColoredNumber(Integer num_) {
        num = num_;
    }
};

public class ListActivity extends AppCompatActivity {
    private List<ColoredNumber> numbers = new ArrayList<>();

    public int getNumbersSize() {
        return numbers.size();
    }

    public ListActivity() {
        fillNumbers();
    }

    public void fillNumbers() {
        for (int i = 0; i < 100; i++) {
            ColoredNumber cur = new ColoredNumber(i + 1);
            if (i % 2 == 0) {
                cur.color = Color.RED;
            } else {
                cur.color = Color.BLUE;
            }
          numbers.add(cur);
        }
    }

    public void incrementNumbers() {
        ColoredNumber prevNum = numbers.get(numbers.size() - 1);
        ColoredNumber cur = new ColoredNumber(prevNum.num + 1);
        if (cur.num % 2 == 0) {
            cur.color = Color.RED;
        } else {
            cur.color = Color.BLUE;
        }
        numbers.add(cur);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        TextView v = findViewById(R.id.my_tv);
        for (int i = 0; i < numbers.size(); i++) {
            v.append(" " +numbers.get(i).num);
            v.setTextColor(numbers.get(i).color);
        }

        //RecyclerView recyclerView = findViewById(R.id.number_list);
    }
}
