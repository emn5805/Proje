package com.example.lkuygulama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText mail, sifre;
    private Button giriş, kayit;
    private ProgressDialog loginProg;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail = (EditText) findViewById(R.id.mail);
        sifre = (EditText) findViewById(R.id.şifre);
        giriş = (Button) findViewById(R.id.GirişButon);
        kayit = (Button) findViewById(R.id.kayitButon);
        loginProg=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();

       kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kayitli();

            }
        });

       giriş.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            String ilkmail = mail.getText().toString();
            String passW= sifre.getText().toString();

                if(!TextUtils.isEmpty(ilkmail) && !TextUtils.isEmpty(passW)){

                    loginProg.setTitle("Oturum Açılıyor");
                    loginProg.setMessage("Hesabınıza giriş Yapılıyor... ");
                    loginProg.setCanceledOnTouchOutside(false);
                    loginProg.show();

                    loginUser(ilkmail,passW);
                }
           }
       });
    }

    private void loginUser(String ilkmail, String passW) {
        mAuth.signInWithEmailAndPassword(ilkmail,passW).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        loginProg.dismiss();

                        Toast.makeText(getApplicationContext(),"Giriş Başarılı!",Toast.LENGTH_SHORT).show();
                            Intent mainint = new Intent(Login.this, MainActivity.class);
                            mainint.putExtra("uid",mAuth.getUid());// bu noktadad getUid alıyorum uid ile kimlik verdim
                            startActivity(mainint);
                    }
                    else{
                        loginProg.dismiss();
                        Toast.makeText(getApplicationContext(),"Giriş Yapılamadı!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

    private void kayitli() {
        Intent intent = new Intent(Login.this, KayitActivity.class);
        startActivity(intent);

    }
}
