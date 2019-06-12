package com.droidrank.sample;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,pass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_login=(Button)findViewById(R.id.button_Login);
        username=(EditText)findViewById(R.id.edittext_MobileNumber);
        pass=(EditText)findViewById(R.id.edittext_Password);
        username.setText("priyanka");
        pass.setText("AgiliZ12345");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("priyanka") && pass.getText().toString().equals("AgiliZ12345")){
                    SharedPreferences sp=getSharedPreferences("INTELLI",getApplicationContext().MODE_PRIVATE);
                    sp.edit().putString("login","true").commit();
                    Intent i=new Intent(LoginActivity.this,Activity_Dashboard.class);
                    i.putExtra("login","true");
                    startActivity(i);
                    finish();
                }else
                {
                    Toast.makeText(LoginActivity.this, "please check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
