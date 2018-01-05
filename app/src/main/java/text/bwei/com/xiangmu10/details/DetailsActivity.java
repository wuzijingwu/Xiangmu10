package text.bwei.com.xiangmu10.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import text.bwei.com.xiangmu10.R;
import text.bwei.com.xiangmu10.details.api.Apidetails;
import text.bwei.com.xiangmu10.details.api.MessageDetails;
import text.bwei.com.xiangmu10.details.bean.DetailsBean;
import text.bwei.com.xiangmu10.details.presenter.presenterDetails;
import text.bwei.com.xiangmu10.details.view.Idetailsview;

public class DetailsActivity extends AppCompatActivity implements Idetailsview {

    @BindView(R.id.detail_banner)
    XBanner detail_banner;
    @BindView(R.id.details_title)
    TextView details_title;
    @BindView(R.id.details_text)
    TextView details_text;
    @BindView(R.id.details_price)
    TextView details_price;
    @BindView(R.id.buy_now)
    Button buy_now;
    @BindView(R.id.add_cart)
    Button add_cart;

    private int pid;
    private text.bwei.com.xiangmu10.details.presenter.presenterDetails presenterDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        //应该获取pid
        presenterDetails = new presenterDetails(this);


    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED, sticky = true)
    public void getDetails(MessageDetails messageDetails) {
        int pid = messageDetails.getPid();
        presenterDetails.getOkDetails(Apidetails.DETAILSURL, pid);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void shouDetails(DetailsBean.DataBean list) {


        final List<String> img_list = new ArrayList<>();
        String images = list.getImages();
        String[] split = images.split("\\|");
        for (int i = 0; i < split.length; i++) {
            img_list.add(split[i]);
        }
        detail_banner.setData(img_list, null);
        detail_banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(DetailsActivity.this).load(img_list.get(position)).into((ImageView) view);
            }
        });


        details_title.setText(list.getTitle());
        details_text.setText(list.getSubhead());
        details_price.setText(list.getPrice());


    }
}
