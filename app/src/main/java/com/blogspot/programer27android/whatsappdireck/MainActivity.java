package com.blogspot.programer27android.whatsappdireck;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp1();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsapp2();
            }
        });
    }


    private void openWhatsApp1(){
        Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=60138811227&text=hai saya lupa ini siapa");
        try{
            Intent i =new Intent(Intent.ACTION_VIEW,uri);
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this,"Silahkan Download Aplikasi terlebih dahulu",Toast.LENGTH_LONG).show();
        }

    }
    private void openWhatsapp2(){
        String smsNumber = "971556847589";
        boolean isWhatsappInstalled = whatsappInstalledornot("com.whatsapp");
        if (isWhatsappInstalled) {
            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");
            //phone number without "+" prefix
            startActivity(sendIntent);
        } else {
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            Toast.makeText(this, "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            startActivity(goToMarket);
        }
    }

    private boolean whatsappInstalledornot(String uri){
        PackageManager pm=getPackageManager();
        boolean appinstall=false;
        try{
            pm.getPackageInfo(uri,PackageManager.GET_ACTIVITIES);
            appinstall=true;
        } catch (PackageManager.NameNotFoundException e) {
            appinstall=true;
        }
        return appinstall;
    }
}
