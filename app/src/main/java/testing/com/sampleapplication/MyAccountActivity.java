package testing.com.sampleapplication;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.HeaderViewListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class MyAccountActivity extends AppCompatActivity {

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    class Confirm extends VoiceInteractor.ConfirmationRequest {
//        public Confirm(String ttsPrompt, String visualPrompt) {
//            VoiceInteractor.Prompt prompt = new VoiceInteractor.Prompt(new String[] {ttsPrompt}, visualPrompt);
//            super(prompt, null);
//        }
//
//        @Override
//        public void onConfirmationResult(boolean confirmed, Bundle null) {
//            if (confirmed) {
//                doAction();
//            }
//            finish();
//        }
//    };

    private static final String TAG = MyAccountActivity.class.getSimpleName();
    @BindView(R.id.recyleview_myaccout_menus)
    RecyclerView recyclerView;

    private SectionedRecyclerViewAdapter sectionAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
       /* else if (CameraActivMyAccountActivity.needPermissions(this)) {
            startActivity(new Intent(this, CameraActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
            return;
        }*/
//        else if (!isVoiceInteraction()) {
//            Log.e(TAG, "Not voice interaction");
//            intent.setComponent(null);
//            intent.setPackage("com.google.android.GoogleCamera");
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            finish();
//            return;
//        }



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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();
        if (isVoiceInteraction()) {
//            String ttsPrompt = getConfirmationTts();
//            String visualPrompt = getConfirmationDisplayText();
//            getVoiceInteractor().sendRequest(new Confirm(ttsPrompt, visualPrompt));
            Toast.makeText(this, "from VOice :: ", Toast.LENGTH_SHORT).show();
        }


        // Start my main non-voice Activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

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
