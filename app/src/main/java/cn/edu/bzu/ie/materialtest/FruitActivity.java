package cn.edu.bzu.ie.materialtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME = "fruit_name";
    public static final String IMAGE_ID = "image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);//接收水果到名字
        int fruitImageId = intent.getIntExtra(IMAGE_ID,0);//接收水果的ID
        Toolbar toolbar = findViewById(R.id.toolBar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing);
        ImageView imageView = findViewById(R.id.image);
        TextView fruitContentText = findViewById(R.id.fruit_content_text);
        setSupportActionBar(toolbar);//设置自定义到 toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置左上角有个返回键
        collapsingToolbarLayout.setTitle(fruitName);//设置标题
        Glide.with(this).load(fruitImageId).into(imageView);//设置图片
        String content = fruitContent(fruitName);//获取水果界面到内容
        fruitContentText.setText(content);//textView设置内容
    }

    //循环名字，来作为内容
    private String fruitContent(String fruitName){
        StringBuilder fruitContent = new StringBuilder();
        for(int i=0;i<500;i++){
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    //设置左上角到点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://左上角到ID，就是这个
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
