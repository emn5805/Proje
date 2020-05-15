package com.example.lkuygulama;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class UrunEklemeActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    private String userId;
    private FirebaseAuth.AuthStateListener mAuthlistener;
    public ArrayList<Urun> goster = new ArrayList<>();
    public ArrayList<Urun2> goster2 = new ArrayList<>();
    ListView lv,lv2;
    CustomAdapter customAdapter;
    CustomAdapter2 customAdapter2;
    public Button liste;
    private FirebaseAuth mAuth;
    private TextView textView;
    Context context = this;
    String uid;

    ChildEventListener urunlerListener;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ekleme);
        Intent gelen = getIntent();

        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getUid();//gelen uid

        Urun urun = new Urun();
        textView = (TextView)findViewById(R.id.text2);
        liste = (Button) findViewById(R.id.urunListeButon);
        lv=(ListView)findViewById(R.id.listele);
        lv2=(ListView)findViewById(R.id.listele2);
        customAdapter = new CustomAdapter(this, goster);
        customAdapter2 = new CustomAdapter2(this, goster2);
        lv.setAdapter(customAdapter);
        lv2.setAdapter(customAdapter2);

        mAuthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                userId = mAuth.getUid();
            }
        };
        tanimla();
        listele();
        gitti();

liste.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent git = new Intent(UrunEklemeActivity.this,MainActivity.class);
        startActivity(git);
    }
});
    }

    private void gitti() {
        System.out.println(uid);
        urunlerListener =  reference.child("urunler").child("GittiGidiyor").child(uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Urun2 u2 = dataSnapshot.getValue(Urun2.class); // Veritabanı okuma kodları
                goster2.add(u2);
                customAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void tanimla() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reference.child("urunler").child("Userlar").child(uid).removeEventListener(urunlerListener);
        reference.child("urunler").child("GittiGidiyor").child(uid).removeEventListener(urunlerListener);
    }

    private void listele() {
        System.out.println(uid);
        urunlerListener =  reference.child("urunler").child("Userlar").child(uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Urun u = dataSnapshot.getValue(Urun.class); // Veritabanı okuma kodları
                goster.add(u);
                customAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        

    }

}
