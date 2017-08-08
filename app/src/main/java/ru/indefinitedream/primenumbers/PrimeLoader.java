package ru.indefinitedream.primenumbers;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 08.08.17.
 */

public class PrimeLoader extends AsyncTaskLoader<PrimeResult> {

    private static final int FIRST_PRIME = 2;

    private int mNumbersLimit;

    public PrimeLoader(Context context, Bundle args) {
        super(context);
        if (args ==null) throw new IllegalStateException("args Bundle shouldn't be null");
        mNumbersLimit = args.getInt(MainActivity.KEY_PRIME_NUMBERS_LIMIT, 0);
    }

    @Override
    public PrimeResult loadInBackground() {
        boolean[] numbers = new boolean[mNumbersLimit + 1];

        for (int i = FIRST_PRIME; i <= mNumbersLimit; i++) numbers[i] = true;

        int p = FIRST_PRIME;

        while (p < mNumbersLimit) {
            if (!numbers[p]) {
                p++;
                continue;
            }
            int pm = p * p;
            while (pm <= mNumbersLimit && pm > 0) {
                numbers[pm] = false;
                pm += p;
            }
            p++;
        }

        List<Integer> resultValues = new ArrayList<>();

        long sum = 0;
        // 0 is not prime
        for (int i = FIRST_PRIME; i <= mNumbersLimit; i++) {
            if (numbers[i]) resultValues.add(i);
            sum += i;
        }

        return new PrimeResult(resultValues, sum);
    }
}
