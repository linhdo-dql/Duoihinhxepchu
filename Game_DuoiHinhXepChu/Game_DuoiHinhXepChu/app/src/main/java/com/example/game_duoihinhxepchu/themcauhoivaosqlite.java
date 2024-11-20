package com.example.game_duoihinhxepchu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class themcauhoivaosqlite extends AppCompatActivity{
    EditText name,dapan;
    ImageView img;
    Button btnThem,ChangeImage;
    public static Database data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themcauhoivaosqlite);
        data = new Database(this, "DBCH.sqlite", null, 1);
        //data.TruyVan();
        name = (EditText) findViewById(R.id.a1);
        img = (ImageView) findViewById(R.id.a3);
        dapan = (EditText) findViewById(R.id.a4);
        btnThem = (Button) findViewById(R.id.a5);
        ChangeImage = (Button) findViewById(R.id.btnChangeImage);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    //data.Insertcauhoi(name.getText().toString(), ConverttoArrayByte(img), dapan.getText().toString());
                    Toast.makeText(themcauhoivaosqlite.this, "Thành Công", Toast.LENGTH_LONG).show();
                    name.requestFocus();
                }catch (Exception e)
                {
                    Toast.makeText(themcauhoivaosqlite.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        });
        ChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = getResources().getIdentifier(name.getText().toString(),"drawable", getPackageName());
                img.setImageResource(id);
                dapan.requestFocus();
            }
        });

    }
    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}