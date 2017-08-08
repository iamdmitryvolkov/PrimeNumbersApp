package ru.indefinitedream.primenumbers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dmitry on 08.08.17.
 */

public class PrimeValuesViewHolder extends RecyclerView.ViewHolder {

    /*package*/ TextView mNumberTextView;

    public PrimeValuesViewHolder(View itemView) {
        super(itemView);
        mNumberTextView = (TextView) itemView.findViewById(android.R.id.text1);
    }
}
