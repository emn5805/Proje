package com.example.lkuygulama;

public class Urun2 {
    private String baslik2;
    private String fiyat2;
    private String kullanici2;
    private String url2;
    private String eskiFiyat2;

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getEskiFiyat2() {
        return eskiFiyat2;
    }

    public void setEskiFiyat2(String eskiFiyat2) {
        this.eskiFiyat2 = eskiFiyat2;
    }

    public Urun2(String url2, String eskiFiyat2) {
        this.url2 = url2;
        this.eskiFiyat2 = eskiFiyat2;
    }



    public String getBaslik2() {
        return baslik2;
    }
    public Urun2() {
        this.baslik2 = baslik2;
        this.fiyat2 = fiyat2;
        this.kullanici2 = kullanici2;
    }


    public void setBaslik2(String baslik2) {
        this.baslik2 = baslik2;
    }

    public String getFiyat2() {
        return fiyat2;
    }

    public void setFiyat2(String fiyat2) {
        this.fiyat2 = fiyat2;
    }

    public String getKullanici2() {
        return kullanici2;
    }

    public void setKullanici2(String kullanici2) {
        this.kullanici2 = kullanici2;
    }



}
