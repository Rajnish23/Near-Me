package com.search.nearme.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.search.nearme.R;
import com.search.nearme.utils.itemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.HomseScreenViewHolder>{

        private LayoutInflater inflater;

        private String[] itemNameList;

        private TypedArray imageList;

        private itemClickListener itemClickListener;

        public HomeScreenAdapter(Context context, String[] itemNameList, TypedArray imageList,itemClickListener itemClickListener) {
            inflater = LayoutInflater.from(context);
            this.itemNameList = itemNameList;
            this.imageList = imageList;
            this.itemClickListener = itemClickListener;
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
            holder.ivIcon.setImageResource(imageList.getResourceId(position,0));

        }

        @Override
        public int getItemCount() {
            return itemNameList.length;
        }

        public class HomseScreenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView title;
            ImageView ivIcon;
            public HomseScreenViewHolder(@NonNull View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                title = itemView.findViewById(R.id.title);
                ivIcon = itemView.findViewById(R.id.iv_icon);
            }

            @Override
            public void onClick(View v) {
                itemClickListener.onClickItem(getAdapterPosition());
            }
        }


}