package com.search.nearme.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import com.search.nearme.R;
import com.search.nearme.adapter.HomeScreenAdapter;
import com.search.nearme.utils.itemClickListener;


public class HomeActivity extends AppCompatActivity implements itemClickListener {

    private static final String TAG = "HomeActivity";

    private RecyclerView mRecyclerView;

    private String [] nameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = findViewById(R.id.home_recycler);

        nameList = getResources().getStringArray(R.array.input);

        final TypedArray imageArray = getResources().obtainTypedArray(R.array.input_icon);

        HomeScreenAdapter homeScreenAdapter = new HomeScreenAdapter(this,nameList,imageArray,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setAdapter(homeScreenAdapter);

        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.near_me));

    }

    @Override
    public void onClickItem(int pos) {

        String type = nameList[pos].toLowerCase();
        Intent searchIntent = new Intent(HomeActivity.this,SearchResultActivity.class);
        searchIntent.putExtra("title",nameList[pos]);
        searchIntent.putExtra("type",type);
        startActivity(searchIntent);
    }

    @Override
    public void onClickDrivingMode(int pos, String mode) {

    }
}
