package com.example.mypc.infosehat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tentang extends AppCompatActivity {
    TextView textDetail;
    ImageView imageView;
    ZoomLayout myZoomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        try {
            openFileText();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Problems: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        setContentView(myZoomView);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View infl = inflater.inflate(R.layout.activity_tentang, container, false);
        ZoomLayout myZoomView = new ZoomLayout(this.getApplicationContext());
        myZoomView.addView(infl);
        return myZoomView;
    }

    public void openFileText() throws IOException {
        String str="";
        StringBuffer buf = new StringBuffer();
        InputStream is = this.getAssets().open( "TENTANG.txt" );
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is!=null) {
            while ((str = reader.readLine()) != null) {
                buf.append(str + "\n" );
            }
        }
        is.close();
        textDetail.setText(buf.toString());

        int id = getResources().getIdentifier("com.example.mypc.infosehat:drawable/tentang", null, null);
        imageView.setImageResource(id);
    }
    private void init(){
        textDetail = (TextView)findViewById(R.id.txtdetailtentang);
        imageView = (ImageView)findViewById(R.id.imageViewtentang);
    }
}
