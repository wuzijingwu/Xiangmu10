package text.bwei.com.xiangmu10.goods.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import text.bwei.com.xiangmu10.R;
import text.bwei.com.xiangmu10.details.DetailsActivity;
import text.bwei.com.xiangmu10.details.api.MessageDetails;
import text.bwei.com.xiangmu10.goods.bean.Goods;

/**
 * Created by dell on 2018/1/3.
 */

public class MyGoodsAdpater extends RecyclerView.Adapter {
    List<Goods.DataBean> list;
    Context context;

    public MyGoodsAdpater(List<Goods.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.goodsactivity_item, parent, false);
        return new MyGoodsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyGoodsViewHolder myGoodsViewHolder = (MyGoodsViewHolder) holder;
        myGoodsViewHolder.goodactivity_text.setText(list.get(position).getTitle());

        String images = list.get(position).getImages();
        if (images != null) {
            String[] split = images.split("\\|");
            Uri parse = Uri.parse(split[0]);
            myGoodsViewHolder.sdv_image.setImageURI(parse);
        }
        myGoodsViewHolder.goodactivity_price.setText(list.get(position).getPrice().toString());
        myGoodsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new MessageDetails(list.get(position).getPid()));
                Intent intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyGoodsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdv_image)
        SimpleDraweeView sdv_image;
        @BindView(R.id.goodactivity_text)
        TextView goodactivity_text;
        @BindView(R.id.goodactivity_price)
        TextView goodactivity_price;

        public MyGoodsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
