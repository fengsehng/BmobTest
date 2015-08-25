package com.example.euler_kalvinhe.bmobtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Euler-KalvinHe on 2015/8/24.
 */
public class OutActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"登陆成功",Toast.LENGTH_LONG).show();
    }
}
