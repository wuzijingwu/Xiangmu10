package text.bwei.com.xiangmu10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import text.bwei.com.xiangmu10.classlfy.ClassIfyFragment;
import text.bwei.com.xiangmu10.home.HomeFragment;
import text.bwei.com.xiangmu10.me.MeFragment;
import text.bwei.com.xiangmu10.shop.ShopCarFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottom_tab_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottom_tab_bar.init(getSupportFragmentManager())
                .addTabItem("首页", R.mipmap.ic_launcher, HomeFragment.class)
                .addTabItem("分类", R.mipmap.ic_launcher, ClassIfyFragment.class)
                .addTabItem("购物车", R.mipmap.ic_launcher, ShopCarFragment.class)
                .addTabItem("个人", R.mipmap.ic_launcher, MeFragment.class);
    }
}
