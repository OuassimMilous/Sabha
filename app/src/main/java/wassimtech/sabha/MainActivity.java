package wassimtech.sabha;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    FragmentManager fm;
    ConstraintLayout container;
    String fragname;
    SharedPreferences[] sp = new SharedPreferences[1];
    SharedPreferences.Editor point;
    String Lang;
    private TasbihFragment tasbihFragment;
    private DuaFragment duaFragment;
    private AboutAppFragment aboutAppFragment;
    private ShareFragment shareFragment;
    private Fragment duaactive;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            BottomNavigationView navigation = findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            if (fm.findFragmentByTag("MorningDuaFragment") != null) {
                if (!fm.findFragmentByTag("MorningDuaFragment").isHidden()) {
                    duaactive = fm.findFragmentByTag("MorningDuaFragment");
                }
            }
            if (fm.findFragmentByTag("EveningFragment") != null) {
                if (!fm.findFragmentByTag("EveningFragment").isHidden()) {
                    duaactive = fm.findFragmentByTag("EveningFragment");
                }
            }
            if (fm.findFragmentByTag("BeforeSleepingFragment") != null) {
                if (!fm.findFragmentByTag("BeforeSleepingFragment").isHidden()) {
                    duaactive = fm.findFragmentByTag("BeforeSleepingFragment");
                }
            }
            if (fm.findFragmentByTag("EatingDuaFragment") != null) {
                if (!fm.findFragmentByTag("EatingDuaFragment").isHidden()) {
                    duaactive = fm.findFragmentByTag("EatingDuaFragment");
                }
            }
            if (fm.findFragmentByTag("AfterThePrayerFragment") != null) {
                if (!fm.findFragmentByTag("AfterThePrayerFragment").isHidden()) {
                    duaactive = fm.findFragmentByTag("AfterThePrayerFragment");
                }
            }
            if (fm.findFragmentByTag("suaratAlKahfFragment") != null) {
                if (!fm.findFragmentByTag("suaratAlKahfFragment").isHidden()) {
                    duaactive = fm.findFragmentByTag("suaratAlKahfFragment");
                }
            }
            if (fm.findFragmentByTag("suaratAlmulkFragment") != null) {
                if (!fm.findFragmentByTag("suaratAlmulkFragment").isHidden()) {
                    duaactive = fm.findFragmentByTag("suaratAlmulkFragment");
                }
            }
            if (fm.findFragmentByTag("tasbihFragment") != null) {
                if (!fm.findFragmentByTag("tasbihFragment").isHidden()) {
                    fm.beginTransaction().hide(fm.findFragmentByTag("tasbihFragment")).commit();
                }
            }
            if (fm.findFragmentByTag("duaFragment") != null) {
                if (!fm.findFragmentByTag("duaFragment").isHidden()) {
                    fm.beginTransaction().hide(fm.findFragmentByTag("duaFragment")).commit();
                }
            }
            if (fm.findFragmentByTag("aboutAppFragment") != null) {
                if (!fm.findFragmentByTag("aboutAppFragment").isHidden()) {
                    fm.beginTransaction().hide(fm.findFragmentByTag("aboutAppFragment")).commit();
                }
            }
            if (fm.findFragmentByTag("shareFragment") != null) {
                if (!fm.findFragmentByTag("shareFragment").isHidden()) {
                    fm.beginTransaction().hide(fm.findFragmentByTag("shareFragment")).commit();
                }
            }

            switch (item.getItemId()) {

                case R.id.navigation_tasbih:

                    if (fm.findFragmentByTag("tasbihFragment") == null) {
                        fm.beginTransaction().add(R.id.main_frame, tasbihFragment, "tasbihFragment").hide(tasbihFragment).commit();
                    }

                    sp[0] = getSharedPreferences("sp[0]", Context.MODE_PRIVATE);
                    point = sp[0].edit();
                    point.putString("fragname", "tasbihFragment");
                    point.apply();

                    if (duaactive != null) {
                        fm.beginTransaction().hide(duaactive).commit();
                        duaactive = null;
                    }

                    fm.beginTransaction().show(tasbihFragment).commit();
                    navigation.getMenu().getItem(0).setChecked(true);

                    return true;
                case R.id.navigation_dua:
                    if (fm.findFragmentByTag("duaFragment") == null) {
                        fm.beginTransaction().add(R.id.main_frame, duaFragment, "duaFragment").hide(duaFragment).commit();
                    }

                    sp[0] = getSharedPreferences("sp[0]", Context.MODE_PRIVATE);
                    point = sp[0].edit();
                    point.putString("fragname", "duaFragment");
                    point.apply();

                    fm.beginTransaction().show(duaFragment).commit();
                    navigation.getMenu().getItem(1).setChecked(true);
                    return true;
                case R.id.navigation_about_app:
                    if (fm.findFragmentByTag("aboutAppFragment") == null) {
                        fm.beginTransaction().add(R.id.main_frame, aboutAppFragment, "aboutAppFragment").hide(aboutAppFragment).commit();
                    }
                    sp[0] = getSharedPreferences("sp[0]", Context.MODE_PRIVATE);
                    point = sp[0].edit();
                    point.putString("fragname", "aboutAppFragment");
                    point.apply();
                    if (duaactive != null) {
                        fm.beginTransaction().hide(duaactive).commit();
                        duaactive = null;
                    }

                    fm.beginTransaction().show(aboutAppFragment).commit();
                    navigation.getMenu().getItem(2).setChecked(true);
                    return true;
                case R.id.navigation_Sadaka_jariya:
                    if (fm.findFragmentByTag("shareFragment") == null) {
                        fm.beginTransaction().add(R.id.main_frame, shareFragment, "shareFragment").hide(shareFragment).commit();
                    }
                    sp[0] = getSharedPreferences("sp[0]", Context.MODE_PRIVATE);
                    point = sp[0].edit();
                    point.putString("fragname", "shareFragment");
                    point.apply();
                    if (duaactive != null) {
                        fm.beginTransaction().hide(duaactive).commit();
                        duaactive = null;
                    }

                    fm.beginTransaction().show(shareFragment).commit();
                    navigation.getMenu().getItem(3).setChecked(true);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


/*
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.SECOND,15);
        Intent intent = new Intent(getApplicationContext(),notification_reciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),120,intent,PendingIntent.FLAG_UPDATE_CURRENT);


        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_HALF_HOUR,pendingIntent);





        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY,11);
        calendar2.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        Intent intent2 = new Intent(getApplicationContext(),notification_reciver2.class);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(),110,intent2,PendingIntent.FLAG_UPDATE_CURRENT);


        AlarmManager alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager2.setRepeating(AlarmManager.RTC_WAKEUP,calendar2.getTimeInMillis(),AlarmManager.INTERVAL_HALF_HOUR,pendingIntent2);


*/

        PackageManager pm = this.getPackageManager();
        ComponentName receiver = new ComponentName(this, DeviceBootReceiver.class);
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //region Enable Daily Notifications
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.SECOND, 15);
        // if notification time is before selected time, send notification the next day
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }
        if (manager != null) {
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        }
        //To enable Boot Receiver class
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        //endregion

        PackageManager pm2 = this.getPackageManager();
        ComponentName receiver2 = new ComponentName(this, DeviceBootReceiver2.class);
        Intent alarmIntent2 = new Intent(this, AlarmReceiver2.class);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 1, alarmIntent2, 0);
        AlarmManager manager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //region Enable Daily Notifications
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY, 9);
        calendar2.set(Calendar.SECOND, 15);
        calendar2.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        // if notification time is before selected time, send notification the next day
        if (calendar2.before(Calendar.getInstance())) {
            calendar2.add(Calendar.DATE, 1);
        }
        if (manager2 != null) {
            manager2.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent2);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                manager2.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), pendingIntent2);
            }
        }
        //To enable Boot receiver2 class
        pm2.setComponentEnabledSetting(receiver2,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        //endregion


        sp[0] = getSharedPreferences("sp[0]", Context.MODE_PRIVATE);
        fragname = sp[0].getString("fragname", "tasbihFragment");

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        container = findViewById(R.id.container);

        tasbihFragment = new TasbihFragment();
        duaFragment = new DuaFragment();
        aboutAppFragment = new AboutAppFragment();
        shareFragment = new ShareFragment();

        fm = getSupportFragmentManager();

        if (fm.findFragmentByTag("MorningDuaFragment") != null) {
            if (!fm.findFragmentByTag("MorningDuaFragment").isHidden()) {
                duaactive = fm.findFragmentByTag("MorningDuaFragment");
            }
        }
        if (fm.findFragmentByTag("EveningFragment") != null) {
            if (!fm.findFragmentByTag("EveningFragment").isHidden()) {
                duaactive = fm.findFragmentByTag("EveningFragment");
            }
        }
        if (fm.findFragmentByTag("BeforeSleepingFragment") != null) {
            if (!fm.findFragmentByTag("BeforeSleepingFragment").isHidden()) {
                duaactive = fm.findFragmentByTag("BeforeSleepingFragment");
            }
        }
        if (fm.findFragmentByTag("EatingDuaFragment") != null) {
            if (!fm.findFragmentByTag("EatingDuaFragment").isHidden()) {
                duaactive = fm.findFragmentByTag("EatingDuaFragment");
            }
        }
        if (fm.findFragmentByTag("AfterThePrayerFragment") != null) {
            if (!fm.findFragmentByTag("AfterThePrayerFragment").isHidden()) {
                duaactive = fm.findFragmentByTag("AfterThePrayerFragment");
            }
        }
        if (fm.findFragmentByTag("suaratAlKahfFragment") != null) {
            if (!fm.findFragmentByTag("suaratAlKahfFragment").isHidden()) {
                duaactive = fm.findFragmentByTag("suaratAlKahfFragment");
            }
        }
        if (fm.findFragmentByTag("suaratAlmulkFragment") != null) {
            if (!fm.findFragmentByTag("suaratAlmulkFragment").isHidden()) {
                duaactive = fm.findFragmentByTag("suaratAlmulkFragment");
            }
        }
        if (fm.findFragmentByTag("tasbihFragment") != null) {
            if (!fm.findFragmentByTag("tasbihFragment").isHidden()) {
                fm.beginTransaction().hide(fm.findFragmentByTag("tasbihFragment")).commit();
            }
        }
        if (fm.findFragmentByTag("duaFragment") != null) {
            if (!fm.findFragmentByTag("duaFragment").isHidden()) {
                fm.beginTransaction().hide(fm.findFragmentByTag("duaFragment")).commit();
            }
        }
        if (fm.findFragmentByTag("aboutAppFragment") != null) {
            if (!fm.findFragmentByTag("aboutAppFragment").isHidden()) {
                fm.beginTransaction().hide(fm.findFragmentByTag("aboutAppFragment")).commit();
            }
        }
        if (fm.findFragmentByTag("shareFragment") != null) {
            if (!fm.findFragmentByTag("shareFragment").isHidden()) {
                fm.beginTransaction().hide(fm.findFragmentByTag("shareFragment")).commit();
            }
        }


        switch (fragname) {
            case "tasbihFragment":

                if (!tasbihFragment.isAdded()) {
                    fm.beginTransaction().add(R.id.main_frame, tasbihFragment, "tasbihFragment").hide(tasbihFragment).commit();
                }

                //   navigation.setSelectedItemId(R.id.navigation_tasbih);
                if (duaactive != null) {
                    fm.beginTransaction().hide(duaactive).commit();
                    duaactive = null;
                }

                fm.beginTransaction().show(tasbihFragment).commit();
                navigation.getMenu().getItem(0).setChecked(true);
                break;
            case "duaFragment":
                if (!duaFragment.isAdded()) {
                    fm.beginTransaction().add(R.id.main_frame, duaFragment, "duaFragment").hide(duaFragment).commit();
                }

                fm.beginTransaction().show(duaFragment).commit();
                //    navigation.setSelectedItemId(R.id.navigation_dua);
                if (duaactive != null) {
                    fm.beginTransaction().hide(duaactive).commit();
                    duaactive = null;
                }
                navigation.getMenu().getItem(1).setChecked(true);
                break;
            case "aboutAppFragment":
                if (!aboutAppFragment.isAdded()) {
                    fm.beginTransaction().add(R.id.main_frame, aboutAppFragment, "aboutAppFragment").hide(aboutAppFragment).commit();
                }

                fm.beginTransaction().show(aboutAppFragment).commit();
                //    navigation.setSelectedItemId(R.id.navigation_about_app);
                if (duaactive != null) {
                    fm.beginTransaction().hide(duaactive).commit();
                    duaactive = null;
                }
                navigation.getMenu().getItem(2).setChecked(true);
                break;
            case "shareFragment":
                if (!shareFragment.isAdded()) {
                    fm.beginTransaction().add(R.id.main_frame, shareFragment, "shareFragment").hide(shareFragment).commit();
                }

                fm.beginTransaction().show(shareFragment).commit();
                //   navigation.setSelectedItemId(R.id.navigation_Sadaka_jariya);
                if (duaactive != null) {
                    fm.beginTransaction().hide(duaactive).commit();
                    duaactive = null;
                }
                navigation.getMenu().getItem(3).setChecked(true);
                break;
        }


        sp[0] = getSharedPreferences("sp", Context.MODE_PRIVATE);

        Lang = sp[0].getString("Lang", Locale.getDefault().getDisplayLanguage());
        if (Lang.equals("en") && !Locale.getDefault().getLanguage().equals("en")) {
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
            Lang = "en";
            SharedPreferences.Editor point = sp[0].edit();
            point.putString("Lang", Lang);
            point.apply();
            recreate();

          /*  TasbihFragment tasbihFragment = TasbihFragment.this;
            if (fm.findFragmentByTag("tasbihFragment")==null) {
                fm.beginTransaction().add(R.id.main_frame, tasbihFragment, "tasbihFragment").hide(tasbihFragment).commit();
            }
              fm.beginTransaction().show(tasbihFragment).commit();
         fm.beginTransaction().show(tasbihFragment).commit();

           if (active!=null){

                       fm.beginTransaction().hide(active).show(tasbihFragment).commit();
           }  */


         /*   finish();
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);*/


        } else if (Lang.equals("ar") && !Locale.getDefault().getLanguage().equals("ar")) {
            Locale locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
            Lang = "ar";
            SharedPreferences.Editor point = sp[0].edit();
            point.putString("Lang", Lang);
            point.apply();
            recreate();
           /* FragmentManager fm;
            fm = getActivity().getSupportFragmentManager();
            TasbihFragment tasbihFragment = TasbihFragment.this;
            if (fm.findFragmentByTag("tasbihFragment")==null) {
                fm.beginTransaction().add(R.id.main_frame, tasbihFragment, "tasbihFragment").hide(tasbihFragment).commit();
            }
           fm.beginTransaction().show(tasbihFragment).commit();
       */


          /*  finish();
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);*/


        }


    }


}