package text.bwei.com.xiangmu10.goods;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import text.bwei.com.xiangmu10.R;
import text.bwei.com.xiangmu10.classlfy.api.Messagechilde;
import text.bwei.com.xiangmu10.goods.adapter.MyGoodsAdpater;
import text.bwei.com.xiangmu10.goods.api.Apis;
import text.bwei.com.xiangmu10.goods.bean.Goods;
import text.bwei.com.xiangmu10.goods.presenter.presenterGoods;
import text.bwei.com.xiangmu10.goods.view.IgoodView;

public class GoodsActivity extends AppCompatActivity implements IgoodView {


    @BindView(R.id.recycler_goods)
    RecyclerView recycler_goods;
    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout swiperefreshLayout;
    private text.bwei.com.xiangmu10.goods.presenter.presenterGoods presenterGoods;
    private int pscid;
    private int page = 1;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        presenterGoods = new presenterGoods(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED, sticky = true)
    public void getgoods(Messagechilde messagechilde) {
        pscid = messagechilde.getPscid();
        presenterGoods.getOkGood(Apis.GOOSDURL, pscid, page, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        presenterGoods.detach();//防内存溢出
    }

    @Override
    public void showgoods(final List<Goods.DataBean> list) {
        linearLayoutManager = new LinearLayoutManager(this);
        recycler_goods.setLayoutManager(linearLayoutManager);
        recycler_goods.setAdapter(new MyGoodsAdpater(list, this));
        recycler_goods.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == list.size() - 1) {
                    if (page < 3) {
                        page++;
                        presenterGoods.getOkGood(Apis.GOOSDURL, pscid, page, 0);
                    } else if (lastVisibleItemPosition == 0) {
                        presenterGoods.getOkGood(Apis.GOOSDURL, pscid, 3, 0);
                    }
                }
            }
        });

        swiperefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page++;
                presenterGoods.getOkGood(Apis.GOOSDURL, pscid, page, 0);
                swiperefreshLayout.setRefreshing(false);
            }
        });


    }
}
