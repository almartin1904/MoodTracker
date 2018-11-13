package com.openclassroom.alice.moodtracker.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.openclassroom.alice.moodtracker.R;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {


    private static final float SWIPE_THRESHOLD = 100;
    private static final float SWIPE_VELOCITY_THRESHOLD = 100;
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetector(this);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result=false;
        float diffY = moveEvent.getY()-downEvent.getY();
        float diffX = moveEvent.getX()-downEvent.getX();
        if (Math.abs(diffX)<Math.abs(diffY)){
            if (Math.abs(diffY)> SWIPE_THRESHOLD && Math.abs(velocityY)>SWIPE_VELOCITY_THRESHOLD){
                if (diffY>0){
                    onSwipeBottom();
                } else {
                    onSwipeTop();
                }
            }
            result= true;
        }

        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void onSwipeTop() {
        Toast.makeText(this, "Swipe Top", Toast.LENGTH_SHORT).show();
    }

    private void onSwipeBottom() {
        Toast.makeText(this, "Swipe Bottom", Toast.LENGTH_SHORT).show();
    }
}
