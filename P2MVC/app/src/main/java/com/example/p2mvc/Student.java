package com.example.p2mvc;

public class Student {
    private int mNoControl;
    private String mName;
    private int mScore;
    //constructor

    public Student(int mNoControl, String mName, int mScore) {
        this.mNoControl = mNoControl;
        this.mName = mName;
        this.mScore = mScore;
    }

    public int getmNoControl() {
        return mNoControl;
    }

    public void setmNoControl(int mNoControl) {
        this.mNoControl = mNoControl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }
}
