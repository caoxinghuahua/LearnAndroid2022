package com.example.aroutermain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

//import com.example.modulea.A_MainActivity;
//import com.example.moduleb.B_MainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.gotoA).setOnClickListener(
                v->{
//                    Intent intent=new Intent(this, A_MainActivity.class);
//                    startActivity(intent);
                }
        );
        findViewById(R.id.gotoB).setOnClickListener(
                v -> {
//                    Intent intent=new Intent(this, B_MainActivity.class);
//                    startActivity(intent);
                }
        );
    }
}