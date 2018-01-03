package text.bwei.com.xiangmu10.goods.model;

/**
 * Created by dell on 2018/1/3.
 */

public interface Imodel {
    //    pscid=1&page=1&sort=0
    void RequestGoodsSuccess(String url, int pscid, int page, int sort, OnselectGoods onselectGoods);


}
