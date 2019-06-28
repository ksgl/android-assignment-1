package com.example.assignment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**************************************************************************************************/
/******************* всё, что связано с Fragment, содержащий число ********************************/
/**************************************************************************************************/

public class SingleNumberFragment extends Fragment {

    private TextView tv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_single_number,
                container, false);
        TextView tv = view.findViewById(R.id.single_number);
        this.tv = tv;

        return view;
    }

    public void showNum(Bundle bundle) {

        if (bundle != null) {
            tv.setText(bundle.getString("num"));
            tv.setTextColor(bundle.getInt("color"));
        }
    }
}
