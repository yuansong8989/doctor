package com.example.doctor.project.Acitivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctor.R;
import com.example.doctor.project.Adapter.DanYuanAdapter;

public class DanYuanActivity extends AppCompatActivity {
    TextView textView;
    TextView back;
    ListView listView;
    DanYuanAdapter danYuanAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuanzhe);
        intiview();
    }
    private void intiview(){
        listView=(ListView)findViewById(R.id.danyuanlist);
        textView=(TextView)findViewById(R.id.txt_main_title);
        back=(TextView)findViewById(R.id.txt_left_title);
        textView.setText(getIntent().getStringExtra("title"));
        danYuanAdapter=new DanYuanAdapter(this);
        listView.setAdapter(danYuanAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
