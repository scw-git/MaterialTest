package cn.edu.bzu.ie.materialtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity  {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.myToolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);



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


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示左上角的图标
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);//设置一个menu

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

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);//打开navigationView（drawer：抽屉）
            }
        });


    }

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
