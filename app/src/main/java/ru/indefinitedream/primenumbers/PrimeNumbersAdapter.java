package ru.indefinitedream.primenumbers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dmitry on 08.08.17.
 */

public class PrimeNumbersAdapter extends RecyclerView.Adapter<PrimeValuesViewHolder> {

    private List<Integer> mNumbersList;

    public void updateValues(List<Integer> numbers) {
        mNumbersList = numbers;
        notifyDataSetChanged();
    }

    @Override
    public PrimeValuesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new PrimeValuesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrimeValuesViewHolder holder, int position) {
        holder.mNumberTextView.setText(Integer.toString(mNumbersList.get(position)));
    }

    @Override
    public int getItemCount() {
        return (mNumbersList != null) ? mNumbersList.size() : 0;
    }
}
