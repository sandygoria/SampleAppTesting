package testing.com.sampleapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.HeaderViewListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class MyAccountActivity extends AppCompatActivity {

    @BindView(R.id.recyleview_myaccout_menus)
    RecyclerView recyclerView;

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyAccountActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        HeaderRecylerViewSection firstSection = new HeaderRecylerViewSection("First Section", getDataSourceOne());
        HeaderRecylerViewSection secondSection = new HeaderRecylerViewSection("Second Section", getDataSourceTwo());
        //HeaderRecylerViewSection thirdSection = new HeaderRecylerViewSection("Third Section", getDataSource());

        sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(firstSection);
        sectionAdapter.addSection(secondSection);
        //sectionAdapter.addSection(thirdSection);
        recyclerView.setAdapter(sectionAdapter);

    }


    private List<ItemObject> getDataSourceOne(){
        List<ItemObject> data = new ArrayList<ItemObject>();
        data.add(new ItemObject("Header 1 Item 1"));
        data.add(new ItemObject("Header 1 Item 2"));
        return data;
    }

    private List<ItemObject> getDataSourceTwo(){
        List<ItemObject> data = new ArrayList<ItemObject>();
        data.add(new ItemObject("Header 2 Item 1"));
        data.add(new ItemObject("Header 2 Item 2"));
        return data;
    }
}
