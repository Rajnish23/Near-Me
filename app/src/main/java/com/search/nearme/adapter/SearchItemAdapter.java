package com.search.nearme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.search.nearme.R;
import com.search.nearme.utils.itemClickListener;
import com.search.nearme.model.SearchResultsItem;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder> {

    private static final String TAG = "SearchItemAdapter";

    private LayoutInflater inflater;

    private List<SearchResultsItem> searchResultsItemList;

    private itemClickListener itemCliclListener;

    public SearchItemAdapter(Context context,List<SearchResultsItem> searchResultsItems,itemClickListener itemCliclListener){
        inflater = LayoutInflater.from(context);
        searchResultsItemList = searchResultsItems;
        this.itemCliclListener=itemCliclListener;
    }

    public void setItem(List<SearchResultsItem> searchResultsItems){
        searchResultsItemList.clear();
        searchResultsItemList.addAll(searchResultsItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_result_item_layout,parent,false);
        return new SearchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchItemViewHolder holder, int position) {
        SearchResultsItem resultsItem = searchResultsItemList.get(position);
        holder.mName.setText(resultsItem.getName());
        if (resultsItem.getOpeningHours() != null){
            if (resultsItem.getOpeningHours().isOpenNow()) {
                holder.mOpen.setText(holder.llBicycle.getContext().getString(R.string.open_now));
            } else {
                holder.mOpen.setText(holder.llBicycle.getContext().getString(R.string.closed));
            }
         }
        holder.mRating.setRating((float)resultsItem.getRating());
        holder.mVicinity.setText(resultsItem.getVicinity());
    }

    @Override
    public int getItemCount() {
        if(searchResultsItemList!=null) {
            return searchResultsItemList.size();
        }
        return 0;
    }

    public class SearchItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.name)
        TextView mName;


        @BindView(R.id.txt_open)
        TextView mOpen;

        @BindView(R.id.item_rating)
        RatingBar mRating;

        @BindView(R.id.vicinity)
        TextView mVicinity;

        @BindView(R.id.ll_driving)
        LinearLayout llDriving;

        @BindView(R.id.ll_bicycle)
        LinearLayout llBicycle;

        @BindView(R.id.ll_walking)
        LinearLayout llWalking;

        public SearchItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            llDriving.setOnClickListener(this);
            llBicycle.setOnClickListener(this);
            llWalking.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_driving:
                    itemCliclListener.onClickDrivingMode(getAdapterPosition(),"driving");
                    break;
                case R.id.ll_bicycle:
                    itemCliclListener.onClickDrivingMode(getAdapterPosition(),"bicycling");
                    break;
                case R.id.ll_walking:
                    itemCliclListener.onClickDrivingMode(getAdapterPosition(),"walking");
                    break;
            }
        }
    }
}
