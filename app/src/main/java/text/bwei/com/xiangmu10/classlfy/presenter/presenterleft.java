package text.bwei.com.xiangmu10.classlfy.presenter;

import java.util.List;

import text.bwei.com.xiangmu10.classlfy.bean.ClassLeft;
import text.bwei.com.xiangmu10.classlfy.model.Imodel;
import text.bwei.com.xiangmu10.classlfy.model.Onselectleft;
import text.bwei.com.xiangmu10.classlfy.model.model;
import text.bwei.com.xiangmu10.classlfy.view.Iview;

/**
 * Created by dell on 2018/1/2.
 */

public class presenterleft {
    Imodel imodel;
    Iview iview;

    public presenterleft(Iview iview) {
        this.iview = iview;
        imodel = new model();
    }
    public void deatch(){
        iview=null;
    }

    public void getOkLeft(String url) {
        imodel.RequestLeft(url, new Onselectleft() {
            @Override
            public void dataleftsuccess(List<ClassLeft.DataBean> list) {
                iview.showleft(list);
            }
        });


    }

}
