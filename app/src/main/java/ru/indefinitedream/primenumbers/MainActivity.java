package ru.indefinitedream.primenumbers;

import android.app.LoaderManager;
import android.content.Loader;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        LoaderManager.LoaderCallbacks<PrimeResult>, TextView.OnEditorActionListener {

    public static final String KEY_PRIME_NUMBERS_LIMIT = "valueN";

    private static final int PRIME_LOADER_ID = 0;

    @BindView(R.id.editText)
    protected EditText mEditText;
    @BindView(R.id.sumTextView)
    protected TextView mSumTextView;
    @BindView(R.id.countTextView)
    protected TextView mCountTextView;
    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.recyclerView)
    protected RecyclerView mRecycler;
    private PrimeNumbersAdapter mAdapter;

    private int mPrimeValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new PrimeNumbersAdapter();
        mRecycler.setAdapter(mAdapter);

        mPrimeValues = 0;
        if (savedInstanceState != null) {
            mPrimeValues = savedInstanceState.getInt(KEY_PRIME_NUMBERS_LIMIT, 0);
        }
        mEditText.setText(Integer.toString(mPrimeValues));

        Bundle args = new Bundle();
        args.putInt(KEY_PRIME_NUMBERS_LIMIT, mPrimeValues);
        getLoaderManager().initLoader(PRIME_LOADER_ID, args, this);

    }

    @OnClick(R.id.button)
    void startUpdate() {
        try {
            mPrimeValues = Integer.parseInt(mEditText.getText().toString());
        } catch (NumberFormatException e) {
            mPrimeValues = 0;
        }
        mProgressBar.setVisibility(View.VISIBLE);
        Bundle args = new Bundle();
        args.putInt(KEY_PRIME_NUMBERS_LIMIT, mPrimeValues);
        getLoaderManager().restartLoader(PRIME_LOADER_ID, args, this).forceLoad();
    }

    @Override
    public Loader<PrimeResult> onCreateLoader(int id, Bundle args) {
        return new PrimeLoader(this, args);
    }

    @Override
    public void onLoadFinished(android.content.Loader<PrimeResult> loader, PrimeResult data) {
        mProgressBar.setVisibility(View.INVISIBLE);
        Resources res = getResources();
        mSumTextView.setText(res.getString(R.string.sum) + ' ' + data.mSum);
        mCountTextView.setText(res.getString(R.string.founded) + ' ' + data.mValues.size());
        mAdapter.updateValues(data.mValues);
    }

    @Override
    public void onLoaderReset(android.content.Loader<PrimeResult> loader) {

    }

    @Override
    public void onClick(View v) {
        startUpdate();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PRIME_NUMBERS_LIMIT, mPrimeValues);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            startUpdate();
            return true;
        }
        return false;
    }
}
