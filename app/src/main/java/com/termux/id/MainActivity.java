package com.termux.id;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.Context;
import android.widget.TextView;
import android.content.Intent;
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView notif = (TextView)findViewById(R.id.info);
        if(!CekKoneksi()){
          notif.setText("Cek Koneksi InternetMu !");
        }else{
        new Handler().postDelayed(new Runnable() { 
            @Override
            public void run() {
              Intent intent = new Intent(getBaseContext(),Memetren.class);
              startActivity(intent);
              finish();
            }
        },2000);
        }
    }
    private boolean CekKoneksi() {
    boolean haveConnectedWifi = false;
    boolean haveConnectedMobile = false;

    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
    for (NetworkInfo ni : netInfo) {
        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
            if (ni.isConnected())
                haveConnectedWifi = true;
        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
            if (ni.isConnected())
                haveConnectedMobile = true;
    }
    return haveConnectedWifi || haveConnectedMobile;
  }
}