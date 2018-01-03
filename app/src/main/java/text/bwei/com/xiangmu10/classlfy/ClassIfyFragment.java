package text.bwei.com.xiangmu10.classlfy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import text.bwei.com.xiangmu10.R;
import text.bwei.com.xiangmu10.classlfy.adapter.MyRightAdapter;
import text.bwei.com.xiangmu10.classlfy.adapter.MyleftAdapter;
import text.bwei.com.xiangmu10.classlfy.api.Api;
import text.bwei.com.xiangmu10.classlfy.api.MessageLeft;
import text.bwei.com.xiangmu10.classlfy.bean.ClassLeft;
import text.bwei.com.xiangmu10.classlfy.bean.ClassRight;
import text.bwei.com.xiangmu10.classlfy.presenter.presenterRight;
import text.bwei.com.xiangmu10.classlfy.presenter.presenterleft;
import text.bwei.com.xiangmu10.classlfy.view.Iview;

import static text.bwei.com.xiangmu10.R.id.recycler_right;

/**
 * Created by dell on 2018/1/2.
 */

public class ClassIfyFragment extends Fragment implements Iview {
    @BindView(R.id.recycler_left)
    RecyclerView recyclerLeft;
    @BindView(recycler_right)
    RecyclerView recyclerRight;
    private View view;
    private Unbinder unbinder;
    private text.bwei.com.xiangmu10.classlfy.presenter.presenterleft presenterleft;
    private text.bwei.com.xiangmu10.classlfy.presenter.presenterRight presenterRight;
    private int cid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.classifyfragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenterleft = new presenterleft(this);
        presenterleft.getOkLeft(Api.URL);
        presenterRight = new presenterRight(this);
        presenterRight.getOkRight(Api.URL, 1);
        EventBus.getDefault().register(this);


    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getGee(MessageLeft messageLeft) {
        cid = messageLeft.getCid();
        presenterRight.getOkRight(Api.URL, cid);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        presenterleft.deatch();
        presenterRight.deatchright();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showleft(List<ClassLeft.DataBean> list) {
        recyclerLeft.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerLeft.setAdapter(new MyleftAdapter(list, getActivity()));
    }

    @Override
    public void showRight(List<ClassRight.DataBean> list) {
        recyclerRight.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerRight.setAdapter(new MyRightAdapter(list, getActivity()));
    }


}
