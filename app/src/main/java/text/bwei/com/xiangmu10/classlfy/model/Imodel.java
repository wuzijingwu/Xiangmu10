package text.bwei.com.xiangmu10.classlfy.model;

/**
 * Created by dell on 2018/1/2.
 */

public interface Imodel {
    void RequestLeft(String url, Onselectleft onselectleft);

    void RequestRight(String url, int cid, OnselectRight onselectRight);

}
