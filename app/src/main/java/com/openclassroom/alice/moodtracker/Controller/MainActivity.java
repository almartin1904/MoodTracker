package com.openclassroom.alice.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.openclassroom.alice.moodtracker.R;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {


    private static final float SWIPE_THRESHOLD = 100;
    private static final float SWIPE_VELOCITY_THRESHOLD = 100;
    private GestureDetector gestureDetector;
    private int mood;
    private RelativeLayout mMainLayout;
    private ImageView mSmiley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetector(this);
        mood=0;


        mMainLayout = (RelativeLayout) findViewById(R.id.main_activity);
        mSmiley = (ImageView) findViewById(R.id.main_activity_smiley_img);

        displayMood(mood);
        mMainLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow));

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
        if (mood>0) {mood-=1;}
        displayMood(mood);
    }

    private void displayMood(int mood) {
        switch (mood) {
            case 0:
                mMainLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                mSmiley.setImageDrawable(getResources().getDrawable(R.drawable.smiley_super_happy));
                break;
            case 1:
                mMainLayout.setBackgroundColor(getResources().getColor(R.color.light_sage));
                mSmiley.setImageDrawable(getResources().getDrawable(R.drawable.smiley_happy));
                break;
            case 2:
                mMainLayout.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                mSmiley.setImageDrawable(getResources().getDrawable(R.drawable.smiley_normal));
                break;
            case 3:
                mMainLayout.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                mSmiley.setImageDrawable(getResources().getDrawable(R.drawable.smiley_disappointed));
                break;
            case 4:
                mMainLayout.setBackgroundColor(getResources().getColor(R.color.faded_red));
                mSmiley.setImageDrawable(getResources().getDrawable(R.drawable.smiley_sad));
                break;
            default :
                Toast.makeText(this, "You have a pb" + mood, Toast.LENGTH_SHORT).show();
        }
    }

    private void onSwipeBottom() {
        if (mood<4) {mood+=1;}
        displayMood(mood);
    }
}
