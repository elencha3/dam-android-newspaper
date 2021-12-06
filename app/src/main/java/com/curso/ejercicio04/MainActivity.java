package com.curso.ejercicio04;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ListView sideMenu;
    String url1, url2, name1, name2;
    ImageView imageView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.main_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name,
                R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sideMenu = findViewById(R.id.side_menu);
        sideMenu.setOnItemClickListener(this);
        imageView = findViewById(R.id.image);
        Animation appear = AnimationUtils.loadAnimation(this, R.anim.appear);
        imageView.startAnimation(appear);
        textView = findViewById(R.id.title);
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        textView.startAnimation(rotate);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                cambiarPantalla(0);
            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        cambiarPantalla(position);

    }

    public void cambiarPantalla(int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        InicioFragment inicioFragment = new InicioFragment();
        Web1Fragment web1Fragment = new Web1Fragment();
        Web2Fragment web2Fragment = new Web2Fragment();


        switch (i) {
            case 0:
                transaction.replace(R.id.content, inicioFragment);

                break;
            case 1:

                setTitle(name1);
                transaction.replace(R.id.content, web1Fragment);
                web1Fragment.receiveUrl1(url1);

                break;
            case 2:
                setTitle(name2);
                transaction.replace(R.id.content, web2Fragment);
                web2Fragment.receiveUrl2(url2);

                break;
            default:

        }

        transaction.commit();
        drawerLayout.closeDrawer(GravityCompat.START);

    }

    public void receiveUrl1(String data1) {
        this.url1 = data1;
    }

    public void receiveUrl2(String data2) {
        this.url2 = data2;
    }

    public void receiveName1(String data1) {
        this.name1 = data1;
    }

    public void receiveName2(String data2) {
        this.name2 = data2;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        drawerToggle.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


}