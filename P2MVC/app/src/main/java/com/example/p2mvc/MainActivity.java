package com.example.p2mvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mStudentTextView;
    //Declaracion de constante
    private static final String TAG="etiqueta";

    private static final String KEY_INDEX="indice";

    Student []mStudents=new Student[]{
            new Student(111,"Carlos",100),
            new Student(222,"Ana",80),
            new Student(333,"Luis",69)
    };
    //indice del arreglo
    private int mCurrentIndex=0;

    private void updateStudent(){
        mStudentTextView.setText(
                mStudents[mCurrentIndex].getmNoControl()+"\n"+
                        mStudents[mCurrentIndex].getmName()+"\n"+
                        mStudents[mCurrentIndex].getmScore()
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX);
        }

        //Inflar widgets
        mNextButton=findViewById(R.id.next_button);
        mPreviousButton=findViewById(R.id.previous_button);
        mStudentTextView=findViewById(R.id.student_textView);
        //event onClick
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex==mStudents.length-1)
                {
                    mCurrentIndex=0;
                    updateStudent();
                }
                else {
                    mCurrentIndex++;
                    updateStudent();
                }
                mCurrentIndex=(mCurrentIndex+1)%(mStudents.length);
                Log.i(TAG, "El Indice es "+mCurrentIndex);
                updateStudent();
            }
        });
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex==0)
                {
                    mCurrentIndex=mStudents.length-1;
                    updateStudent();
                }
                else {
                    mCurrentIndex--;
                    updateStudent();
                }
            }
        });
        updateStudent();
        Log.d(TAG, "onCreate() llamado");
    }
    @Override
    public void onSaveInstanceState (Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState llamado");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
    @Override
    protected void onStart() {

        super.onStart();
        Log.d(TAG, "onStart() llamado");
    }
    @Override
    protected void onResume() {

        super.onResume();
        Log.d(TAG, "onResume() llamado");
    }
    @Override
    protected void onPause() {

        super.onPause();
        Log.d(TAG, "onPause() llamado");
    }
    @Override
    protected void onStop() {

        super.onStop();
        Log.d(TAG, "onStop() llamado");
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.d(TAG, "onDestroy() llamado");
    }
}
