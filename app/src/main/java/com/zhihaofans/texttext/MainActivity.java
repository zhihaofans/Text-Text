package com.zhihaofans.texttext;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Spinner  sp;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CloseActivityClass.activityList.add(this);  //退出代码初始化

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://github.com/zhihaofans/Text-Text/issues/new");
                intent.setData(content_url);
                startActivity(intent);
            }
        });
        Button button_encode=(Button)findViewById(R.id.button_encode);
        Button button_decode=(Button)findViewById(R.id.button_decode);
        Button button_copy=(Button)findViewById(R.id.button_copy);
        Button button_paste=(Button)findViewById(R.id.button_paste);
        Button button_delall=(Button)findViewById(R.id.button_delall);
        sp = (Spinner) findViewById(R.id.spinner_type);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //拿到被选择项的值
                TextView temptext=(TextView) findViewById(R.id.textView_temp);
                String str= (String) sp.getSelectedItem();
                temptext.setText(str);
                //把该值传给 TextView
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        button_encode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView temptext=(TextView) findViewById(R.id.textView_temp);
                EditText editText1=(EditText) findViewById(R.id.editText);
                String str= (String) temptext.getText();
                String oldstr= (String) editText1.getText().toString();
                String newstr =oldstr;
                switch (str){
                    case "Urlencode":
                        try {
                            newstr=URLEncoder.encode(oldstr,"UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ASCII":
                        try {
                            newstr= StringToSth.StringToAscii(oldstr);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Unicode":
                        newstr= StringToSth.StringToUnicode(oldstr);
                        break;
                    case "Base64":
                        newstr=Base64.encodeToString(oldstr.getBytes(), Base64.DEFAULT);
                        break;
                    default:
                        midToast("该功能制作中",1);
                }
                editText1.setText(newstr);
            }
        });
        button_decode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView temptext=(TextView) findViewById(R.id.textView_temp);
                EditText editText1=(EditText) findViewById(R.id.editText);
                String str= (String) temptext.getText();
                String oldstr= (String) editText1.getText().toString();
                String newstr =oldstr;
                switch (str){
                    case "Urlencode":
                        try {
                            newstr= URLDecoder.decode(oldstr,"UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ASCII":
                        try {
                            newstr= StringToSth.AsciiToString(oldstr);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Unicode":
                        newstr= StringToSth.UnicodeToString(oldstr);
                        break;
                    case "Base64":
                        newstr = new String(Base64.decode(oldstr.getBytes(), Base64.DEFAULT));
                        break;
                    default:
                        midToast("该功能制作中",1);
                }
                editText1.setText(newstr);

            }
        });
        button_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText editText1=(EditText) findViewById(R.id.editText);
                String oldstr= (String) editText1.getText().toString();
                if(oldstr!=""){
                    SysAct.Tcopy(oldstr,MainActivity.this);
                    midToast("复制成功",1);
                }

            }
        });
        button_paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText editText1=(EditText) findViewById(R.id.editText);
                String pastetext=SysAct.Tpaste(MainActivity.this);
                if(pastetext==""){
                    midToast("剪切板空白",1);
                }else{
                    editText1.setText(pastetext);
                    midToast("粘贴成功",1);
                }


            }
        });
        button_delall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText editText1=(EditText) findViewById(R.id.editText);
                String oldstr= (String) editText1.getText().toString();
                if(oldstr!=""){
                    editText1.setText("");
                    midToast("清空成功",1);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_about:
                Intent intent_about =new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent_about);
                return true;
            case R.id.action_exit:
                CloseActivityClass.exitClient(MainActivity.this);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
    void midToast(String str, int showTime)
    {
        Toast toast = Toast.makeText(MainActivity.this, str, showTime);
        toast.setGravity(Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL , 0, 0);  //设置显示位置
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);     //设置字体颜色
        toast.show();
    }
}