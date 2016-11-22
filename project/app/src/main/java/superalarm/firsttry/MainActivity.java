package superalarm.firsttry;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextClock;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

import basic_class.Item;
import basic_class.ItemManager;
<<<<<<< HEAD
import basic_class.RepeatedAddtionException;

=======
>>>>>>> 73644653c23fbe4bbd8674d37e537e56858d54ca

public class MainActivity extends AppCompatActivity {

    public  static MainActivity instance = null;
    private TextClock hTextClock;
    private TextClock dTextClock;
    private boolean have_login;
    private ListView lv;
    private ItemManager items1 = new ItemManager();
    private ItemManager items2 = new ItemManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Item it1 = new Item("Study","study1","201610022345",1);
        Item it2 = new Item("Study","study2","201610023345",2);
        try {
            items1.add(it1);
            items1.add(it2);
            items1.write();
        } catch (RepeatedAddtionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            items2.read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RepeatedAddtionException e) {
            e.printStackTrace();
        }

        lv = (ListView)findViewById(R.id.list);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();/*在数组中存放数据*/
<<<<<<< HEAD
        for (int i = 0;i<items2.length;i++){
=======
        /*for (int i = 0;i<=20;i++){
>>>>>>> 73644653c23fbe4bbd8674d37e537e56858d54ca
            HashMap<String,Object> map = new HashMap<String,Object>();
            switch (i%5){
                case 0:map.put("ItemImage",R.drawable.communication);break;
                case 1:map.put("ItemImage",R.drawable.entertainment);break;
                case 2:map.put("ItemImage",R.drawable.study);break;
                case 3:map.put("ItemImage",R.drawable.exercise);break;
                default:map.put("ItemImage",R.drawable.food);
            }
            map.put("ItemTitle", items2.itemArr.get(i).title);
            map.put("ItemText", items2.itemArr.get(i).deadline);
            listItem.add(map);
        } */

<<<<<<< HEAD
        SimpleAdapter mSimpleAdaptr = new SimpleAdapter(this,listItem,R.layout.listitem,
                new String[]{"ItemImage","ItemTitle","ItemText"},new int[]{R.id.ItemImage,R.id.ItemTitle,R.id.ItemText});
        lv.setAdapter(mSimpleAdaptr);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
                Intent intent = new Intent(MainActivity.this,Detail.class);
                intent.putExtra("num",arg2);
                startActivity(intent);
            }
        });

        instance = this;
=======
        makeItemList();
>>>>>>> 73644653c23fbe4bbd8674d37e537e56858d54ca

        hTextClock = (TextClock)findViewById(R.id.textClock3);
        dTextClock = (TextClock)findViewById(R.id.textClock4);
        AssetManager mgr = getAssets();
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/FZLTCXHJW.TTF");
        hTextClock.setTypeface(tf);
        dTextClock.setTypeface(tf);

        ImageButton btn_person = (ImageButton)findViewById(R.id.imageButton_person);
        btn_person.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,PersonalInformation.class);
                startActivity(intent);
//                finish();
            }
        });

        ImageButton btn_group = (ImageButton)findViewById(R.id.imageButton_group);
        btn_group.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,JsonActivity.class);
                startActivity(intent);
                //Toast.makeText(MainActivity.this,"No Service",Toast.LENGTH_LONG).show();
            }
        });

        ImageButton btn_new = (ImageButton)findViewById(R.id.imageButton_add);
        btn_new.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,type_set_activity.class);
                startActivity(intent);
//                finish();
            }
        });

        int y = 2016, m = 10, d = 19, h = 9, min = 38;
        int Id = 0;
        startRemind(y, m, d, h, min, Id + 1);
        startRemind(y, m, d, h, min + 4, Id + 2);
        startRemind(y, m, d, h, min + 6, Id + 3);

    }

   private void startRemind(int year, int month, int day, int hour, int minute, int Id) {
//    private void startRemind() {
       Calendar mCalendar = Calendar.getInstance();
       mCalendar.setTimeInMillis(System.currentTimeMillis());

       //获取当前毫秒值
       long systemTime = System.currentTimeMillis();

       //设置日历的时间，让日历的年月日和当前同步
       mCalendar.setTimeInMillis(System.currentTimeMillis());
       //时区设置
       mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
       mCalendar.set(Calendar.YEAR, year);
       mCalendar.set(Calendar.MONTH, month);
       mCalendar.set(Calendar.DAY_OF_MONTH, day);
       mCalendar.set(Calendar.HOUR_OF_DAY, hour);
       mCalendar.set(Calendar.MINUTE, minute);
       mCalendar.set(Calendar.SECOND, 0);
       mCalendar.set(Calendar.MILLISECOND, 0);

       long selectTime = mCalendar.getTimeInMillis();

       if (systemTime > selectTime) {
           Toast.makeText(MainActivity.this, "This alarm time is expired", Toast.LENGTH_LONG).show();
           return;
       }

        //AlarmReceiver.class为广播接受者
       Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
       intent.putExtra("Id",Id);
       PendingIntent pi = PendingIntent.getBroadcast(
               MainActivity.this, Id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
       //得到AlarmManager实例
       AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
       String str = String.valueOf(year) + " " + String.valueOf(month) + String.valueOf(day) + " "
               + String.valueOf(hour) + String.valueOf(minute);
       Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
       am.setExact(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pi);
    }

    private void stopRemind(int Id){

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        intent.putExtra("Id",Id);
        PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, Id,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        //取消警报
        am.cancel(pi);
        Toast.makeText(this, "关闭了提醒", Toast.LENGTH_SHORT).show();

    }

    private void clearNotification(int nId){
        NotificationManager notificationManager = (NotificationManager) this
                .getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(nId);

    }

    private void makeItemList() {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();/*在数组中存放数据*/
        ItemManager itemManager = new ItemManager();
        itemManager.read(this);
        itemManager.write(this);
        ArrayList<Item> itemArrayList = itemManager.getItemArr();
        for (int i = 0; i < itemManager.getLength(); ++i) {
            final String title = itemArrayList.get(i).getTitle();
            final String text = itemArrayList.get(i).getDeadline();
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("ItemImage",R.drawable.exercise);
            map.put("ItemTitle", title);
            map.put("ItemText", text);
            listItem.add(map);
        }

        SimpleAdapter mSimpleAdaptr = new SimpleAdapter(this,listItem,R.layout.listitem,
                new String[]{"ItemImage","ItemTitle","ItemText"},new int[]{R.id.ItemImage,R.id.ItemTitle,R.id.ItemText});
        lv.setAdapter(mSimpleAdaptr);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(MainActivity.this,Detail.class);
                intent.putExtra("string_key","0"+arg2);
                startActivity(intent);
            }
        });

        instance = this;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        //clearNotification(intent.getIntExtra("Id", 0));
        super.onStart();
    }

    @Override
    protected void onRestart() {
        makeItemList();
    }
}
