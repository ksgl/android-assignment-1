package com.example.assignment;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;

import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import java.util.ArrayList;


class MyViewHolder extends RecyclerView.ViewHolder {

    TextView mTextView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.coloredNumberRow);
    }
}

class NumbersAdapter extends RecyclerView.Adapter<MyViewHolder> {
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.number_card, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ColoredNumber cn = cns_.get(i);
        myViewHolder.mTextView.setText(cn.num.toString());
        myViewHolder.mTextView.setTextColor(cn.color);
    }

    @Override
    public int getItemCount() {
        return cns_.size();
    }
}

