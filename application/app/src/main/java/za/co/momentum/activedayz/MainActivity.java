package za.co.momentum.activedayz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;



public class MainActivity extends AppCompatActivity {
    Button buttonAchieve, buttonCommunity, buttonLeaders;
    private BeaconManager beaconManager;
    private BeaconManager beaconManager2;
    private StopWatch timer;
    private Region region;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        beaconManager = new BeaconManager(this);
        beaconManager2 = new BeaconManager(getApplicationContext());
        timer = new StopWatch();

        final String[] currentBeacon = {""};
        final String prevBeacon = "";
        region = new Region("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

        /*beaconManager2.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                showNotification(currentBeacon[0], "Arrived");
            }
            @Override
            public void onExitedRegion(Region region) {
                showNotification(currentBeacon[0], "Arrived");
            }
        });*/


        beaconManager.setRangingListener(new BeaconManager.RangingListener() {


            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    final Beacon nearestBeacon = list.get(0);
                    final String BeaconDescription = placesNearBeacon(nearestBeacon);
                    if (!currentBeacon[0].equals(BeaconDescription)) {
                        ;
                        if (!currentBeacon[0].equals("")) {
                            try {
                                timer.stop();
                                String text = getPhoneName() + ": Departed " + currentBeacon[0] + " after " + timer.getElapsedTimeSecs() + " seconds";
                                sendDataToServer(text);
                                showNotification(currentBeacon[0], "Departed after " + timer.getElapsedTimeSecs() + " seconds");
                            }catch (Exception e){

                            }

                        }
                        timer.start();
                        currentBeacon[0] = BeaconDescription;
                        sendDataToServer(getPhoneName() + ": Arrived at " + currentBeacon[0]);
                        showNotification(BeaconDescription, "Arrived");
                    }
                } else {
                    if (!currentBeacon[0].equals("")) {
                        try {
                            timer.stop();
                            String text = getPhoneName() +  ": Departed " + currentBeacon[0] + " after " + timer.getElapsedTimeSecs() + " seconds";
                            sendDataToServer(text);
                            showNotification(currentBeacon[0], "Departed after " + timer.getElapsedTimeSecs() + " seconds");
                        }catch (Exception e){

                        }
                    }
                    currentBeacon[0] = "";
                }
            }
        });

        // Locate the button in activity_main.xml
        buttonAchieve = (Button) findViewById(R.id.achievements);
        // Capture button clicks
        buttonAchieve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        Achievements.class);
                startActivity(myIntent);
            }
        });

        // Locate the button in activity_main.xml
        buttonCommunity = (Button) findViewById(R.id.community);
        // Capture button clicks
        buttonCommunity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        Community.class);
                startActivity(myIntent);
            }
        });

        // Locate the button in activity_main.xml
        buttonLeaders = (Button) findViewById(R.id.leaders);
        // Capture button clicks
        buttonLeaders.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        Leaders.class);
                startActivity(myIntent);
            }
        });
    }

    //RANGING
    @Override
    protected void onResume() {
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(region);
        super.onPause();
    }

    //Will read from a se
    private static final Map<String, String> PLACES_BY_BEACONS;

    static {
        Map<String, String> placesByBeacons = new HashMap<>();
        placesByBeacons.put("1058:7609", "Virgin Active (PINK)");
        placesByBeacons.put("64011:42975", "Planet Fitness (YELLOW)");
        placesByBeacons.put("18520:32557", "Marathon (PURPLE)");
        PLACES_BY_BEACONS = Collections.unmodifiableMap(placesByBeacons);
    }

    private String placesNearBeacon(Beacon beacon) {
        String beaconKey = String.format("%d:%d", beacon.getMajor(), beacon.getMinor());
        if (PLACES_BY_BEACONS.containsKey(beaconKey)) {
            return PLACES_BY_BEACONS.get(beaconKey);
        }
        return "";
    }

    //Pushes Notifications To Device
    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[]{notifyIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    private void sendDataToServer(String msg){
        final String json = msg;

        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... voids) {
                String james = getServerResponse(json);
                this.cancel(true);
                return "";
            }

            @Override
            protected void onPostExecute(String result){
                this.cancel(true);
            }

        }.execute();
    }

    private String getServerResponse(String json) {
        ArrayList<NameValuePair> postParameters;
        HttpPost post = new HttpPost("http://ec2-52-214-35-225.eu-west-1.compute.amazonaws.com:9005");
        try {
            postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("Message", json));
            post.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
            post.setHeader("Content-type","application/json");
            post.setHeader("message",json);
            DefaultHttpClient client = new DefaultHttpClient();
            BasicResponseHandler handler = new BasicResponseHandler();
            String response = client.execute(post, handler);
        }catch(UnsupportedEncodingException e){

        }catch(ClientProtocolException e){

        }catch(IOException e){

        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    public String getPhoneName() {
        BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
        String deviceName = myDevice.getName();
        return deviceName;
    }
}