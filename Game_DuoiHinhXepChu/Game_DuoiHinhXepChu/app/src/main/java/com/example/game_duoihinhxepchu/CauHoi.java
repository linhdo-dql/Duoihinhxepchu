package com.example.game_duoihinhxepchu;

import java.util.ArrayList;

public class CauHoi extends ArrayList<CauHoi> {
    public String tenCH, DapAn;
    public byte[] hinhanh;
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CauHoi(int id, String tenCH, byte[] hinhanh, String dapAn) {
        this.id = id;
        this.tenCH = tenCH;
        DapAn = dapAn;
        this.hinhanh = hinhanh;
    }

    public String getTenCH() {
        return tenCH;
    }

    public void setTenCH(String tenCH) {
        this.tenCH = tenCH;
    }

    public String getDapAn() {
        return DapAn;
    }

    public void setDapAn(String dapAn) {
        DapAn = dapAn;
    }

    public byte[] getHinh() {
        return hinhanh;
    }

    public void setHinh(byte[] hinh) {
        this.hinhanh = hinh;
    }
}
