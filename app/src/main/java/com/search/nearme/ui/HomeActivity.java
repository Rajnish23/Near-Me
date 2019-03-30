package com.search.nearme.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.search.nearme.R;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = findViewById(R.id.home_recycler);

        String [] nameList = getResources().getStringArray(R.array.input);

        HomeScreenAdapter homeScreenAdapter = new HomeScreenAdapter(this,nameList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setAdapter(homeScreenAdapter);


    }

    private class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.HomseScreenViewHolder>{

        private LayoutInflater inflater;

        private String[] itemNameList;

        public HomeScreenAdapter(Context context,String[] itemNameList) {
            inflater = LayoutInflater.from(context);
            this.itemNameList = itemNameList;
        }

        @NonNull
        @Override
        public HomseScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.home_screen_item_layout,parent,false);
            return new HomseScreenViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HomseScreenViewHolder holder, int position) {
            holder.title.setText(itemNameList[position]);
        }

        @Override
        public int getItemCount() {
            return itemNameList.length;
        }

        public class HomseScreenViewHolder extends RecyclerView.ViewHolder {

            TextView title;
            public HomseScreenViewHolder(@NonNull View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);
            }
        }
    }
}
