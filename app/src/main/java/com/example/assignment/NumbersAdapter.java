package com.example.assignment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import java.util.ArrayList;

/*****************************************************************************/
/******************* всё, что связано с RecyclerView *************************/
/*****************************************************************************/

class NumbersViewHolder extends RecyclerView.ViewHolder {

    TextView mTextView;

    public NumbersViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.coloredNumberRow);
    }
}

public class NumbersAdapter extends RecyclerView.Adapter<NumbersViewHolder> {

    static final public class ColoredNumber  {
        public Integer num;
        public Integer color;

        public ColoredNumber() {}
    }

    private ArrayList<ColoredNumber> cns_;


    public NumbersAdapter(ArrayList<ColoredNumber> cns) {
        cns_ = cns;
    }

    @NonNull
    @Override
    public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.number_card, viewGroup, false);
        return new NumbersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final NumbersViewHolder myViewHolder, int i) {
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "Recycle Click: " + myViewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                ColoredNumber value = cns_.get(myViewHolder.getAdapterPosition());
                SingleNumberFragment numberFragment = new SingleNumberFragment();
                Bundle bundle = new Bundle();
                bundle.putString("num", value.num.toString());
                bundle.putInt("color", value.color);
                numberFragment.setArguments(bundle);

                this.getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.activity_main_frame, numberFragment)
                            .addToBackStack(null)
                            .commit();
            }
        });

        ColoredNumber cn = cns_.get(i);
        myViewHolder.mTextView.setText(cn.num.toString());
        myViewHolder.mTextView.setTextColor(cn.color);
    }

    @Override
    public int getItemCount() {
        return cns_.size();
    }
}

