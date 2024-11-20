package com.example.game_duoihinhxepchu.xuly;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.game_duoihinhxepchu.CauHoi;
import com.example.game_duoihinhxepchu.R;

import java.util.List;

public class listAdapter extends ArrayAdapter<CauHoi> {

    public listAdapter(Context context, int resource,List<CauHoi> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if (view ==null)
        {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            view=inflater.inflate(R.layout.row_list, null);
        }
        CauHoi cauHoi =getItem(position);
        if (cauHoi!=null)
        {

            ImageView imgDebai = (ImageView) view.findViewById(R.id.anhdebai1);
            Bitmap bitmap= BitmapFactory.decodeByteArray(cauHoi.hinhanh, 0, cauHoi.hinhanh.length);
            imgDebai.setImageBitmap(bitmap);

        }
        return view;
    }
}
