package com.zhihaofans.texttext;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Button button_github_p=(Button)findViewById(R.id.button_github_p);
        button_github_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://github.com/zhihaofans/Text-Text/");
                intent.setData(content_url);
                startActivity(intent);
            }
        });
        Button button_github=(Button)findViewById(R.id.button_github);
        button_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://github.com/zhihaofans/");
                intent.setData(content_url);
                startActivity(intent);
            }
        });
    }
}
