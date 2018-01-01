package testing.com.sampleapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import q.rorbin.badgeview.QBadgeView;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    BottomNavigationView bottomNavigation;
    TextView slideshow;
    QBadgeView qbview;

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
                    setMenuCounter(R.id.navigation_notifications,2);
                    //initializeCountDrawer();
                    //qbview.setBadgeNumber(4);
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
       // bottomNavigation.getMenu().getItem(2).setActionView(R.layout.menu_counter);

        BottomNavigationMenuView bottomNavigationMenuView =(BottomNavigationMenuView) bottomNavigation.getChildAt(0);
//        View v = bottomNavigationMenuView.getChildAt(2); // number of menu from left
//         qbview =  new QBadgeView(this);
//        qbview.bindTarget(v).setBadgeBackground(getDrawable(R.drawable.ic_launcher));
//        qbview.setBadgeGravity(Gravity.END|Gravity.TOP);
//        qbview.setBadgeNumber(3);


        


         slideshow = (TextView) MenuItemCompat.getActionView(bottomNavigation.getMenu().
                findItem(R.id.navigation_notifications));

        //initializeCountDrawer();


        createFakeNotification();
        //setMenuCounter(R.id.navigation_notifications,2);

        //TextView view = (TextView) bottomNavigation.getMenu().findItem(R.id.navigation_notifications).getActionView();
        //view.setText("sad");

        //MenuItem.item.setActionView(R.layout.menu_counter);

    }

    private void initializeCountDrawer(){
        //Gravity property aligns the text
        slideshow.setGravity(Gravity.CENTER_VERTICAL);
        slideshow.setTypeface(null, Typeface.BOLD);
        slideshow.setTextColor(getResources().getColor(R.color.colorAccent));
        slideshow.setText("99+");
        slideshow.setGravity(Gravity.CENTER_VERTICAL);
        slideshow.setTypeface(null,Typeface.BOLD);
        slideshow.setTextColor(getResources().getColor(R.color.colorAccent));
//count is added
        slideshow.setText("7");
    }

    private void createFakeNotification() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                CustomNotification notification = new CustomNotification.Builder()
//                        .setText("1")
//                        .setBackgroundColor(Color.YELLOW)
//                        .setTextColor(Color.BLACK)
//                        .build();
                // Adding notification to last item.

                //MenuItem.setActionView(2);

                //bottomNavigation.setNotification(notification, bottomNavigation.getItemsCount() - 1);

                //notificationVisible = true;
                //setMenuCounter(R.id.navigation_notifications,3);
                setMenuCounter(R.id.navigation_dashboard,20);

                TextView view = (TextView) bottomNavigation.getMenu().findItem(R.id.navigation_notifications).getActionView();
                view.setText("sad");
                //qbview.setBadgeNumber(70);
            }
        }, 2000);
    }

    private void setMenuCounter(@IdRes int itemId, int count) {
        TextView view = (TextView) bottomNavigation.getMenu().findItem(itemId).getActionView();
        view.setText("11");
        view.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    public  void updateItem(BottomNavigationView bottomNavigationView, int index, Drawable icon) {
        Menu menu = bottomNavigationView.getMenu();
        MenuItem item = menu.getItem(index);

        if (item != null) {
            item.setIcon(icon);
        }
    }
}
