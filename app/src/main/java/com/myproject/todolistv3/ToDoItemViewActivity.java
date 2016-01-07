package com.myproject.todolistv3;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.View;

import java.util.ArrayList;

public class ToDoItemViewActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<ToDoItem> items;
    int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_item_view);

        Intent launchingIntent = getIntent();
        items = launchingIntent.getParcelableArrayListExtra("TODO_ITEMS");
        selectedPosition = launchingIntent.getIntExtra("SELECTED_POSITION", 0);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        FragmentManager manager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                ToDoItem item = items.get(position);
                ItemViewFragment viewFragment = new ItemViewFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("ITEM", item);
                viewFragment.setArguments(bundle);
                return viewFragment;
            }

            @Override
            public int getCount() {
                return items.size();
            }
        });
        viewPager.setCurrentItem(selectedPosition);
    }
}
