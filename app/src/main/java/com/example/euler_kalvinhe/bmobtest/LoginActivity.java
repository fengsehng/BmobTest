package com.example.euler_kalvinhe.bmobtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Euler-KalvinHe on 2015/8/24.
 */
public class LoginActivity extends Activity {
    private EditText userName,password;
    private Button login,regeter;
    String username,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initViews();
        Toast.makeText(LoginActivity.this,username+pass,Toast.LENGTH_LONG).show();
        Bmob.initialize(this, "79bda8edfbabc323575348b6d50508e6");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = userName.getText().toString();
                pass = password.getText().toString();
                BmobQuery<User> query = new BmobQuery<User>();
                query.addWhereEqualTo("userName",username);
                query.findObjects(LoginActivity.this,new FindListener<User>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        String str = null;
                        str = users.get(0).getPassword();
                        if (str.equals(pass)){
                            Intent intent = new Intent(LoginActivity.this,OutActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this,"用户名或者密码不正确",Toast.LENGTH_LONG).show();
                        }
                       // Toast.makeText(LoginActivity.this,str,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(int i, String s) {
                        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        regeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegesterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void initViews(){
        userName = (EditText) findViewById(R.id.userName);
        login = (Button) findViewById(R.id.log);
        password = (EditText) findViewById(R.id.password);
        regeter = (Button) findViewById(R.id.regester);
    }
    private void logIn(){
        username = userName.getText().toString();
        pass = password.getText().toString();

    }
    private String queryPass(String username){
        final String[] passTwo = {null};
        if (username == null){
            return null;
        }
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("userName",username);
        query.findObjects(LoginActivity.this,new FindListener<User>() {
            @Override
            public void onSuccess(List<User> users) {
                passTwo[0] = users.get(0).getPassword();
                Toast.makeText(LoginActivity.this,passTwo[0],Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
            }
        });
        return passTwo[0];
    }
}
