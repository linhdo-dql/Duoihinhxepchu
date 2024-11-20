package com.example.game_duoihinhxepchu;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game_duoihinhxepchu.xuly.Xuly_Dapan;
import com.example.game_duoihinhxepchu.xuly.Xuly_GoiY;

import java.util.ArrayList;
import java.util.Random;

public class Manchinh extends AppCompatActivity {
    public ArrayList<CauHoi> cauHois;
    ImageView imgCH;
    Button btnback, btnquaylai, btnCautiep, btnGoiY, btnDoiCauHoi, btnHuy, btnDongy;
    String txtdapan = "";
    ArrayList<String> arrCTL;
    GridView gridView;
    int vtri = 0;
    ArrayList<String> arrGY;
    GridView gridViewGY;
    TextView txtLevel, txtTien, txtdapanchuan, txtThongbao;
    private int x = 0;
    int tien = 10;
    int capdo = 1;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manchinh);
        try {
            GetView();
            if (getIntentData() != 1) {
                getIntentData();
            }
            khoitao();
            btnback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Manchinh.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            //đổi câu hỏi khác
            btnDoiCauHoi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        tien = tien - 5;
                        khoitao();
                    } catch (Exception ex) {
                    }
                }
            });
            //GY
            btnGoiY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MoGoiY();
                    txtTien.setText(""+tien);
                    LayoutInflater li = getLayoutInflater();
                    View layout = li.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.customToast));
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void GetView() {
        imgCH = (ImageView) findViewById(R.id.anhdebai1);
        txtLevel = (TextView) findViewById(R.id.txtLevel);
        gridView = (GridView) findViewById(R.id.listCTL);
        gridViewGY = (GridView) findViewById(R.id.listGY);
        btnback = (Button) findViewById(R.id.back);
        txtTien = (TextView) findViewById(R.id.txtTien);
        btnGoiY = (Button) findViewById(R.id.btnGoiY);
        btnDoiCauHoi = (Button) findViewById(R.id.btnDoiCauHoi);
    }

    private int getIntentData() {
        Intent intent = getIntent(); //tiep nhan gia tri intent duoc truyen sang
        capdo = intent.getIntExtra("hello", capdo);
        tien = intent.getIntExtra("hi", tien);
        return capdo;
    }


    //thông báo của chức năng sau khi trả lời đúng câu hỏi
    //chơi tiếp hay dừng lại để lưu
    public void Dialog() {
        final Dialog di = new Dialog(this);
        di.requestWindowFeature(Window.FEATURE_NO_TITLE);
        di.setContentView(R.layout.dialog_doicau);
        txtdapanchuan = (TextView) di.findViewById(R.id.txtDAPAN);
        btnquaylai = (Button) di.findViewById(R.id.RestartCH);
        btnCautiep = (Button) di.findViewById(R.id.btnNextCH);
        txtdapanchuan.setText(cauHois.get(x).tenCH);

        btnquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("choitiepindex", MODE_PRIVATE);
                sp.edit().putInt("ax", capdo + 1).apply();
                sp.edit().putInt("bx", tien).apply();
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });
        btnCautiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capdo++;
                txtLevel.setText("" + capdo);
                khoitao();
                vtri = 0;
                di.dismiss();
                SharedPreferences sp = getSharedPreferences("choitiepindex", MODE_PRIVATE);
                sp.edit().clear();
            }
        });
        di.show();

    }


    public void khoitao() {
        arrCTL = new ArrayList<>();
        arrGY = new ArrayList<>();
        cauHois = new ArrayList<>();
        cauHois.clear();
        Database db = new Database(getApplication(), "BH.sqlite", null, 1);
        Cursor cursor = db.TruyVanTraVe("SELECT * FROM batchu");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenCH = cursor.getString(1);
            byte[] img = cursor.getBlob(2);
            String dapan = cursor.getString(3);
            cauHois.add(new CauHoi(id, tenCH, img, dapan));
        }
        int size = cauHois.size();
        if (size != 0 && cauHois != null) {
            x = r.nextInt(size);
            Bitmap bitmap = BitmapFactory.decodeByteArray(cauHois.get(x).getHinh(), 0, cauHois.get(x).hinhanh.length);
            imgCH.setImageBitmap(bitmap);
            txtdapan = cauHois.get(x).getDapAn();
        }
        txtTien.setText("" + tien);
        txtLevel.setText("" + capdo);
        data();
        setOnclick();
        showDA();
        showGY();
        KiemtraDapAn();
    }

    void showDA() {
        gridView.setNumColumns(arrCTL.size());
        gridView.setAdapter(new Xuly_Dapan(this, 0, arrCTL));
    }

    void showGY() {
        gridViewGY.setNumColumns(arrGY.size() / 2);
        gridViewGY.setAdapter(new Xuly_GoiY(this, 0, arrGY));
    }

    void data() {
        arrCTL.clear();
        arrGY.clear();
        Random r = new Random();
        //Random các kí tự ASCII trong các ô gợi
        for (int i = 0; i < txtdapan.length(); i++) {
            arrCTL.add("");
            String s = "" + (char) (r.nextInt(26) + 65);
            arrGY.add(s);
            String s1 = "" + (char) (r.nextInt(26) + 65);
            arrGY.add(s1);
        }
        //Truyền đáp án cảu câu hỏi vào các ô gợi ý
        for (int i = 0; i < txtdapan.length(); i++) {
            String s = "" + txtdapan.charAt(i);
            arrGY.set(i, s.toUpperCase());
        }
        //Random các kí tự của đáp án với các ô gợi ý
        for (int i = 0; i < 10; i++) {
            String s = "" + arrGY.get(i);
            int vt = r.nextInt(arrGY.size());
            arrGY.set(i, arrGY.get(vt));
            arrGY.set(vt, s);
        }
    }

    public void KiemtraDapAn() {
        String s = "";
        for (String x : arrCTL) {
            s = s + x;
        }
        if (s.equalsIgnoreCase(txtdapan.toString())) {
            tien += 10;
            Dialog();
        }
    }

    public void setOnclick() {
        gridViewGY.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = (String) adapterView.getItemAtPosition(i);
                if (s.length() != 0 && vtri < arrCTL.size()) {
                    for (int z = 0; z < arrCTL.size(); z++) {
                        if (arrCTL.get(z).length() == 0) {
                            vtri = z;
                            break;
                        }
                    }
                    arrGY.set(i, "");
                    arrCTL.set(vtri, s);
                    vtri++;
                    LinearLayout layout = (LinearLayout) findViewById(R.id.layoutGY);
                    layout.removeAllViews();
                    KiemtraDapAn();
                    showDA();
                    showGY();
                }
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = (String) adapterView.getItemAtPosition(i);
                vtri = i;
                if (s.length() != 0) {
                    arrCTL.set(i, "");
                    for (int z = 0; z < arrGY.size(); z++)

                        if (arrGY.get(z).length() == 0) {
                            arrGY.set(z, s);
                            break;
                        }
                    showDA();
                    showGY();
                }
            }

        });
    }

    public void MoGoiY() {
        int id = -1;
        tien = tien - 1;
        for (int i = 0; i < arrCTL.size(); i++) {
            if (arrCTL.get(i).length() == 0) {
                id = i;
                break;
            }
        }
        String goiy = "" + txtdapan.charAt(id);
        goiy = goiy.toUpperCase();
        for (int i = 0; i < arrGY.size(); i++) {
            if (goiy.equals(arrGY.get(i))) {
                arrGY.set(i, "");
                break;
            }
        }
        arrCTL.set(id,goiy);


        showDA();
        showGY();
    }

}