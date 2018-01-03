package text.bwei.com.xiangmu10.classlfy.adapter;

import android.content.Context;
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
import text.bwei.com.xiangmu10.classlfy.api.MessageLeft;
import text.bwei.com.xiangmu10.classlfy.bean.ClassLeft;

/**
 * Created by dell on 2018/1/2.
 */

public class MyleftAdapter extends RecyclerView.Adapter {

    List<ClassLeft.DataBean> list;
    Context context;

    public MyleftAdapter(List<ClassLeft.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.classlfyitem_left, parent, false);
        return new MyviewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyviewHolder myviewHolder = (MyviewHolder) holder;
        myviewHolder.classlfyText.setText(list.get(position).getName());
        myviewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new MessageLeft(list.get(position).getCid()));

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.classlfy_text)
        TextView classlfyText;

        public MyviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
