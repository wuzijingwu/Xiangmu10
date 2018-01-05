package text.bwei.com.xiangmu10.details.model;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import text.bwei.com.xiangmu10.details.api.ApiServers;
import text.bwei.com.xiangmu10.details.bean.DetailsBean;

/**
 * Created by dell on 2018/1/4.
 */

public class model implements Idetailsmodel {
    @Override
    public void RequestDetails(String url, int pid, final OnselectDetails onselectDetails) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("pid", pid);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServers apiServers = retrofit.create(ApiServers.class);
        Observable<DetailsBean> detailsBeanObservable = apiServers.getdataDetails("product/getProductDetail", map);
        detailsBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        DetailsBean.DataBean data = detailsBean.getData();
                        onselectDetails.dataDetailsSuccess(data);
                    }
                });


    }
}
