package com.example.p3dosactividades;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {
    private Button mShowButtom;
    private TextView mMessageTextView;
    private TextView mNameTextView;
    private static final String SEND_MESSAGE ="Mensaje";
    private static final String NAME = "Carlos";
    private static final String EXTRA_NAME_SHOWN = "Carlos";
    //private static final String EXTRA_NAME_SHOWN = NAME; try this later

    public static Intent newIntent(Context context, String message){
        Intent i = new Intent(context, OtherActivity.class);
        i.putExtra(SEND_MESSAGE, message);
        return i;
    }

    public static String wasNameShow(Intent data) {
        return data.getStringExtra(EXTRA_NAME_SHOWN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        //Inflar
        mShowButtom=findViewById(R.id.show_button);
        mMessageTextView=findViewById(R.id.message_textView);
        mNameTextView=findViewById(R.id.name_textView);

        mShowButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = getIntent().getStringExtra(SEND_MESSAGE);
                mMessageTextView.setText(message);
                mNameTextView.setText(NAME);
                sendBackMain(NAME);
            }
        });
    }
    //mandar parametro el nombre del estudiante
    private void sendBackMain(String name) {
        Intent data = new Intent();
        //Asignar variable a constante
        data.putExtra(EXTRA_NAME_SHOWN, name);
        setResult(RESULT_OK, data);
    }
}
