package in.naynesh.activitylifecycle;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static in.naynesh.activitylifecycle.App.CHANNEL_1_ID;
import static in.naynesh.activitylifecycle.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private EditText edtitle;
    private EditText edmsg;
    String t, m;
    ListView listview;
    Button btnnot;
    private NotificationManager mNotificationManager;
    private NotificationManagerCompat notificationManagerCompat;
    private static String DEFAULT_CHANNEL_ID = "default_channel";
    private static String DEFAULT_CHANNEL_NAME = "Default";
    String contacts[] = {"Naynesh", "Shankar", "Sachin", "Sumit", "Yogesh"};

    public static void createNotificationChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(DEFAULT_CHANNEL_ID) == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT));
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtitle = findViewById(R.id.edtitle);
        edmsg = findViewById(R.id.edmsg);
        listview = findViewById(R.id.listview);
        ArrayAdapter<String> adepter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
        listview.setAdapter(adepter);
        registerForContextMenu(listview);
        notificationManagerCompat = NotificationManagerCompat.from(this);

        btnnot = findViewById(R.id.Btn_notofy);
        btnnot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                createNotificationChannel(mNotificationManager);
                Notification notification = new NotificationCompat.Builder(getApplicationContext(), DEFAULT_CHANNEL_ID).setContentTitle("Simple Notification").setContentText("This is A Cont notificaton menu").setSmallIcon(android.R.drawable.ic_lock_idle_alarm).build();
                mNotificationManager.notify(1, notification);
            }
        });
    }

    public void sendOnChannel1(View v) {
        t = edtitle.getText().toString().trim();
        m = edmsg.getText().toString().trim();
        Notification notification = new NotificationCompat.Builder(getApplicationContext(),
                CHANNEL_1_ID)
                .setContentTitle(t)
                .setContentText(m)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setSmallIcon(android.R.drawable.ic_popup_disk_full)
                .build();
        notificationManagerCompat.notify(1, notification);
    }

    public void sendOnChannel2(View v) {
        t = edtitle.getText().toString().trim();
        m = edmsg.getText().toString().trim();
        Notification notification = new NotificationCompat.Builder(getApplicationContext(),
                CHANNEL_2_ID)
                .setContentTitle(t)
                .setContentText(m)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setSmallIcon(android.R.drawable.ic_popup_disk_full)
                .build();
        notificationManagerCompat.notify(2, notification);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.setHeaderTitle("Select The Action ");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.call) {
            Toast.makeText(this, "Calling Code", Toast.LENGTH_SHORT).show();
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            createNotificationChannel(mNotificationManager);
            Notification notification = new NotificationCompat.Builder(getApplicationContext(),
                    DEFAULT_CHANNEL_ID)
                    .setContentTitle("This is Calling  Notification")
                    .setContentText("This is A Calling  notification for use as ")
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)
                    .build();
            mNotificationManager.notify(1, notification);
        } else if (item.getItemId() == R.id.sms) {
            Toast.makeText(this, "Sending SMS Code", Toast.LENGTH_SHORT).show();
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            createNotificationChannel(mNotificationManager);
            Notification notification = new NotificationCompat.Builder(getApplicationContext(), DEFAULT_CHANNEL_ID).setContentTitle("This is SMS  Notification").setContentText("This is A SMs  notification for use as ").setSmallIcon(android.R.drawable.ic_dialog_email).build();
            mNotificationManager.notify(1, notification);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Selected item : " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.Search_item:
                return true;
            case R.id.Bookmark_item:
                return true;
            case R.id.Copy_item:
                return true;
            case R.id.Share_item:
                return true;
            case R.id.Upload_item:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart: Invoked");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume: invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause: Invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop: Invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "onRestart: Invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "onDestroy: Invoked");
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.copy_data:
                Toast.makeText(this, "Copy Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.paste_data:
                Toast.makeText(this, "Paste Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.move_data:
                Toast.makeText(this, "Move Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete_data:
                Toast.makeText(this, "Delete Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }

    }
}
