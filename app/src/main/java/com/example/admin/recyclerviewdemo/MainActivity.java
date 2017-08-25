package com.example.admin.recyclerviewdemo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Data> dataList;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;
    private MyRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    if (lastVisibleItemPosition == dataList.size() - 1) {
                        initData();
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }
        });

    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Data data = new Data("这是条目" + i, R.mipmap.ic_launcher);
            dataList.add(data);
        }
        adapter = new MyRecyclerViewAdapter(dataList, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        layoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        StaggeredGridLayoutManager staggGridManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDecoration(MainActivity.this));
    }
}
