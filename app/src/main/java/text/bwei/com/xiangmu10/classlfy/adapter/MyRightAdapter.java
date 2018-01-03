package text.bwei.com.xiangmu10.classlfy.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import text.bwei.com.xiangmu10.R;
import text.bwei.com.xiangmu10.classlfy.bean.ClassRight;

/**
 * Created by dell on 2018/1/3.
 */

public class MyRightAdapter extends RecyclerView.Adapter {
    List<ClassRight.DataBean> list;
    Context context;

    public MyRightAdapter(List<ClassRight.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.classlfyitem_right, parent, false);
        return new MyRightViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyRightViewHolder myRightViewHolder = (MyRightViewHolder) holder;
        myRightViewHolder.classlfytextRight.setText(list.get(position).getName());
        List<ClassRight.DataBean.ListBean> list1 = this.list.get(position).getList();
        myRightViewHolder.recyclerrightChilde.setLayoutManager(new GridLayoutManager(context,4));
        myRightViewHolder.recyclerrightChilde.setAdapter(new MyRightchildeAdapter(list1,context));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyRightViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.classlfytext_right)
        TextView classlfytextRight;
        @BindView(R.id.recyclerright_childe)
        RecyclerView recyclerrightChilde;

        public MyRightViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
