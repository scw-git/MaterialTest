package cn.edu.bzu.ie.materialtest;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FruitAdapter extends RecyclerView .Adapter<FruitAdapter.ViewHolder>{
    private List<Fruit> list;
    private Context context ;

    //生成构造函数
    public FruitAdapter(List<Fruit> list) {
        this.list = list;
    }

    @NonNull
    @Override//加载子项布局
    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context==null){
            context = parent.getContext();//下面用到context，
        }
        View view = View.inflate(parent.getContext(),R.layout.fruit_item,null);
        final ViewHolder viewHolder = new ViewHolder(view);

        //设置最外层布局(item)点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAbsoluteAdapterPosition();
                Fruit fruit = list.get(position);//获取item的位置
                Intent intent = new Intent(context,FruitActivity.class);//点击图片或者文字时，跳转页面
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());//把水果名字传递到FruitActi中
                intent.putExtra(FruitActivity.IMAGE_ID,fruit.getImageId());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    //设置内容
    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit = list.get(position);
        holder.textView.setText(fruit.getName());
            Glide.with(context).load(fruit.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //加载子项布局的ID
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;
        public final CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;//好像可以直接用itemView（用itemView设置点击事件，则点击图片和文字都会响应）
            imageView = itemView.findViewById(R.id.image);//想要图片到点击事件，则用imageView代替itemView
            textView = itemView.findViewById(R.id.fruit_name);
        }
    }
}
