package cn.edu.bzu.ie.materialtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity  {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Fruit[] fruits={new Fruit("cherry",R.drawable.cherry),new Fruit("grape",R.drawable.grape)
    ,new Fruit("lemon",R.drawable.lemon),new Fruit("orange",R.drawable.orange)
            ,new Fruit("peach",R.drawable.peach),new Fruit("strawberry",R.drawable.strawberry)};
    private List<Fruit> list = new ArrayList<>();
    private FruitAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.myToolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        //悬浮按钮
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"编辑",Toast.LENGTH_SHORT).show();
            }
        });

        //使headerLayout（即上半部分）的事件可点击（这里是让头像可点击）
        //注意：要把布局文件中headerLayout删除，否则会出现两个布局
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_head);
        CircleImageView  circleImageView = headerLayout.findViewById(R.id.icon_image);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"you clik headImage",Toast.LENGTH_SHORT).show();
            }
        });


        //自定义的toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示左上角的图标
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);//设置一个menu

        //设置滑动菜单的点击事件
        navigationView.setCheckedItem(R.id.nav_call);//设置默认的选中状态
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_chat:
                        Toast.makeText(MainActivity.this,"你点击了聊天",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_cloud:
                        Toast.makeText(MainActivity.this,"你点击了☁图标",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_edit:
                        Toast.makeText(MainActivity.this,"你点击了编辑",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_call:
                        Toast.makeText(MainActivity.this,"你点击了打电话",Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawers();//关闭drawer：抽屉（navigationView）
                return false;
            }
        });

        //点击左上角图标，打开滑动菜单
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);//打开navigationView（drawer：抽屉）
            }
        });


        //显示水果
        initFruit();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        adapter = new FruitAdapter(list);
        recyclerView.setAdapter(adapter);

        //设置下拉刷新
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));//设置下拉圈的颜色
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFuit();
            }
        });
    }
    private void refreshFuit(){
        new Thread(new Runnable() {//开启一个线程
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);//让线程沉睡2秒，刷新太快看不到效果
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {//切换到主线程
                    @Override
                    public void run() {
                        initFruit();//从新生成数据
                        adapter.notifyDataSetChanged();//通知适配器，改变类数据
                        swipeRefreshLayout.setRefreshing(false);//关闭刷新
                    }
                });
            }
        }).start();//注意开启了一个线程要有start
    }

    private void initFruit(){
        list.clear();//清空水果列表
        for(int i=0;i<50;i++){//随机挑选水果加入list列表
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            list.add(fruits[index]);
        }
    }

    //渲染自定义ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.music:
                Toast.makeText(MainActivity.this,"you click music",Toast.LENGTH_SHORT).show();
                break;
            case R.id.taxi:
                Toast.makeText(MainActivity.this,"you click taxi",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
