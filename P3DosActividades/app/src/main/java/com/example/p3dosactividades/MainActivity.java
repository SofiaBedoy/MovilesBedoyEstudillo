package com.example.p3dosactividades;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_NAME = 0;
    private static final String TAG = "etiqueta";
    //Declaration
    private Button mOtherActivityButton;
    private TextView mHelloTextView;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inflation
        mOtherActivityButton=findViewById(R.id.other_activity_button);
        mHelloTextView=findViewById(R.id.message_textView);

        //OnClickEvent
        mOtherActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=mHelloTextView.getText().toString();
                //Intent i = new Intent(MainActivity.this, OtherActivity.class);
                Intent i = OtherActivity.newIntent(MainActivity.this, message);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode!= Activity.RESULT_OK)
        {
            return;
        }
        if(requestCode==REQUEST_CODE_NAME) {
            if(data == null)
            {
                return;
            }
            mName = OtherActivity.wasNameShow(data);
            mHelloTextView.setText(mName);
        }
        Log.d(TAG, "onActivityResult() llamado");
    }
}
