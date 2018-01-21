package testing.com.sampleapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sande on 1/20/2018.
 */

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    TextView txtHeader;
    public HeaderViewHolder(View itemView) {
        super(itemView);
        txtHeader = itemView.findViewById(R.id.txtViewHeader);
    }
}
