package com.example.lkuygulama;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<Urun> urunler;

    public CustomAdapter(Context context, ArrayList<Urun> urunler) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.urunler = urunler;
    }

    @Override
    public int getCount() {
        return urunler.size();
    }

    @Override
    public Object getItem(int i) {
        return urunler.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.gitti_gidiyor, viewGroup, false);

        TextView tid = view.findViewById(R.id.satir_id1);
        TextView tad = view.findViewById(R.id.satir_ad1);
        TextView tfiyat = view.findViewById(R.id.satir_fiyat2);
        TextView tEskiFiyat = view.findViewById(R.id.satir_eskiFiyat2);

        Urun u = urunler.get(i);
        tid.setText(u.getKullanici());
        tad.setText(u.getBaslik());
        tfiyat.setText(u.getFiyat());
        tEskiFiyat.setText(u.getEskiFyat());

        return view;
    }
}
