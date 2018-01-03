package text.bwei.com.xiangmu10.classlfy.view;

import java.util.List;

import text.bwei.com.xiangmu10.classlfy.bean.ClassLeft;
import text.bwei.com.xiangmu10.classlfy.bean.ClassRight;

/**
 * Created by dell on 2018/1/2.
 */

public interface Iview {
    void showleft(List<ClassLeft.DataBean> list);

    void showRight(List<ClassRight.DataBean> list);


}
