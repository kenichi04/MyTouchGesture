package com.example.mytouchgesture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.VelocityTrackerCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;

public class MainActivity extends AppCompatActivity{
    private static final String DEBUG_TAG = "MultiTouchGesture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 戻り値はイベントを消費するかの真偽値
    // イベントが消費された場合、他のハンドラでこのイベントを利用することができなくなる
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        // pointerIdはタッチにおけるポインターの識別子.ジェスチャーの中で
        // 一意になるIDがふられる.
        int pointerId = event.getPointerId(index);

        int xPos = -1;
        int yPos = -1;

        Log.d(DEBUG_TAG, "The action is " + actionToString(action));

        if (event.getPointerCount() > 1) {
            // The coordinates of the current screen contact, relative to
            // the responding View or Activity.
            xPos = (int)MotionEventCompat.getX(event, index);
            yPos = (int)MotionEventCompat.getY(event, index);
            Log.d(DEBUG_TAG, "Multitouch event index: " + index +
                    " x:" + xPos + " y:" + yPos);

        } else {
            // Single touch event
            xPos = (int)MotionEventCompat.getX(event, index);
            yPos = (int)MotionEventCompat.getY(event, index);
            Log.d(DEBUG_TAG, "Single touch event index: " + index +
                    " x:" + xPos + " y:" + yPos);
        }

        return true;
    }

    // Given an action int, returns a string description
    public static String actionToString(int action) {
        switch(action) {
            case MotionEvent.ACTION_DOWN: return "Down";
            case MotionEvent.ACTION_MOVE: return "Move";
            case MotionEvent.ACTION_POINTER_DOWN: return "Pointer Down";
            case MotionEvent.ACTION_UP: return "Up";
            case MotionEvent.ACTION_POINTER_UP: return "Pointer Up";
            case MotionEvent.ACTION_OUTSIDE: return "Outside";
            case MotionEvent.ACTION_CANCEL: return "Cancel";
        }
        return "";
    }

}