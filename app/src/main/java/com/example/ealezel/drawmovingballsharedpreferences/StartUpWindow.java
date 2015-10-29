package com.example.ealezel.drawmovingballsharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ealezel.drawmovingballsharedpreferences.classes.DrawScene;

public class StartUpWindow extends AppCompatActivity {

    private int start_x;
    private int start_y;
    public static final String PREFS_NAME = "BallSharedPrefsFile";
    DrawScene scene;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_window);

        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        start_x = storedData.getInt("start_x", 50);
        start_y = storedData.getInt("start_y", 50);

        scene = new DrawScene(this, start_x, start_y);
        setContentView(scene);
}

    @Override    protected void onStop() {
        super.onStop();
        this.savePosition();
   }

    protected void savePosition() {
        if (scene != null)
        {
            SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor storedDataEditor = storedData.edit();
            storedDataEditor.putInt("start_x", scene.getPositionX());
            storedDataEditor.putInt("start_y", scene.getPositionY());
            storedDataEditor.commit();
        }

    }
}
