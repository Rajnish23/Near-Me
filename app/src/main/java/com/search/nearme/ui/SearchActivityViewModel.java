package com.search.nearme.ui;

import android.util.Log;

import com.search.nearme.model.Response;
import com.search.nearme.model.SearchResultsItem;
import com.search.nearme.utils.APIClient;
import com.search.nearme.utils.APIService;
import com.search.nearme.utils.AppConst;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchActivityViewModel extends ViewModel {
    private static final String TAG = "SearchActivityViewModel";

    private APIService apiService;

    private CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<List<SearchResultsItem>> responseMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> showProgressLiveData = new MutableLiveData<>();

    public SearchActivityViewModel() {
        apiService = APIClient.getClient().create(APIService.class);

    }

    public void fetchNearByData(String type,String location){
        showProgressLiveData.setValue(true);
        disposable.add(apiService.searchPlaces(AppConst.GOOGLE_PLACE_API_KEY,
                type,location,AppConst.DISTANCE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((response)->{
                    return response.getResults();
                })
        .subscribe(searchResultsItems -> {
            showProgressLiveData.setValue(false);
            responseMutableLiveData.setValue(searchResultsItems);
        }));


    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
