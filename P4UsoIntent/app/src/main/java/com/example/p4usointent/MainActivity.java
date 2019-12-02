package com.example.p4usointent;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declaration
    private Button mCallButton;
    private Button mCameraButton;
    private Button mNavegatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inflation
        mCallButton=findViewById(R.id.call_button);
        mCameraButton=findViewById(R.id.camera_button);
        mNavegatorButton=findViewById(R.id.navegator_button);

        //OnClickEvent
        mCallButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                String phone = "6644289331";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);

                /*Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:6644289331"));
                startActivity(callIntent);*/

                /*Intent i=new Intent(Intent.ACTION_CALL,Uri.parse("6645839929"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(i);*/
            }
        });

        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                //
                Intent i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(i);
            }
        });

        mNavegatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.mx"));
                startActivity(i);
            }
        });
    }
}

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode!= Activity.RESULT_OK)
        {
            return;
        }
        Log.d(TAG, "onActivityResult() llamado");
    }*/
