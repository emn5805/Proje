package com.example.lkuygulama;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter2 extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<Urun2> urunler2;

    public CustomAdapter2(Context context, ArrayList<Urun2> urunler2) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.urunler2 = urunler2;
    }

    @Override
    public int getCount() {
        return urunler2.size();
    }

    @Override
    public Object getItem(int i) {
        return urunler2.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.satir_layout, viewGroup, false);

        TextView tid1 = view.findViewById(R.id.satir_id1);
        TextView tad1 = view.findViewById(R.id.satir_ad1);
        TextView tfiyat1 = view.findViewById(R.id.satir_fiyat2);
        TextView tEskifyat2= view.findViewById(R.id.satir_eskiFiyat1);

        Urun2 u2 = urunler2.get(i);
        tid1.setText(u2.getKullanici2());
        tad1.setText(u2.getBaslik2());
        tfiyat1.setText(u2.getFiyat2());
        tEskifyat2.setText(u2.getEskiFiyat2());

        return view;
    }
}
