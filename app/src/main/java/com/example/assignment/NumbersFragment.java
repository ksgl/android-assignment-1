package com.example.assignment;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.support.annotation.NonNull;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;

/**************************************************************************************************/
/******************* всё, что связано с Fragment, содержащий список чисел *************************/

/**************************************************************************************************/

public class NumbersFragment extends Fragment {

    private SharedPreferences prefs;
    private String savedNumbers;
    private ArrayList<NumbersAdapter.ColoredNumber> cns;

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences.Editor prefEditor = PreferenceManager
                .getDefaultSharedPreferences(this.getActivity()
                        .getBaseContext()).edit();

        Gson gson = new Gson();
        String json = gson.toJson(cns);
        prefEditor.putString("numbers", json);
        prefEditor.apply();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_numbers,
                container, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity().getBaseContext());
        savedNumbers = prefs.getString("numbers", "");
        Log.d("saved_JSON", savedNumbers);

        if (!savedNumbers.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<NumbersAdapter.ColoredNumber>>() {}.getType();
            cns = gson.fromJson(savedNumbers, type);
        } else {
            cns = new ArrayList<>();
            fillNumbers(cns);
        }
        RecyclerView rvNumbers = view.findViewById(R.id.rvNumbers);

        int orientation = getResources().getConfiguration().orientation;
        int spanCount;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 4;
        } else {
            spanCount = 3;
        }

        final RecyclerView.LayoutManager layout = new GridLayoutManager(getContext(), spanCount);
        rvNumbers.setLayoutManager(layout);
        final NumbersAdapter numAdapter = new NumbersAdapter(cns, this);
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
