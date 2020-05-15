package com.example.lkuygulama;

public class Urun {
    private String baslik;
    private String fiyat;
    private String kullanici;
    private String url;
    private String eskiFyat;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEskiFyat() {
        return eskiFyat;
    }

    public void setEskiFyat(String eskiFyat) {
        this.eskiFyat = eskiFyat;
    }




    public Urun(String baslik, String fiyat) {
        this.baslik = baslik;
        this.fiyat = fiyat;
    }



    public Urun() {
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getKullanici() {
        return kullanici;
    }

    public void setKullanici(String kullanici) {
        this.kullanici = kullanici;
    }

}
