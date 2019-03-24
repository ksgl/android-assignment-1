package com.example.assignment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.support.annotation.NonNull;

import android.os.Bundle;

import java.util.ArrayList;

/**************************************************************************************************/
/******************* всё, что связано с Fragment, содержащий список чисел *************************/
/**************************************************************************************************/

public class NumbersFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_numbers,
                container, false);

        final ArrayList<NumbersAdapter.ColoredNumber> cns = new ArrayList<>();
        fillNumbers(cns);

        RecyclerView rvNumbers = view.findViewById(R.id.rvNumbers);

        final RecyclerView.LayoutManager layout = new GridLayoutManager(getContext(), 2);
        rvNumbers.setLayoutManager(layout);
        final NumbersAdapter numAdapter = new NumbersAdapter(cns, this.getContext());
        rvNumbers.setAdapter(numAdapter);

        Button incrementBtn = view.findViewById(R.id.btn);

        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementNumbers(cns);
                numAdapter.notifyItemChanged(cns.size() - 1);
            }
        });

        return view;
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
