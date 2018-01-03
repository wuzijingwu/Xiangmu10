package text.bwei.com.xiangmu10.classlfy.presenter;

import java.util.List;

import text.bwei.com.xiangmu10.classlfy.bean.ClassRight;
import text.bwei.com.xiangmu10.classlfy.model.Imodel;
import text.bwei.com.xiangmu10.classlfy.model.OnselectRight;
import text.bwei.com.xiangmu10.classlfy.model.model;
import text.bwei.com.xiangmu10.classlfy.view.Iview;

/**
 * Created by dell on 2018/1/3.
 */

public class presenterRight {
    Iview iview;
    Imodel imodel;

    public presenterRight(Iview iview) {
        this.iview = iview;
        imodel = new model();
    }
    public void deatchright(){
        iview=null;
    }

    public void getOkRight(String url, int cid) {
        imodel.RequestRight(url, cid, new OnselectRight() {
            @Override
            public void dataRight(List<ClassRight.DataBean> list) {
                iview.showRight(list);
            }
        });


    }


}
