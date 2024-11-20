package com.example.game_duoihinhxepchu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnBatdau, btnChoitiep, btnBXH, btnThoat;
    ImageButton btnlanguage;
    String[] c = new String[]{"VN","EN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetView();
        btnBatdau.setOnClickListener(this);
        btnlanguage.setOnClickListener(this);
        btnChoitiep.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
    }
    private void GetView(){
        btnBatdau = (Button) findViewById(R.id.batdau);
        btnChoitiep = (Button) findViewById(R.id.choitiep);
        btnBXH = (Button) findViewById(R.id.BXH);
        btnThoat = (Button) findViewById(R.id.thoat);
        btnlanguage = (ImageButton) findViewById(R.id.btnlanguage);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.batdau:
                Intent a = new Intent(this, Manchinh.class);
                startActivity(a);
                break;

            case R.id.btnlanguage:
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_ngonngu);
                final ListView listN2 = (ListView) dialog.findViewById(R.id.list_n2);
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, c);
                listN2.setAdapter(adapter);
                listN2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String val =(String) listN2.getItemAtPosition(i);
                        if(val.equals("VN"))
                        {
                            setLanguage("vi");
                        }else
                        {
                            setLanguage("en");
                        }
                    }
                });
                dialog.show();
                break;
            case R.id.choitiep:
                int index = 0, money = 0;
                SharedPreferences sp = getSharedPreferences("choitiepindex", MODE_PRIVATE);
                index = sp.getInt("ax", 0);
                money = sp.getInt("bx", 0);
                Intent intent = new Intent(MainActivity.this, Manchinh.class);
                intent.putExtra("hello",index);
                intent.putExtra("hi", money);
                startActivity(intent);
                break;
            case R.id.thoat:
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
                break;
        }
    }

    public void setLanguage(String language){
        Locale locale = new Locale(language);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

}