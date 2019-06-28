package com.example.assignment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;


/**************************************************************************************************/
/******************* всё, что связано с Fragment, содержащий число ********************************/
/**************************************************************************************************/

public class SingleNumberFragment extends Fragment {
    private SharedPreferences prefs;
    private String savedNumber;
    private TextView tv;

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences.Editor prefEditor = PreferenceManager
                .getDefaultSharedPreferences(this.getActivity()
                        .getBaseContext()).edit();

        NumbersAdapter.ColoredNumber curNum = new NumbersAdapter.ColoredNumber();
        curNum.color = tv.getCurrentTextColor();
        curNum.num = (Integer.parseInt(tv.getText().toString()));

        Gson gson = new Gson();
        String json = gson.toJson(curNum);
        prefEditor.putString("cur_number", json);
        prefEditor.apply();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_single_number,
                container, false);
        TextView tv = view.findViewById(R.id.single_number);
        this.tv = tv;

        prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity().getBaseContext());
        savedNumber = prefs.getString("cur_number", "");

        if (!savedNumber.isEmpty()) {
            Gson gson = new Gson();
            NumbersAdapter.ColoredNumber coloredNum = gson.fromJson(savedNumber, NumbersAdapter.ColoredNumber.class);
            tv.setText(coloredNum.num.toString());
            tv.setTextColor(coloredNum.color);
        }

        return view;
    }

    public void showNum(Bundle bundle) {

        if (bundle != null) {
            tv.setText(bundle.getString("num"));
            tv.setTextColor(bundle.getInt("color"));
        }
    }
}
