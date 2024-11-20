package com.example.game_duoihinhxepchu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {


    public Database(Context con, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(con,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = String.format("DROP TABLE IF EXISTS %s", "batchu");
        db.execSQL(sql);
        onCreate(db);
    }

    /*void TruyVan()
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS batchu (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, image BLOB, answer VARCHAR)");
    }*/
    Cursor TruyVanTraVe(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        return db.rawQuery(sql,null);
    }

    /*void Insertcauhoi(String ten, byte[] hinh, String dapan) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Insert into batchu values (null,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, ten);
        statement.bindBlob(2, hinh);
        statement.bindString(3, dapan);
        statement.executeInsert();
    }*/
}
