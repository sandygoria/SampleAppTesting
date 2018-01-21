package testing.com.sampleapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by sande on 1/19/2018.
 */

class HeaderRecylerViewSection extends StatelessSection {

    private Context mContext;

    String mTitle;
    List<ItemObject> mItemObjList;

    public HeaderRecylerViewSection(String title, List<ItemObject> list) {
        super(R.layout.view_header, R.layout.view_itemsection);
        mTitle = title;
        mItemObjList = list;
    }

    @Override
    public int getContentItemsTotal() {
        return mItemObjList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder iHolder = (ItemViewHolder)holder;
        iHolder.mItemSection.setText(mItemObjList.get(position).getContents());
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }
    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder hHolder = (HeaderViewHolder)holder;
        hHolder.txtHeader.setText(mTitle);
    }
}
