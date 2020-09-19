//package com.sia.siaandroidapp.java.viewmodel.retrofit.roles;
//
//
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import com.sia.siaandroidapp.java.interfaces.RxSingleSchedulers;
//
//import javax.inject.Inject;
//
//import io.reactivex.disposables.CompositeDisposable;
//
//public class RolesViewModel2 extends ViewModel {
//
//    private CompositeDisposable disposable;
//    private final NewsApiClient apiClient;
//    private final RxSingleSchedulers rxSingleSchedulers;
//    private final MutableLiveData<NewsListViewState> newsListState = new MutableLiveData<>();
//
//    public MutableLiveData<NewsListViewState> getNewsListState() {
//        return newsListState;
//    }
//
//    @Inject
//    public NewsViewModel(NewsApiClient apiClient, RxSingleSchedulers rxSingleSchedulers) {
//        this.apiClient = apiClient;
//        this.rxSingleSchedulers = rxSingleSchedulers;
//        disposable = new CompositeDisposable();
//    }
//
//    public void fetchNews() {
//        disposable.add(apiClient.fetchNews()
//                .doOnEvent((newsList, throwable) -> onLoading())
//                .compose(rxSingleSchedulers.applySchedulers())
//                .subscribe(this::onSuccess,
//                        this::onError));
//    }
//
//    private void onSuccess(NewsList newsList) {
//        NewsListViewState.SUCCESS_STATE.setData(newsList);
//        newsListState.postValue(NewsListViewState.SUCCESS_STATE);
//    }
//
//    private void onError(Throwable error) {
//        NewsListViewState.ERROR_STATE.setError(error);
//        newsListState.postValue(NewsListViewState.ERROR_STATE);
//    }
//
//    private void onLoading() {
//        newsListState.postValue(NewsListViewState.LOADING_STATE);
//    }
//
//    @Override
//    protected void onCleared() {
//        super.onCleared();
//        if (disposable != null) {
//            disposable.clear();
//            disposable = null;
//        }
//    }
//}
