package text.bwei.com.xiangmu10.details.presenter;

import text.bwei.com.xiangmu10.details.DetailsActivity;
import text.bwei.com.xiangmu10.details.bean.DetailsBean;
import text.bwei.com.xiangmu10.details.model.Idetailsmodel;
import text.bwei.com.xiangmu10.details.model.OnselectDetails;
import text.bwei.com.xiangmu10.details.model.model;
import text.bwei.com.xiangmu10.details.view.Idetailsview;

/**
 * Created by dell on 2018/1/4.
 */

public class presenterDetails {
    Idetailsview idetailsview;
    Idetailsmodel idetailsmodel;

    public presenterDetails(DetailsActivity idetailsview) {
        this.idetailsview = idetailsview;
        idetailsmodel = new model();
    }

    public void getOkDetails(String url, int pid) {
        idetailsmodel.RequestDetails(url, pid, new OnselectDetails() {
            @Override
            public void dataDetailsSuccess(DetailsBean.DataBean list) {
                idetailsview.shouDetails(list);
            }
        });

    }


}
