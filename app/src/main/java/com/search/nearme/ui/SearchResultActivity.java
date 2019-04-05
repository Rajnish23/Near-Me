package com.search.nearme.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.search.nearme.R;
import com.search.nearme.adapter.SearchItemAdapter;
import com.search.nearme.utils.itemClickListener;
import com.search.nearme.model.SearchResultsItem;
import com.search.nearme.utils.AppConst;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity implements itemClickListener {

    private String searchtype;

    private SearchActivityViewModel searchActivityViewModel;

    private RecyclerView mRecyclerView;

    private SearchItemAdapter searchItemAdapter;

    private String title;

    private List<SearchResultsItem> searchResultsItemList;

    private static final String TAG = "SearchResultActivity";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        if(getIntent()!=null){
            title = getIntent().getStringExtra("title");
            searchtype = getIntent().getStringExtra("type");
            searchtype = searchtype.replace(" ","_");
        }

        GetIds();

        setUpViewModel();
    }

    private void GetIds() {
        AppBarLayout appBarLayout = findViewById(R.id.search_app_bar);
        Toolbar toolbar = findViewById(R.id.search_toolbar);
        mRecyclerView = findViewById(R.id.searchRecycler);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.please_wait));
        progressDialog.setMessage(getString(R.string.fetch_details));


    }

    @Override
    protected void onResume() {
        super.onResume();
        searchActivityViewModel.fetchNearByData(searchtype, AppConst.getLocationValue());
    }

    private void setUpViewModel() {

        searchActivityViewModel = ViewModelProviders.of(this).get(SearchActivityViewModel.class);

        searchActivityViewModel.showProgressLiveData.observe(this,value->{
            if(value){
                progressDialog.show();
            }
            else{
                progressDialog.dismiss();
            }
        });

        searchActivityViewModel.responseMutableLiveData.observe(this,searchResultsItems -> {
            this.searchResultsItemList = searchResultsItems;
            searchItemAdapter = new SearchItemAdapter(this,searchResultsItems,this);
            mRecyclerView.setAdapter(searchItemAdapter);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickItem(int pos) {


    }

    @Override
    public void onClickDrivingMode(int pos, String mode) {

        if(mode.equalsIgnoreCase(getString(R.string.bicycling))){
            Snackbar view = Snackbar.make(mRecyclerView,getString(R.string.bicycle_do_not_support),Snackbar.LENGTH_SHORT);
            view.getView().setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
            view.show();

        }
        else {
            Intent locationIntent = new Intent(SearchResultActivity.this, LocationActivity.class);
            locationIntent.putExtra("dest_lat", searchResultsItemList.get(pos).getGeometry().getLocation().getLat());
            locationIntent.putExtra("dest_lng", searchResultsItemList.get(pos).getGeometry().getLocation().getLng());
            locationIntent.putExtra("mode", mode);
            startActivity(locationIntent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }
}
