package com.example.snapchatclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import androidx.fragment.app.FragmentTransaction;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //crate and get elements

        //TabLayout tabLayout=findViewById(R.id.tabs);
        //add to main activity xml if I want add tabs
        /** <com.google.android.material.tabs.TabLayout
         android:id="@+id/tabs"
         android:layout_width="match_parent"
         android:layout_height="wrap_content">

         </com.google.android.material.tabs.TabLayout>
         also add android:layout_below="@+id/tabs" to viewpager2 **/

        ViewPager2 viewPager2;
        viewPager2= findViewById(R.id.viewPager2);
        //before need create a layout for games and leading board
        ViewPageAdapter adapter= new ViewPageAdapter(this);
        viewPager2.setAdapter(adapter);
        //below make tabs move tab1,tab2,tab3
        /** new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("Tab"+(position +1));
            }
        }).attach(); **/

    }

}