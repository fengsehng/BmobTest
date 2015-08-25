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
import cn.bmob.v3.listener.SaveListener;


public class RegesterActivity extends Activity{
    private EditText userName, phoneNumber, password, passwordAgain;
    private Button regester,login;
    private String username,pass,passAgain;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regester);
        initViews();
        Bmob.initialize(this, "79bda8edfbabc323575348b6d50508e6");
        regester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regesterUser();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegesterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        regester = (Button) findViewById(R.id.regester);
        passwordAgain = (EditText) findViewById(R.id.passwordAgain);
        password = (EditText) findViewById(R.id.password);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        userName = (EditText) findViewById(R.id.userName);
        login = (Button) findViewById(R.id.login);
    }
    private void regesterUser(){
        username = userName.getText().toString();
        pass = password.getText().toString();
        passAgain = passwordAgain.getText().toString();
        phone = phoneNumber.getText().toString();
        if (username != null && pass != null && password != null && phone != null ){
            if ( pass.equals(passAgain)){
            BmobQuery<User> query = new BmobQuery<User>();
            query.findObjects(RegesterActivity.this,new FindListener<User>() {
                @Override
                public void onSuccess(List<User> users) {
                    boolean flag = false;
                    for (User u:users){
                        if (phone.equals(u.getPhoneNumber())){
                            flag = true;
                            break;
                        }
                    }
                    if (flag){
                        phoneNumber.setText("");
                        Toast.makeText(RegesterActivity.this, "手机号已经被注册,请重试", Toast.LENGTH_LONG).show();
                    }else{
                        User user = new User();
                        user.setUserName(username);
                        user.setPassword(pass);
                        user.setPhoneNumber(phone);
                        user.save(RegesterActivity.this,new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(RegesterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegesterActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(RegesterActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(RegesterActivity.this,"请检查网络重试",Toast.LENGTH_LONG).show();
                }
            });}else{
                password.setText("");
                passwordAgain.setText("");
                Toast.makeText(RegesterActivity.this,"密码不一致,请重试",Toast.LENGTH_LONG).show();
            }
        }else {
            userName.setText("");
            password.setText("");
            passwordAgain.setText("");
            phoneNumber.setText("");
            Toast.makeText(RegesterActivity.this,"不能为空",Toast.LENGTH_LONG).show();
        }
    }
}
