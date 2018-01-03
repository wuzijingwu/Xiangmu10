package text.bwei.com.xiangmu10.classlfy.model;

import java.util.HashMap;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import text.bwei.com.xiangmu10.classlfy.api.ApiServers;
import text.bwei.com.xiangmu10.classlfy.bean.ClassLeft;
import text.bwei.com.xiangmu10.classlfy.bean.ClassRight;

/**
 * Created by dell on 2018/1/2.
 */

public class model implements Imodel {
    @Override
    public void RequestLeft(String url, final Onselectleft onselectleft) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServers apiServers = retrofit.create(ApiServers.class);
        Observable<ClassLeft> dataleft = apiServers.getDataleft();
        dataleft.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ClassLeft>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ClassLeft classLeft) {
                        List<ClassLeft.DataBean> data = classLeft.getData();
                        onselectleft.dataleftsuccess(data);
                    }
                });


    }

    @Override
    public void RequestRight(String url, int cid, final OnselectRight onselectRight) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("cid", cid);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServers apiServers = retrofit.create(ApiServers.class);
        Observable<ClassRight> dataRight = apiServers.getDataRight("product/getProductCatagory", map);
        dataRight.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ClassRight>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ClassRight classRight) {
                        List<ClassRight.DataBean> data = classRight.getData();

                        onselectRight.dataRight(data);
                    }
                });


    }
}
