package testing.com.sampleapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import testing.com.sampleapplication.R;

/**
 * Created by sande on 1/20/2018.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView mItemSection;

    public ItemViewHolder(View itemView) {
        super(itemView);
        mItemSection =  itemView.findViewById(R.id.txtViewItemSection);
    }
}
