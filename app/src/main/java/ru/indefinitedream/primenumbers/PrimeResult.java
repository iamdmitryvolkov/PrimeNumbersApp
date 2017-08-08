package ru.indefinitedream.primenumbers;

import java.util.List;

/**
 * Container for data to return from loader
 */
public class PrimeResult {

    public PrimeResult(List<Integer> values, long sum) {
        mValues = values;
        mSum = sum;
    }

    public List<Integer> mValues;
    public long mSum;
}
