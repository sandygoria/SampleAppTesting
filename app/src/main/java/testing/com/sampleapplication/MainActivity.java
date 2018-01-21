package testing.com.sampleapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

//import com.crashlytics.android.Crashlytics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import q.rorbin.badgeview.QBadgeView;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    BottomNavigationView bottomNavigation;
    TextView slideshow;
    QBadgeView qbview;
    TextView txtViewCounter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    //qbview.hide(true);
                    return true;

                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    //qbview.hide(false);
                    Intent i = new Intent(MainActivity.this, MainActivityTab.class);
                    startActivity(i);
                    return true;

                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    //setMenuCounter(R.id.navigation_notifications,2);
                  //  item.setActionView(R.layout.menu_counter);
                    //updateItem(bottomNavigation,2, getDrawable(R.drawable.ic_launcher));
                    //setMenuCounter(R.id.navigation_notifications,2);
                    //initializeCountDrawer();
                    //qbview.setBadgeNumber(4);
                    txtViewCounter.setVisibility(View.INVISIBLE);
                    Intent i2 = new Intent(MainActivity.this, MyAccountActivity.class);
                    startActivity(i2);

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationMenuView bottomNavigationMenuView =(BottomNavigationMenuView) bottomNavigation.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        View badge = LayoutInflater.from(this) .inflate(R.layout.notification_badge, bottomNavigationMenuView, false);
        txtViewCounter = badge.findViewById(R.id.txtview_badgeview);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yy";     //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                // update the Textview with selected Date from DatePickerDialog.
                mTextMessage.setText(sdf.format(myCalendar.getTime()));
            }
        };

        // u can have any widget here-say Button, Textview, EditText.. and write this on click of that widget
        // opens a DatePickerDialog
        mTextMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        //itemView.addView(badge);

        //createFakeNotification();
    }


    private void createFakeNotification() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtViewCounter.setVisibility(View.VISIBLE);
                //txtViewCounter.setText("20");
            }
        }, 2000);
    }

    private void saveAndRetrieveMap(){
        Map<String, String> aMap = new HashMap<>();
        aMap.put("user_id", "1P_123");
        aMap.put("close_clicked", "closed");

        SharedPreferences keyValues = getSharedPreferences("Your_Shared_Prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor keyValuesEditor = keyValues.edit();

        for (String s : aMap.keySet()) {
            keyValuesEditor.putString(s, aMap.get(s));
        }

        keyValuesEditor.apply();
    }

    private void writeToFile() {

        Map<String, String> aMap = new HashMap<>();
        aMap.put("user_id", "1P_123");
        aMap.put("close_clicked", "closed");

        File file = new File(getDir("data.txt", MODE_PRIVATE), "user_map");
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(aMap);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMap(){
        // create an ObjectInputStream for the file we created before
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("data.txt"));
            // read and print an object and cast it as string
            System.out.println("" +ois.readObject());

            // read and print an object and cast it as string
            byte[] read = (byte[]) ois.readObject();
            String s2 = new String(read);
            Map<String,String> aMapp  = new HashMap<>();

            

            System.out.println("" + s2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
