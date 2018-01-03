package text.bwei.com.xiangmu10.classlfy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import text.bwei.com.xiangmu10.R;
import text.bwei.com.xiangmu10.classlfy.api.Messagechilde;
import text.bwei.com.xiangmu10.classlfy.bean.ClassRight;
import text.bwei.com.xiangmu10.goods.GoodsActivity;

/**
 * Created by dell on 2018/1/3.
 */

public class MyRightchildeAdapter extends RecyclerView.Adapter {
    List<ClassRight.DataBean.ListBean> list;
    Context context;

    public MyRightchildeAdapter(List<ClassRight.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.childeritem, parent, false);
        return new MychilderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MychilderViewHolder mychilderViewHolder = (MychilderViewHolder) holder;
        mychilderViewHolder.childer_text.setText(list.get(position).getName());
        mychilderViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new Messagechilde(list.get(position).getPscid()));
                Intent intent = new Intent(context, GoodsActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MychilderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.childer_text)
        TextView childer_text;

        public MychilderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
