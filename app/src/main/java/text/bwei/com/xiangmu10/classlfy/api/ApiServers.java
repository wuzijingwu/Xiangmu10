package text.bwei.com.xiangmu10.classlfy.api;


import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
import text.bwei.com.xiangmu10.classlfy.bean.ClassLeft;
import text.bwei.com.xiangmu10.classlfy.bean.ClassRight;

/**
 * Created by dell on 2018/1/2.
 */

public interface ApiServers {
    @GET("product/getCatagory")
    Observable<ClassLeft> getDataleft();

//    http://120.27.23.105/product/getProductCatagory?cid=1
    @POST
    Observable<ClassRight> getDataRight(@Url String url, @QueryMap Map<String, Integer> map);


}
