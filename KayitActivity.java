package com.example.lkuygulama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.ScheduledExecutorService;

public class KayitActivity extends AppCompatActivity {
    private EditText isimKayit, mailKayit,mailSifre;
    private Button kayitButon;
    private ProgressDialog kayitDiyalog;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        kayitButon=(Button) findViewById(R.id.kayitliButon);
        isimKayit= (EditText)findViewById(R.id.isimKayit);
        mailKayit = (EditText)findViewById(R.id.kayitMail);
        mailSifre = (EditText)findViewById(R.id.kayitSifre);
        kayitDiyalog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        kayitButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isim = isimKayit.getText().toString();
                String parola = mailSifre.getText().toString();
                String mailAdı = mailKayit.getText().toString();

                if(!TextUtils.isEmpty(isim)&& !TextUtils.isEmpty(parola)&& !TextUtils.isEmpty(mailAdı)){

                        kayitDiyalog.setTitle("Kayıt Yapılıyor");
                        kayitDiyalog.setMessage("Hesap Oluşturuluyor...");
                        kayitDiyalog.setCanceledOnTouchOutside(false);
                        kayitDiyalog.show();

                        kayitKullanici(isim,parola,mailAdı);
                }
            }
        });

    }

    private void kayitKullanici(final String isim, final String parola, String mailAdı) {
        mAuth.createUserWithEmailAndPassword(mailAdı,parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        String user_id = mAuth.getCurrentUser().getUid();
                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Kullanıcılar").child(user_id);
                        HashMap<String, String> userMap = new HashMap<>();
                        userMap.put("isim",isim);
                        userMap.put("Parola",parola);
                        mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        kayitDiyalog.dismiss();
                                        Intent main = new Intent(KayitActivity.this, MainActivity.class);
                                        startActivity(main);

                                    }
                            }
                        });
                    }
                    else{

                        kayitDiyalog.dismiss();
                        Toast.makeText(getApplicationContext(),"Hata Oluştu"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
