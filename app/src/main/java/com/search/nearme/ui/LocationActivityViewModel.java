package com.search.nearme.ui;

import android.util.Log;

import com.search.nearme.utils.APIClient;
import com.search.nearme.utils.APIService;
import com.search.nearme.utils.AppConst;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LocationActivityViewModel extends ViewModel {

    private static final String TAG = "LocationActivityViewMod";

    private APIService apiService;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<String> polyLine = new MutableLiveData<>();

    public MutableLiveData<Boolean> showProgressLiveData = new MutableLiveData<>();

    public LocationActivityViewModel() {
        apiService = APIClient.getClient().create(APIService.class);
    }

    public void fetchRoute(String origin,String destination,String drivingMode){
        showProgressLiveData.setValue(true);
        compositeDisposable
                .add(apiService.getRoute(AppConst.GOOGLE_PLACE_API_KEY,
                                        origin,destination,drivingMode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->{
                    showProgressLiveData.setValue(false);

                    if("OK".equalsIgnoreCase(result.getStatus())) {
                        polyLine.setValue(result.getRoutes().get(0).getOverviewPolyline().getPoints());
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
