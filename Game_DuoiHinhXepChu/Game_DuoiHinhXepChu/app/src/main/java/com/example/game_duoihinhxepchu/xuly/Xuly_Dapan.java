package com.example.game_duoihinhxepchu.xuly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.game_duoihinhxepchu.R;

import java.util.ArrayList;
import java.util.List;

public class Xuly_Dapan extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> arrDA;

    public Xuly_Dapan(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrDA = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null)
            {
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.dapan, null);
            }
            TextView dapan = convertView.findViewById(R.id.kitu);
            dapan.setText(this.arrDA.get(position));
            return convertView;
    }
}

