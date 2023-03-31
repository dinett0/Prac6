package com.example.prac6_r;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        // set the layout parameters of the window
        final WindowManager.LayoutParams params = new
                WindowManager.LayoutParams(
                // Shrink the window to wrap the content rather
                // than filling the screen
                WindowManager.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                // Display it on top of other application windows
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                // Don't let it grab the input focus
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                // Make the underlying application window visible
                // through any transparent parts
                PixelFormat.TRANSLUCENT);

        // inflating the view with the custom layout we created
        View view = LayoutInflater.from(this).inflate(R.layout.banner, null);
        ((TextView) view.findViewById(R.id.textBanner)).setText("BannerText");
        windowManager.addView(view, params);
        // Define the position of the
        // window within the screen
        params.gravity = Gravity.BOTTOM | Gravity.CENTER;
        windowManager.updateViewLayout(view, params);

        view.findViewById(R.id.closeBtnBanner).setOnClickListener(v -> {
            Intent intent2 = new Intent(this, MainActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
            windowManager.removeView(view);
        });

        return super.onStartCommand(intent, flags, startId);
    }
}